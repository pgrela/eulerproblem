package pgrela.eulerproblem.problem54.poker;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Ordering.from;
import static com.google.common.collect.Ordering.natural;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static pgrela.eulerproblem.problem54.poker.Category.*;
import static pgrela.eulerproblem.problem54.poker.Rank.*;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Ordering;

/**
 * https://gist.github.com/gdejohn/8293321#file-hand-java
 */
public class Hand implements Comparable<Hand> {
    public final Category category;

    private final LinkedList<Rank> distinctRanks = new LinkedList<>();

    public Hand(Set<Card> cards) {
        checkArgument(cards.size() == 5);
        Set<Suit> suits = EnumSet.noneOf(Suit.class);
        Multiset<Rank> ranks = EnumMultiset.create(Rank.class);
        for (Card card : cards) {
            suits.add(card.suit);
            ranks.add(card.rank);
        }
        Set<Entry<Rank>> entries = ranks.entrySet();
        for (Entry<Rank> entry : byCountThenRank.immutableSortedCopy(entries)) {
            distinctRanks.addFirst(entry.getElement());
        }
        Rank first = distinctRanks.getFirst();
        int distinctCount = distinctRanks.size();
        if (distinctCount == 5) {
            boolean flush = suits.size() == 1;
            if (first.ordinal() - distinctRanks.getLast().ordinal() == 4) {
                category = flush ? STRAIGHT_FLUSH : STRAIGHT;
            } else if (first == ACE && distinctRanks.get(1) == FIVE) {
                category = flush ? STRAIGHT_FLUSH : STRAIGHT;
                // ace plays low, move to end
                distinctRanks.addLast(distinctRanks.removeFirst());
            } else {
                category = flush ? FLUSH : HIGH_CARD;
            }
        } else if (distinctCount == 4) {
            category = ONE_PAIR;
        } else if (distinctCount == 3) {
            category = ranks.count(first) == 2 ? TWO_PAIR : THREE_OF_A_KIND;
        } else {
            category = ranks.count(first) == 3 ? FULL_HOUSE : FOUR_OF_A_KIND;
        }
    }

    @Override
    public final int compareTo(Hand that) {
        return byCategoryThenRanks.compare(this, that);
    }

    private static final Ordering<Entry<Rank>> byCountThenRank;

    private static final Comparator<Hand> byCategoryThenRanks;

    static {
        Comparator<Entry<Rank>> byCount = comparingInt(Entry::getCount);
        Comparator<Entry<Rank>> byRank = comparing(Entry::getElement);
        byCountThenRank = from(byCount.thenComparing(byRank));
        Comparator<Hand> byCategory = comparing((Hand hand) -> hand.category);
        Function<Hand, Iterable<Rank>> getRanks =
                (Hand hand) -> hand.distinctRanks;
        Comparator<Hand> byRanks =
                comparing(getRanks, natural().lexicographical());
        byCategoryThenRanks = byCategory.thenComparing(byRanks);
    }

}