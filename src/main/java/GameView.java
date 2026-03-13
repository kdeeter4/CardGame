import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    // Instance variables
    private Game backend;
    private boolean instructScreen;

    // Constants
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;

    // Constructor
    public GameView(Game backend) {
        this.backend = backend;

        instructScreen = false;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Uno");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Toggles the instruction screen
    public void setInstructScreen() {
        instructScreen = !instructScreen;
    }

    // Paints the scene
    public void paint(Graphics g) {
        // Green background
        g.setColor(new Color(2, 82, 8));
        g.fillRect(0, 0, 1000, 1000);

        // Instruction icon
        g.setColor(Color.white);
        g.fillOval(940, 40, 50, 50);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("?", 953, 80);

        // --- ADD THIS BLOCK: Restart Button ---
        g.setColor(Color.white);
        g.fillOval(10, 40, 50, 50); // Same 50x50 dimensions as the "?" icon
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("R", 22, 80);

        if (instructScreen) {
            // Writes the instruction screen
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Instructions: Each player starts with 7 cards. Every turn, you draw any number of cards " +
                    "from the deck", 10, 120);
            g.drawString("until you can play a card. You are allowed to continue drawing even if you have an " +
                    "available card.", 10, 150);
            g.drawString("You can only play a card if it is the same color or number as the previous card (unless " +
                    "it is a Wild", 10, 180);
            g.drawString("or Draw 4. The goal of the game is end with no cards.", 10, 210);

            g.drawString("Special cards:", 10, 270);
            g.drawString("- Draw 2: The next person must draw 2 cards ", 10, 300);
            g.drawString("- Wild: Lets you choose the color", 10, 330);
            g.drawString("- Draw 4: Wild + makes the next person Draw 4", 10, 360);
            return;
        } else if (backend.getGameIsOver()) {
            // Writes the game over message
            String s;
            s="Game over";
            for (Player player : backend.getPlayers()) {
                if (player.getHand().isEmpty()) {
                    s = player.getName();
                    s += " wins";
                }
            }
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString(s, 100, 700);
        }

        // Creates all the buttons at the same time so it doesn't have to keep switching colors
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.setColor(Color.gray);
        g.fillRect(870, 880, 100, 50);
        g.fillRect(870, 820, 100, 50);

        Polygon rightArrow = new Polygon();
        rightArrow.addPoint(815, 825);
        rightArrow.addPoint(845, 875);
        rightArrow.addPoint(815, 925);
        g.fillPolygon(rightArrow);

        Polygon leftArrow = new Polygon();
        leftArrow.addPoint(55, 825);
        leftArrow.addPoint(25, 875);
        leftArrow.addPoint(55, 925);
        g.fillPolygon(leftArrow);

        g.setColor(Color.black);
        g.drawString("Sort", 900, 912);
        g.drawString("Draw", 895, 852);

        // Draws the deck and players
        backend.getDeck().draw(465, 475, g);
        backend.getPlayers()[0].drawCpu(65, 475, g, 1);
        backend.getPlayers()[1].drawCpu(465, 98, g, 2);
        backend.getPlayers()[2].drawCpu(865, 475, g, 3);
        backend.getPlayers()[3].drawPlayer(500, 825, g);

        // Draws the icon to pick the color
        if (backend.getPickColorScreen()) {
            g.setColor(Color.gray);
            g.fillRect(395, 700, 210, 75);
            g.setColor(Color.red);
            g.fillRect(405, 710, 40, 55);
            g.setColor(Color.yellow);
            g.fillRect(455, 710, 40, 55);
            g.setColor(Color.green);
            g.fillRect(505, 710, 40, 55);
            g.setColor(Color.blue);
            g.fillRect(555, 710, 40, 55);
        }
    }

}
