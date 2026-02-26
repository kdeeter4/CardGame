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
    public int getStartIndex() {
        return startIndex;
    }

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
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.black);

        if (colors.indexOf(c2.getSuit()) > colors.indexOf(c1.getSuit())) {
            return -1;
        }

        return 1;
    }

    // Sorts the hand with colors and numbers
    public void sortHand() {
        hand.sort((a,b) -> {return this.orderCard(a, b);});
    }

    // Draws the computer icon
    public void drawCpu(int x, int y, Graphics g, int number) {
        // Draws the computer's hand
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

        // Makes the name blue if its the computer's turn
        if (number - 1 == game.getTurn()) {
            g.setColor(Color.blue);
        }

        // Draws the compouters name
        g.drawString("Computer " + number, x-40, y-20);
    }

    // Changes the index for the players hand when scrolling through cards
    public void changeIndex(boolean posneg) {
        if (posneg) {
            startIndex += 7;
        } else {
            startIndex -= 7;
        }
    }

    // Draws out the players hand
    public void drawPlayer(int x, int y, Graphics g) {
        // Determines what section of the cards to show
        int showSize=7;
        if (this.hand.size() - startIndex< 7) {
            showSize = this.hand.size() % 7;
        }

        // If the card is not shown, put it far away (I needed this because I ran into problems when scrolling)
        for (int i = 0; i < this.hand.size(); i++) {
            this.hand.get(i).setX(99999);
            this.hand.get(i).setY(99999);
        }

        // Show the chosen cards
        for (int i = this.startIndex; i < startIndex + showSize; i ++) {
            this.hand.get(i).setX(x - 430 + (i-this.startIndex) * 110);
            this.hand.get(i).setY(y);
            this.hand.get(i).draw(g);
        }

        // Draws player nam and makes it blue if its their turn
        g.setColor(Color.black);
        if (game.getPlayerMove()) {
            g.setColor(Color.blue);
        }
        g.drawString(this.name, x, y+150);
    }

}
