package com.ljy.core.es.domain;

import com.ljy.core.es.event.Event;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
abstract public class AggregateRoot<ID> {
    protected ID identifier;
    private Long expectedVersion = 0L;
    private final List<Event> events = new ArrayList<>();

    protected AggregateRoot(ID identifier) {
        this.identifier = identifier;
    }

    final public ID getIdentifier() {
        return identifier;
    }

    final public void markChangedCommited(){
        events.clear();
    }

    final public List<Event> unCommitedChange(){
        return events;
    }

    final public Long getExpectedVersion() {
        return expectedVersion;
    }

    final public void replay(List<Event> events){
        for(Event event : events){
            applyChange(event, false);
            expectedVersion++;
        }
    }

    final protected void applyChange(Event event){
        applyChange(event, true);
    }

    private static final String APPLY_METHOD = "apply";
    private void applyChange(Event event, boolean isNew){
        try {
            Method method = getClass().getDeclaredMethod(APPLY_METHOD, event.getClass());
            if(!Objects.isNull(method)){
                method.setAccessible(true);
                method.invoke(this, event);
            }
            if(isNew){
                events.add(event);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
