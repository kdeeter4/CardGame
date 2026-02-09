import java.util.ArrayList;

public class Player {
    // Instance Variables
    private String name;
    private ArrayList<Card> hand;

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
}
