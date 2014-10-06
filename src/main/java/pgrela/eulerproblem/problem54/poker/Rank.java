package pgrela.eulerproblem.problem54.poker;

import java.util.HashMap;
import java.util.Map;

public enum Rank {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private final char code;

    private static Map<Character, Rank> converterFromChar = new HashMap<>();

    static {
        for (Rank rank : Rank.values())
            converterFromChar.put(rank.code, rank);
    }

    private Rank(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Rank fromChar(char c) {
        return converterFromChar.get(c);
    }
}
