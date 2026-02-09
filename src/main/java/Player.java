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

    // Sorts the hand with colors and numbers
    public void sortHand() {
        System.out.println("sort function");
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");colors.add("yellow");colors.add("green");colors.add("blue");colors.add("");
        ArrayList<String> ranks = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            ranks.add(String.valueOf(i));
        }
        ranks.add("Draw2");

        for (int j = 0; j < hand.size() - 1; j++) {
            for (int i = 0; i < hand.size()-1-j; i++) {
                Card c = hand.get(i);
                Card next = hand.get(i+1);

                if (colors.indexOf(next.getSuit()) < colors.indexOf(c.getSuit())) {
                    this.swap(c, next);
                    continue;
                }

                if (c.getSuit().equals("")) {
                    if (next.getRank().equals("Wild") && c.getRank().equals(("Draw4"))) {
                        this.swap(c, next);
                    }
                    continue;
                }

                if (ranks.indexOf(next.getSuit()) < ranks.indexOf(c.getSuit())) {
                    this.swap(c, next);
                }
            }
        }


    }

    // Swaps the cards in hand
    private void swap(Card one, Card two) {
        System.out.println("swapping" + one.toString() + two.toString());
        Card temp = new Card(one.getRank(), one.getSuit());
        this.hand.set(this.hand.indexOf(one), two);
        this.hand.set(this.hand.indexOf(two), temp);
    }
}
