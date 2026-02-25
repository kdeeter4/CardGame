import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import java.awt.Color;


public class Game implements MouseListener {
    // Instance Variables
    private final Player[] players;
    private final Deck deck;
    private boolean gameIsOver;
    private Card lastCard;
    private GameView window;
    private int turn;
    private boolean playerMove;
    private boolean pickColorScreen;
    private Color pickedColor;
    private Card selectedCard;

    public boolean getPickColorScreen() {
        return pickColorScreen;
    }


    public boolean getPlayerMove() {
        return playerMove;
    }
    private final int numPlayers = 4;

    public int getTurn() {
        return turn;
    }

    public Card getLastCard() {
        return this.lastCard;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return this.deck;
    }

    // Constructor
    public Game() {
        selectedCard = null;
        pickColorScreen = false;
        playerMove = false;
        gameIsOver = false;
        this.players = new Player[numPlayers];

        Scanner input = new Scanner(System.in);

        // Creates computer players
        for (int i = 0; i < numPlayers - 1; i++) {
            this.players[i] = new Player("Computer " + (i+1), this);
        }

        // Creates human player
        System.out.println("What is your name: ");
        this.players[numPlayers - 1] = new Player(input.nextLine(), this);

        // Creates deck
        String[] ranks = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw2"};
        Color[] suits = {Color.red, Color.green, Color.yellow, Color.blue};
        deck = new Deck(ranks, suits, this);
        lastCard = this.deck.deal();
        turn = -1;
        window = new GameView(this);

        this.window.addMouseListener(this);

    }

    public boolean getGameIsOver() {
        return gameIsOver;
    }

    // Main game loop for entire round
    public void playGame() {
        // Game setup
        this.distributeCards();

        // Variables to keep track of who's turn it is
        advanceTurn();
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

    private void advanceTurn() {

        turn = (turn + 1) % numPlayers;
        window.repaint();

        lastCard = playTurn(turn);
        gameIsOver = checkGameOver();
        window.repaint();


        window.repaint();
    }

    // Main loop for each turn
    public Card playTurn(int turn) {
        window.repaint();

        Player player = this.players[turn];
        Player nextPlayer = this.players[(turn + 1) % this.players.length];
        boolean computerTurn = player.getName().startsWith("Computer ");
        // Human turn instructions
        if (computerTurn) {
            while (true) {
                for (Card card : player.getHand()) {
                    if (this.checkValid(card)) {
                        switch (card.getRank()) {
                            case "Wild" -> card.setSuit(cpuPickColor());
                            case "Draw4" -> {
                                drawCards(4, nextPlayer);
                                card.setSuit(cpuPickColor());
                            }
                            case "Draw2" -> drawCards(2, nextPlayer);
                        }

                        // Prints out the move and plays the card
                        return player.getHand().remove(player.getHand().indexOf(card));
                    }
                }
                // Draw card if no avaliable cards
                player.addCard(this.deck.deal());
            }
        }
        return lastCard;

    }

    // Picking a color when the computer plays a wild or draw 4
    public Color cpuPickColor() {
        Color[] colors = {Color.red, Color.green, Color.yellow, Color.blue};
        return colors[(int) (Math.random() * 4)];
    }

    // Picking a color when the player plays a wild or darw 4


    // Dealing the cards when a draw 4 or draw 2 is played
    public void drawCards(int numCards, Player player) {
        for (int i = 0; i < numCards; i++) {
            player.addCard(this.deck.deal());
        }
    }

    // Checks if the card is avaliable by comparing it to the previous card played
    public boolean checkValid(Card card) {
        if (card.getSuit().equals(Color.white)) {
            return true;
        } else if (card.getRank().equals(this.lastCard.getRank())) {
            return true;
        } else return card.getSuit().equals(this.lastCard.getSuit());
    }


    // Distributes the cards to the player
    public void distributeCards() {
        for (Player player : this.players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(this.deck.deal());
            }
        }
    }

    private boolean clickedInstructions(int x, int y) {
        if (x<990 && x>950 && y < 90 && y > 40) {
            return true;
        }
        return false;
    }

    private boolean clickedSort(int x, int y) {
        if (x<970 && x>870 && y < 930 && y > 880) {
            return true;
        }
        return false;
    }
    public boolean clickedRight(int x, int y) {
        if (x > 815 && x < 845 && y < 925 && y > 825) {
            if ((this.players[numPlayers - 1].getHand().size()-1) > (this.players[numPlayers - 1].getStartIndex()+6)) {
                return true;
            }
        }
        return false;
    }
    public boolean clickedLeft(int x, int y) {
        if (x > 25 && x < 55 && y < 925 && y > 825) {
            if (this.players[numPlayers - 1].getStartIndex()>0) {
                return true;
            }
        }
        return false;
    }

    public boolean clickedDraw(int x, int y) {
        if (x<970 && x > 870 && y < 870 && y > 820) {
            return true;
        }
        return false;
    }

    public int cardClicked(int x, int y) {
        for (int i = 0; i < this.players[numPlayers - 1].getHand().size(); i++) {
            if (this.players[numPlayers-1].getHand().get(i).isClicked(x, y)) {
                return i;
            }
        }
        return -1;
    }

    public void clickedColor(int x, int y) {
        if (y < 765&&y>710) {
            if (x < 445 && x > 405) {
                pickedColor = Color.red;
            } else if (x > 455 && x < 495) {
                pickedColor = Color.yellow;
            } else if (x > 505 && x < 545) {
                pickedColor = Color.green;
            } else if (x > 555 && x < 595) {
                pickedColor = Color.blue;
            }
        }

    }

    public void mouseClicked(MouseEvent e) {



    }

    public void mousePressed(MouseEvent e) {
        int c = this.cardClicked(e.getX(), e.getY());
        if (pickColorScreen) {
            this.clickedColor(e.getX(), e.getY());
            selectedCard.setSuit(pickedColor);
            pickColorScreen = false;
            playerMove = false;
        } else if (playerMove && !pickColorScreen) {

            if (this.clickedDraw(e.getX(), e.getY())) {
                Card addedCard = this.deck.deal();
                this.players[numPlayers - 1].addCard(addedCard);
            }


            else if (c != -1) {
                //turn++;
                window.repaint();
                Card card = this.players[numPlayers-1].getHand().get(c);
                if (this.checkValid(card)) {
                    switch (card.getRank()) {
                        case "Wild":
                            selectedCard = card;
                            pickColorScreen = true;
                            break;
                        case "Draw4":
                            drawCards(4, this.players[0]);
                            selectedCard = card;
                            pickColorScreen = true;
                            break;

                        case "Draw2":
                            drawCards(2, this.players[0]);
                            break;
                    }


                    lastCard = this.players[numPlayers-1].getHand().remove(c);
                    window.repaint();
                    if (!pickColorScreen) {
                        playerMove = false;
                    }



                }

            }
        } else if (!pickColorScreen){


            if (turn < 2 || turn == 3) {
                advanceTurn();
            } else {
                playerMove = true;
                turn++;

            }

        }

        if (this.clickedInstructions(e.getX(), e.getY())) {
            window.setInstructScreen();
        }

        else if (this.clickedSort(e.getX(), e.getY())) {
            this.players[numPlayers - 1].sortHand();
        }

        else if (this.clickedRight(e.getX(), e.getY())) {
            this.players[numPlayers - 1].changeIndex(true);
        }

        else if (this.clickedLeft(e.getX(), e.getY())) {
            this.players[numPlayers - 1].changeIndex(false);
        }

        window.repaint();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    // Main method creates the game object
    public static void main(String[] args) {
        Game uno = new Game();
        uno.playGame();
    }
}