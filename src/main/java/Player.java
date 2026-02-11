import java.awt.*;
import java.util.ArrayList;

public class Player {
    // Instance Variables
    private String name;
    private ArrayList<Card> hand;
    private final int cardWidth = 70;
    private final int cardHeight = 100;

    // Constructors
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    // Adds a card
    public void addCard(Card card) {
        this.hand.add(card);
    }

    // Orders the card for the sort function
    private int orderCard(Card c1, Card c2) {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.red);colors.add(Color.yellow);colors.add(Color.green);colors.add(Color.blue);colors.add(Color.black);
        ArrayList<String> ranks = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            ranks.add(String.valueOf(i));
        }
        ranks.add("Draw2");

        if (colors.indexOf(c2.getSuit()) > colors.indexOf(c1.getSuit())) {
            return -1;
        }

        if (c1.getSuit().equals("")) {
            if (c2.getRank().equals("Wild") && c1.getRank().equals(("Draw4"))) {
                return -1;
            }
        }

        if (ranks.indexOf(c2.getSuit()) < ranks.indexOf(c1.getSuit())) {
            return -1;
        }
        return 1;

    }

    // Sorts the hand with colors and numbers
    public void sortHand() {
        hand.sort((a,b) -> {return this.orderCard(a, b);});
    }

    public void drawCpuHand(int x, int y, Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, cardWidth, cardHeight);

        g.setColor(Color.white);
        g.drawOval(x + 20, y + 50, 20, 20);
        g.drawString(Integer.toString(this.hand.size()), x, y);
    }

}
