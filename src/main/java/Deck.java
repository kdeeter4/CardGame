import java.awt.*;
import java.util.ArrayList;

public class Deck {
    // Instance Variables
    private ArrayList<Card> cards;
    private int cardsLeft;
    private final int cardWidth = 70;
    private final int cardHeight = 100;
    private Game game;

    // Constructors
    public Deck(String[] ranks, Color[] suits, Game game) {
        // Making the cards
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            for (Color suit : suits) {
                for (int j = 0; j < 2; j++) {
                    cards.add(new Card(ranks[i], suit));
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            cards.add(new Card("Wild", Color.white));
            cards.add(new Card("Draw4", Color.white));
        }

        cardsLeft = cards.size();
        this.shuffle();
        this.game = game;
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



    // Adds given card back to deck of cards. Increases cardsLeft accordingly
    public void addCard(Card card) {
        cards.add(card);
        cardsLeft++;
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

    public void clear() {
        cards.clear();
    }

    // Draws the deck with the number of cards left and the last card next to it
    public void draw(int x, int y, Graphics g) {
        // Draws the deck of face down cards
        g.setColor(Color.black);
        g.fillRect(x+50, y, cardWidth, cardHeight);

        g.setColor(Color.white);
        g.fillOval(x + 85, y + 60, 30, 30);
        g.setColor(Color.black);

        if (this.getCardsLeft()<10) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString(Integer.toString(this.getCardsLeft()), x+92, y+85);
        } else {
            g.setFont(new Font("Arial", Font.BOLD, 23));
            g.drawString(Integer.toString(this.getCardsLeft()), x+87, y+85);
        }

        // Draws the face-up card
        game.getLastCard().setX(x-50);
        game.getLastCard().setY(y);
        game.getLastCard().draw(g);
    }
}
