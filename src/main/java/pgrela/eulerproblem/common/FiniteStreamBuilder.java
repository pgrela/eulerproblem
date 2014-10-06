package pgrela.eulerproblem.common;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class FiniteStreamBuilder<T> {
    UnaryOperator<T> generator;
    Predicate<T> endCondition;
    T seed;

    public FiniteStreamBuilder<T> of(UnaryOperator<T> generator) {
        this.generator = generator;
        return this;
    }

    public FiniteStreamBuilder<T> from(T seed) {
        this.seed = seed;
        return this;
    }

    public FiniteStreamBuilder<T> until(Predicate<T> endCondition) {
        this.endCondition = endCondition;
        return this;
    }

    public Stream<T> stream() {
        return Streams.stream(new FiniteIterator<>(seed, generator, endCondition));
    }

    public static <T> FiniteStreamBuilder<T> streamFrom(T seed){
        return new FiniteStreamBuilder<T>().from(seed);
    }

    public static <T> FiniteStreamBuilder<T> stream(Stream<T> s) {
        FiniteStreamBuilder<T> finiteStreamBuilder = new FiniteStreamBuilder<>();
        Iterator<T> iterator = s.iterator();
        finiteStreamBuilder.of($ -> iterator.next());
        finiteStreamBuilder.from(iterator.next());
        return finiteStreamBuilder;
    }
}
