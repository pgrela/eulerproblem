package pgrela.eulerproblem.common;

import java.util.Arrays;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Streams {
    /**
     * @see {http://stackoverflow.com/questions/17640754/zipping-streams-using-jdk8-with-lambda-java-util-stream-streams-zip}
     */
    public static <A, B, C> Stream<C> zip(Stream<? extends A> a, Stream<? extends B> b, BiFunction<? super A, ? super B, ? extends C> zipper) {
        Objects.requireNonNull(zipper);
        @SuppressWarnings("unchecked") Spliterator<A> aSpliterator = (Spliterator<A>) Objects.requireNonNull(a).spliterator();
        @SuppressWarnings("unchecked") Spliterator<B> bSpliterator = (Spliterator<B>) Objects.requireNonNull(b).spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int both = aSpliterator.characteristics() & bSpliterator.characteristics() & ~(Spliterator.DISTINCT | Spliterator.SORTED);
        int characteristics = both;

        long zipSize = ((characteristics & Spliterator.SIZED) != 0) ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown()) : -1;

        Iterator<A> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<B> bIterator = Spliterators.iterator(bSpliterator);
        Iterator<C> cIterator = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(aIterator.next(), bIterator.next());
            }
        };

        Spliterator<C> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
        return (a.isParallel() || b.isParallel()) ? StreamSupport.stream(split, true) : StreamSupport.stream(split, false);
    }

    /**
     * http://stackoverflow.com/questions/20746429/java-8-limit-infinite-stream-by-a-predicate
     */
    public static <T> Spliterator<T> takeWhile(Spliterator<T> splitr, Predicate<? super T> predicate) {
        return new Spliterators.AbstractSpliterator<T>(splitr.estimateSize(), 0) {
            boolean stillGoing = true;

            @Override
            public boolean tryAdvance(Consumer<? super T> consumer) {
                if (stillGoing) {
                    boolean hadNext = splitr.tryAdvance(elem -> {
                        if (predicate.test(elem)) {
                            consumer.accept(elem);
                        } else {
                            stillGoing = false;
                        }
                    });
                    return hadNext && stillGoing;
                }
                return false;
            }
        };
    }

    public static <T> Stream<T> takeWhile(T seed, UnaryOperator<T> generator, Predicate<? super T> takeWhile) {
        return StreamSupport.stream(takeWhile(Stream.iterate(seed, generator).spliterator(), takeWhile), false);
    }

    public static <T> Stream<T> stream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
    }

    public static <FIRST, SECOND> Stream<Tuple<FIRST, SECOND>> cartessian(FIRST[] firsts, SECOND[] seconds) {
        return Arrays.stream(firsts).flatMap(f -> Arrays.stream(seconds).map(s -> Tuple.tuple(f, s)));
    }

    public static Stream<Tuple<Long, Long>> cartessian(long[] firsts, long[] seconds) {
        return LongStream.of(firsts).boxed().flatMap(f -> LongStream.of(seconds).mapToObj(s -> Tuple.tuple(f, s)));
    }

    public static <OBJECT extends Comparable<OBJECT>> Stream<OBJECT> mergeSorted(Collection<Stream<OBJECT>> streams) {
        PriorityQueue<Tuple<Spliterator<OBJECT>, OBJECT>> queue = new PriorityQueue<>(Comparator.comparing(Tuple::second));
        streams.stream().map(Stream::spliterator).forEach(spliterator -> spliterator.tryAdvance(object -> queue.add(Tuple.tuple(spliterator, object))));
        System.out.println(Stream.empty().spliterator().tryAdvance(System.out::println));
        return Stream.generate(() -> {
            if (queue.isEmpty()) {
                return null;
            }
            Tuple<Spliterator<OBJECT>, OBJECT> polled = queue.poll();
            polled.first().tryAdvance(object -> queue.add(Tuple.tuple(polled.first(), object)));
            return polled.second();
        }).takeWhile(Objects::nonNull);
    }
}
