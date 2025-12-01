import java.util.Scanner;

public class Game {
    Player[] players;
    Deck deck;
    boolean forward;
    boolean gameIsOver;
    Card lastCard;

    public Game(int players) {
        forward = true;
        gameIsOver = false;
        this.players = new Player[players];
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < players - 1; i++) {
            this.players[i] = new Player("Computer " + (i+1));
        }
        System.out.println("What is your name: ");
        this.players[players - 1] = new Player(input.nextLine());
        String[] ranks = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Reverse", "Skip", "Draw2"};
        String[] suits = {"red", "green", "yellow", "blue"};
        int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1};
        deck = new Deck(ranks, suits, values);
        lastCard = this.deck.deal();
    }

    public void playGame() {
        this.printInstructions();
        this.distributeCards();
//      System.out.println(players[players.length - 1]);
        int step = 0;
        int turn;
        while (!gameIsOver) {
            turn = step % this.players.length;
            this.lastCard = this.playTurn(turn);
            this.gameIsOver = this.checkGameOver();
            step++;
        }
    }

    public boolean checkGameOver() {
        for (Player player : this.players) {
            if (player.getHand().isEmpty()) {
                System.out.println(player.getName() + " wins!");
                return true;
            }
        }
        return false;
    }

    public Card playTurn(int turn) {
        Scanner input = new Scanner(System.in);
        Player player = this.players[turn];
        Player nextPlayer = this.players[(turn + 1) % this.players.length];
        while (true) {
            boolean computerTurn = player.getName().startsWith("Computer ");
            if (!computerTurn) {
                System.out.println();
                System.out.println(player.getHand());
                System.out.println("Current card: " + this.lastCard);
                System.out.println("What do you want to do? Either type in 'draw' or 'play [# of Card in array]'");
                String action;
                do {
                    System.out.println("Play a valid action: ");
                    action = input.nextLine();
                } while (!(action.equals("draw") || (action.startsWith("play ") && this.checkValid(player.getHand().get(Integer.parseInt(action.split(" ")[1]) - 1)))));
                if (action.equals("draw")) {
                    Card addedCard = this.deck.deal();
                    player.addCard(addedCard);
                    System.out.println(addedCard);
                } else {
                    Card card = player.getHand().get(Integer.parseInt(action.split(" ")[1]) - 1);
                    if (card.getRank().equals("Wild")) {
                        // pickColor()
                    } else if (card.getRank().equals("Draw4")) {
                        drawCards(4, nextPlayer);
                        // pickColor()
                    } else if (card.getRank().equals("Draw2")) {
                        drawCards(2, nextPlayer);
                    } else if (card.getRank().equals("Skip")) {
                        // skip()
                    } else if (card.getRank().equals("Reverse")) {
                        // reverse()
                    }
                    return player.getHand().remove(Integer.parseInt(action.split(" ")[1]) - 1);

                }
            } else {
                for (Card card : player.getHand()) {
                    if (this.checkValid(card)) {
                        System.out.println(player.getName() + " plays " + card + " with " + player.getHand().size() + " cards left.");
                        return card;
                    }
                }
                player.addCard(this.deck.deal());


            }



        }
    }

    public void drawCards(int numCards, Player player) {
        for (int i = 0; i < numCards; i++) {
            player.addCard(this.deck.deal());
        }
    }

    public boolean checkValid(Card card) {
        if (card.getSuit().isEmpty()) {
            return true;
        } else if (card.getRank().equals(this.lastCard.getRank())) {
            return true;
        } else return card.getSuit().equals(this.lastCard.getSuit());
    }

    public void printInstructions() {
        String string = "Instructions:\nEach player starts with 7 cards. Every turn, you draw any number of cards " +
                        "from the deck until you can play a card. You are allowed to continue drawing even if you " +
                        "have an available card.\nYou can only play a card if it is the same color or number as the " +
                        " previous card (unless it is a Wild or Draw 4. The goal of the game is end with no cards." +
                        "\nSpecial cards:\n - Draw 2: The next person must draw 2 cards " +
                        "(you can stack on draw 2's)\n - Skip: Skips the next person\n - Reverse: Reverses order " +
                        "of play\n - Wild: Lets you choose the color\n - Draw 4: Wild + makes the next person Draw 4";

        System.out.println(string);
    }

    public void distributeCards() {
        for (Player player : this.players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(this.deck.deal());
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many players: ");
        Game uno = new Game(input.nextInt());
        input.nextLine();
        uno.playGame();
    }
}