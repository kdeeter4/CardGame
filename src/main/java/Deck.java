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
    // Removes a card and returns it. If the deck is empty, it refills it first.
    public Card deal() {
        if (this.isEmpty()) {
            // Refill the deck with a brand new set of cards
            String[] ranks = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw2"};
            Color[] suits = {Color.red, Color.green, Color.yellow, Color.blue};

            this.cards.clear();

            // Re-make the standard colored cards
            for (int i = 0; i < ranks.length; i++) {
                for (Color suit : suits) {
                    for (int j = 0; j < 2; j++) {
                        this.cards.add(new Card(ranks[i], suit));
                    }
                }
            }

            // Re-make the wild cards
            for (int i = 0; i < 3; i++) {
                this.cards.add(new Card("Wild", Color.white));
                this.cards.add(new Card("Draw4", Color.white));
            }

            // Reset the card count and shuffle the new deck
            this.cardsLeft = this.cards.size();
            this.shuffle();
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
