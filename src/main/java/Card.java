import java.awt.*;

public class Card {
    // Instance Variables
    private String rank;
    private Color suit;
    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    private final int cardWidth = 70;
    private final int cardHeight = 100;

    // Constructor
    public Card(String rank, Color suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getters + Setters
    public String getRank() {
        return rank;
    }

    public Color getSuit() {
        return suit;
    }

    public void setSuit(Color suit) {
        this.suit = suit;
    }

    // String output
    @Override
    public String toString() {
        return this.suit + this.rank;
    }

    public boolean isClicked(int x, int y) {
        if (x < this.x + cardWidth && x > this.x && y < this.y+cardHeight && y > this.y) {
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(this.suit);
        g.fillRect(x,y,cardWidth,cardHeight);

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 50));


        switch (this.rank) {
            case "Draw2":
                g.drawString("+2", x+7, y+65);
                break;
            case "Draw4":
                g.drawString("+4", x+7, y+65);
                break;
            case "Wild":
                g.drawString("W", x+12, y+65);
                break;
            default:
                g.drawString(this.rank, x+22, y+65);
        }



    }


}
