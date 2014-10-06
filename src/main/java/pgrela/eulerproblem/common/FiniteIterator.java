package pgrela.eulerproblem.common;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class FiniteIterator<T> implements Iterator<T> {
    T nextValue;
    UnaryOperator<T> generator;
    Predicate<T> endCondition;

    public FiniteIterator(T seed, UnaryOperator<T> generator, Predicate<T> endCondition) {
        this.nextValue = seed;
        this.generator = generator;
        this.endCondition = endCondition;
    }

    @Override
    public boolean hasNext() {
        return endCondition.test(nextValue);
    }

    @Override
    public T next() {
        T currentValue = nextValue;
        nextValue = generator.apply(currentValue);
        return currentValue;
    }
}
