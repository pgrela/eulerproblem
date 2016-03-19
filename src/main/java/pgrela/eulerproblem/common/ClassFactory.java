package pgrela.eulerproblem.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassFactory {

        @SuppressWarnings(value="Unchecked cast")
        public static<T> T getObjectOf(Class<T> clazz) {
            Constructor<?> constructor;
            try {
                constructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            T instance;

            try {
                instance = (T) constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            return instance;
        }
}
