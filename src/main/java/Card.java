public class Card {
    private String rank;
    private String suit;
    private int value;

    public Card(int value, String suit, String rank) {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }
}
