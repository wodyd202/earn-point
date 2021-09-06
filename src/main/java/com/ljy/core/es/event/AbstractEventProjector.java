package com.ljy.core.es.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

abstract public class AbstractEventProjector implements EventProjector{

    private static String EXECUTE_METHOD = "execute";
    @Override
    public void handle(Event event) {
        try {
            Method method = getClass().getDeclaredMethod(EXECUTE_METHOD, event.getClass());
            if(Objects.isNull(method)){
                throw new NoSuchMethodException();
            }
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
