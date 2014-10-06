package pgrela.eulerproblem.problem54.poker;

import java.util.HashMap;
import java.util.Map;

public enum Suit {
    DIAMONDS('D'),
    CLUBS('C'),
    HEARTS('H'),
    SPADES('S');

    final private char code;

    private static final Map<Character, Suit> converterFromChar = new HashMap<>();

    static {
        for (Suit suit : Suit.values())
            converterFromChar.put(suit.code, suit);
    }

    private Suit(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Suit fromChar(char c) {
        return converterFromChar.get(c);
    }
}
