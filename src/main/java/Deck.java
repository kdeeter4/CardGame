import java.util.ArrayList;

public class Deck {
    // Instance Variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructors
    public Deck(String[] ranks, String[] suits) {
        // Making the cards
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit));
                cards.add(new Card(ranks[i], suit));

            }
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Card("Wild", ""));
            cards.add(new Card("Draw4", ""));
        }

        cardsLeft = cards.size();
        this.shuffle();
    }

    // Checks if the deck is empty
    public boolean isEmpty() {
        return this.cardsLeft == 0;
    }

    // Returns the number of remaining cards
    public int getCardsLeft() {
        return this.cardsLeft;
    }

    // Removes a card and returns it
    public Card deal() {
        if (this.isEmpty()) {
            return null;
        }

        return this.cards.get(this.cardsLeft-- - 1);
    }

    // Reorders the cards
    public void shuffle() {
        cardsLeft = this.cards.size();

        for (int i = cardsLeft - 1; i > 0; i--) {
            int r = (int) (Math.random() * i + 1);
            Card temp = this.cards.get(i);
            this.cards.set(i, this.cards.get(r));
            this.cards.set(r, temp);

        }
    }
}
