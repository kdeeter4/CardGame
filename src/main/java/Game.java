import java.util.Scanner;

public class Game {
    // Instance Variables
    private Player[] players;
    private Deck deck;
    private boolean gameIsOver;
    private Card lastCard;

    // Constructor
    public Game(int players) {
        gameIsOver = false;
        this.players = new Player[players];
        Scanner input = new Scanner(System.in);

        // Creates computer players
        for (int i = 0; i < players - 1; i++) {
            this.players[i] = new Player("Computer " + (i+1));
        }

        // Creates human player
        System.out.println("What is your name: ");
        this.players[players - 1] = new Player(input.nextLine());

        // Creates deck
        String[] ranks = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw2"};
        String[] suits = {"red", "green", "yellow", "blue"};
        deck = new Deck(ranks, suits);
        lastCard = this.deck.deal();
    }

    // Main game loop for entire round
    public void playGame() {
        // Game setup
        this.printInstructions();
        this.distributeCards();

        // Variables to keep track of who's turn it is
        int step = 0;
        int turn;

        // Game loop
        while (!gameIsOver) {
            // Determining who's turn it is and then executing the turn
            turn = step % this.players.length;
            this.lastCard = this.playTurn(turn);
            this.gameIsOver = this.checkGameOver();
            step++;
        }
    }

    // Checks if the game is over by looping through each player and checking if their hand is empty
    public boolean checkGameOver() {
        for (Player player : this.players) {
            if (player.getHand().isEmpty()) {
                System.out.println(player.getName() + " wins!");
                return true;
            }
        }
        return false;
    }

    // Main loop for each turn
    public Card playTurn(int turn) {
        Scanner input = new Scanner(System.in);
        Player player = this.players[turn];
        Player nextPlayer = this.players[(turn + 1) % this.players.length];

        // Loop for input
        while (true) {
            boolean computerTurn = player.getName().startsWith("Computer ");

            // Human turn instructions
            if (!computerTurn) {
                // Prompting user for an action, parses the input and then checks if the turn is valid
                System.out.println();
                System.out.println(player.getHand());
                System.out.println("Current card: " + this.lastCard);
                System.out.println("What do you want to do? Either type in 'draw' or 'play [# of Card in array]'");
                String action;
                int index;
                boolean correctFormat;
                do {
                    System.out.println("Play a valid action: ");
                    action = input.nextLine();
                    index = Integer.parseInt(action.split(" ")[1]) - 1;
                    correctFormat = (action.equals("draw") || (action.startsWith("play ")));
                } while (!correctFormat || !this.checkValid(player.getHand().get(index)));

                // Let the player draw a card
                if (action.equals("draw")) {
                    Card addedCard = this.deck.deal();
                    player.addCard(addedCard);
                    System.out.println(addedCard);
                } else {
                    // Perform special card actions
                    Card card = player.getHand().get(Integer.parseInt(action.split(" ")[1]) - 1);
                    if (card.getRank().equals("Wild")) {
                        card.setSuit(pickColor());
                    } else if (card.getRank().equals("Draw4")) {
                        drawCards(4, nextPlayer);
                        card.setSuit(pickColor());
                    } else if (card.getRank().equals("Draw2")) {
                        drawCards(2, nextPlayer);
                    }

                    // Remove the card from the hand and "play" the card
                    return player.getHand().remove(Integer.parseInt(action.split(" ")[1]) - 1);
                }
            } else {
                // Computer actoin which cycles through each of their cards and plays the first valid one
                for (Card card : player.getHand()) {
                    if (this.checkValid(card)) {
                        if (card.getRank().equals("Wild")) {
                            card.setSuit(cpuPickColor());
                        } else if (card.getRank().equals("Draw4")) {
                            drawCards(4, nextPlayer);
                            card.setSuit(cpuPickColor());
                        } else if (card.getRank().equals("Draw2")) {
                            drawCards(2, nextPlayer);
                        }

                        // Prints out the move and plays the card
                        System.out.println(player.getName() + " plays " + card + " with " + (player.getHand().size() - 1) + " cards left.");
                        return player.getHand().remove(player.getHand().indexOf(card));
                    }
                }
                // Draw card if no avaliable cards
                player.addCard(this.deck.deal());
            }



        }
    }

    // Picking a color when the computer plays a wild or draw 4
    public String cpuPickColor() {
        String[] colors = {"red", "green", "yellow", "blue"};
        return colors[(int) (Math.random() * 4)];
    }

    // Picking a color when the player plays a wild or darw 4
    public String pickColor() {
        Scanner input = new Scanner(System.in);
        System.out.println("What color do you want to choose?");
        return input.nextLine();
    }

    // Dealing the cards when a draw 4 or draw 2 is played
    public void drawCards(int numCards, Player player) {
        for (int i = 0; i < numCards; i++) {
            player.addCard(this.deck.deal());
        }
    }

    // Checks if the card is avaliable by comparing it to the previous card played
    public boolean checkValid(Card card) {
        if (card.getSuit().isEmpty()) {
            return true;
        } else if (card.getRank().equals(this.lastCard.getRank())) {
            return true;
        } else return card.getSuit().equals(this.lastCard.getSuit());
    }

    // Prints the instructions
    public void printInstructions() {
        String string = "Instructions:\nEach player starts with 7 cards. Every turn, you draw any number of cards " +
                        "from the deck until you can play a card. You are allowed to continue drawing even if you " +
                        "have an available card.\nYou can only play a card if it is the same color or number as the " +
                        " previous card (unless it is a Wild or Draw 4. The goal of the game is end with no cards." +
                        "\nSpecial cards:\n - Draw 2: The next person must draw 2 cards " +
                        "\n - Wild: Lets you choose the color\n - Draw 4: Wild + makes the next person Draw 4";

        System.out.println(string);
    }

    // Distributes the cards to the player
    public void distributeCards() {
        for (Player player : this.players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(this.deck.deal());
            }
        }
    }

    // Main method creates the game object
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many players: ");
        Game uno = new Game(input.nextInt());
        input.nextLine();
        uno.playGame();
    }
}