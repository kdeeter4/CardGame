public class Card {
    // Instance Variables
    private String rank;
    private String suit;

    // Constructor
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getters + Setters
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    // String output
    @Override
    public String toString() {
        return this.suit + this.rank;
    }
}
