package pgrela.eulerproblem.problem54.poker;

import static pgrela.eulerproblem.problem54.poker.Rank.*;
import static pgrela.eulerproblem.problem54.poker.Suit.*;

import java.util.HashMap;
import java.util.Map;

public enum Card {
    TWO_DIAMONDS(TWO, DIAMONDS),
    THREE_DIAMONDS(THREE, DIAMONDS),
    FOUR_DIAMONDS(FOUR, DIAMONDS),
    FIVE_DIAMONDS(FIVE, DIAMONDS),
    SIX_DIAMONDS(SIX, DIAMONDS),
    SEVEN_DIAMONDS(SEVEN, DIAMONDS),
    EIGHT_DIAMONDS(EIGHT, DIAMONDS),
    NINE_DIAMONDS(NINE, DIAMONDS),
    TEN_DIAMONDS(TEN, DIAMONDS),
    JACK_DIAMONDS(JACK, DIAMONDS),
    QUEEN_DIAMONDS(QUEEN, DIAMONDS),
    KING_DIAMONDS(KING, DIAMONDS),
    ACE_DIAMONDS(ACE, DIAMONDS),

    TWO_CLUBS(TWO, CLUBS),
    THREE_CLUBS(THREE, CLUBS),
    FOUR_CLUBS(FOUR, CLUBS),
    FIVE_CLUBS(FIVE, CLUBS),
    SIX_CLUBS(SIX, CLUBS),
    SEVEN_CLUBS(SEVEN, CLUBS),
    EIGHT_CLUBS(EIGHT, CLUBS),
    NINE_CLUBS(NINE, CLUBS),
    TEN_CLUBS(TEN, CLUBS),
    JACK_CLUBS(JACK, CLUBS),
    QUEEN_CLUBS(QUEEN, CLUBS),
    KING_CLUBS(KING, CLUBS),
    ACE_CLUBS(ACE, CLUBS),

    TWO_HEARTS(TWO, HEARTS),
    THREE_HEARTS(THREE, HEARTS),
    FOUR_HEARTS(FOUR, HEARTS),
    FIVE_HEARTS(FIVE, HEARTS),
    SIX_HEARTS(SIX, HEARTS),
    SEVEN_HEARTS(SEVEN, HEARTS),
    EIGHT_HEARTS(EIGHT, HEARTS),
    NINE_HEARTS(NINE, HEARTS),
    TEN_HEARTS(TEN, HEARTS),
    JACK_HEARTS(JACK, HEARTS),
    QUEEN_HEARTS(QUEEN, HEARTS),
    KING_HEARTS(KING, HEARTS),
    ACE_HEARTS(ACE, HEARTS),

    TWO_SPADES(TWO, SPADES),
    THREE_SPADES(THREE, SPADES),
    FOUR_SPADES(FOUR, SPADES),
    FIVE_SPADES(FIVE, SPADES),
    SIX_SPADES(SIX, SPADES),
    SEVEN_SPADES(SEVEN, SPADES),
    EIGHT_SPADES(EIGHT, SPADES),
    NINE_SPADES(NINE, SPADES),
    TEN_SPADES(TEN, SPADES),
    JACK_SPADES(JACK, SPADES),
    QUEEN_SPADES(QUEEN, SPADES),
    KING_SPADES(KING, SPADES),
    ACE_SPADES(ACE, SPADES);

    public final Rank rank;

    public final Suit suit;

    private final String code;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        code = rank.getCode()+String.valueOf(suit.getCode());
    }

    private static Map<String, Card> converterFromString = new HashMap<>();

    static {
        for (Card card : Card.values())
            converterFromString.put(card.getCode(), card);
    }

    public static Card fromString(String code) {
        return converterFromString.get(code);
    }
    public String getCode() {
        return code;
    }
}
