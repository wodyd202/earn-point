package com.ljy.core.es.event;

import com.ljy.core.es.domain.AggregateRoot;
import com.ljy.core.es.snapshot.Snapshot;
import com.ljy.core.es.snapshot.SnapshotRepository;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

abstract public class AbstractEventHandler<A extends AggregateRoot, ID extends Serializable> implements EventHandler<A,ID> {
    private final Class<A> aggregateType;
    private EventStore<ID> eventStore;
    private SnapshotRepository<A, ID> snapshotRepository;
    private final static int SNAPSHOT_COUNT = 5;

    abstract protected Snapshot<A, ID> newSnapshot(A aggregate);

    protected AbstractEventHandler(EventStore<ID> eventStore, SnapshotRepository<A, ID> snapshotRepository) {
        this.aggregateType = aggregateType();
        this.eventStore = eventStore;
        this.snapshotRepository = snapshotRepository;
    }

    @Override
    final public void save(A aggregate) {
        ID identifier = (ID) aggregate.getIdentifier();
        eventStore.saveEvents(identifier, aggregate.getExpectedVersion(), aggregate.unCommitedChange());
        aggregate.markChangedCommited();
        long countEvents = eventStore.countEvents(identifier);

        if(shouldSaveSnapshot(countEvents)){
            Snapshot<A, ID> snapshot = newSnapshot(aggregate);
            snapshotRepository.save(snapshot);
        }
    }

    private boolean shouldSaveSnapshot(long countEvents) {
        return countEvents % SNAPSHOT_COUNT == 0;
    }

    @Override
    final public Optional<A> find(ID identifier) {
        A aggregateRoot = createRootAggregateViaReflection(identifier);
        Optional<Snapshot<A, ID>> retrieveSnapshot = retrieveSnapshot(identifier);
        List<Event> baseEvents;

        if(retrieveSnapshot.isPresent()){
            Snapshot<A, ID> snapshot = retrieveSnapshot.get();
            baseEvents = eventStore.getEventsByAfterVersion(identifier, snapshot.getVersion());
            aggregateRoot = snapshot.getAggregateRoot();
        }else{
            baseEvents = eventStore.getEvents(identifier);
        }

        if(baseEvents.isEmpty()){
            return Optional.empty();
        }
        aggregateRoot.replay(baseEvents);
        return Optional.of(aggregateRoot);
    }

    private Optional<Snapshot<A,ID>> retrieveSnapshot(ID identifier){
        if(Objects.isNull(snapshotRepository)){
            return Optional.empty();
        }
        return snapshotRepository.findLatest(identifier);
    }

    private Class<A> aggregateType() {
        Type genType = this.getClass().getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

            if ((params != null) && (params.length >= 1)) {
                return (Class) params[0];
            }
        }
        return null;
    }

    private A createRootAggregateViaReflection(ID identifier){
        A aggregateRoot = null;
        try {
            Constructor<A> declaredConstructor = aggregateType.getDeclaredConstructor(identifier.getClass());
            aggregateRoot = declaredConstructor.newInstance(identifier);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return aggregateRoot;
    }
}
