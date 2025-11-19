import java.util.Scanner;

public class Game {
    Player[] players;
    Deck deck;
    boolean forward;
    boolean gameIsOver;

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
        String[] suits = {"red", "green", "yellow", "blue", "special"};
        int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1};
        deck = new Deck(ranks, suits, values);
    }

    public void playGame() {
        int step = 0;
        int turn;
        Scanner input = new Scanner(System.in);
        while (!gameIsOver) {
            turn = step % this.players.length;


            step++;
        }
    }
    public void printInstructions() {
        //
    }

    public void distribute cards() {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many players: ");
        Game uno = new Game(input.nextInt());
        input.nextLine();
        uno.playGame();
    }
}