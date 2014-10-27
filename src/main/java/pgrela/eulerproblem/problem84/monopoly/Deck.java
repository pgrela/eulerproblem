package pgrela.eulerproblem.problem84.monopoly;

import java.util.List;

public class Deck {
    List<Card> cards;
    private int size;
    int front = 0;


    public Deck(List<Card> cards, int size) {
        this.cards = cards;
        this.size = size;
    }

    public Card nextCard() {
        front = (front + 1) % size;
        if (front >= cards.size()) return Card.DO_NOTHING;
        return cards.get(front);
    }
}
