import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards;
    int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit, values[i]));
                cards.add(new Card(ranks[i], suit, values[i]));

            }
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Card("Wild", "", 2));
            cards.add(new Card("Draw4", "", 2));
        }
        cardsLeft = cards.size();
        this.shuffle();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isEmpty() {
        return this.cardsLeft == 0;
    }

    public int getCardsLeft() {
        return this.cardsLeft;
    }

    public Card deal() {
        if (this.isEmpty()) {
            return null;
        }

        return this.cards.get(this.cardsLeft-- - 1);
    }

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
