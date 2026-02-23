import java.awt.*;
import java.util.ArrayList;

public class Player {
    // Instance Variables
    private String name;
    private ArrayList<Card> hand;
    private final int cardWidth = 70;
    private final int cardHeight = 100;
    private Game game;
    private int startIndex;

    // Constructors
    public Player(String name, Game game) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.game = game;
        startIndex = 0;
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

    public void drawCpu(int x, int y, Graphics g, int number) {
        g.setColor(Color.black);
        g.fillRect(x, y, cardWidth, cardHeight);

        g.setColor(Color.white);
        g.fillOval(x + 35, y + 60, 30, 30);
        g.setColor(Color.black);
        if (this.hand.size()<10) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString(Integer.toString(this.hand.size()), x+42, y+85);
        } else {
            g.setFont(new Font("Arial", Font.BOLD, 23));
            g.drawString(Integer.toString(this.hand.size()), x+37, y+85);

        }
        if (number - 1 == game.getTurn()) {
            g.setColor(Color.blue);
        }
        g.drawString("Computer " + number, x-40, y-20);
    }

    public void changeIndex(boolean posneg) {
        if (posneg) {
            startIndex += 7;
        } else {
            startIndex -= 7;
        }
    }

    public void drawPlayer(int x, int y, Graphics g) {
        int showSize=7;
        if (this.hand.size() - startIndex< 7) {
            showSize = this.hand.size();
        }
        for (int i = this.startIndex; i < startIndex + showSize; i ++) {
            this.hand.get(i).draw(x - 430 + i * 110, y, g);
        }
        g.drawString(this.name, x, y+150);
    }

}
