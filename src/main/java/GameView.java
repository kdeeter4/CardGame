import javax.swing.*;
import java.awt.*;


public class GameView extends JFrame {
    private Game backend;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private boolean instructScreen;

    public GameView(Game backend) {
        this.backend = backend;

        instructScreen = false;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Uno");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void setInstructScreen() {
        instructScreen = !instructScreen;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(2, 82, 8));
        g.fillRect(0, 0, 1000, 1000);

        g.setColor(Color.white);
        g.fillOval(940, 40, 50, 50);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("?", 953, 80);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (instructScreen) {

            g.drawString("Instructions: Each player starts with 7 cards. Every turn, you draw any number of cards " +
                    "from the deck", 10, 100);
            g.drawString("until you can play a card. You are allowed to continue drawing even if you have an " +
                    "available card.", 10, 130);
            g.drawString("You can only play a card if it is the same color or number as the previous card (unless " +
                    "it is a Wild", 10, 160);
            g.drawString("or Draw 4. The goal of the game is end with no cards.", 10, 190);

            g.drawString("Special cards:", 10, 250);
            g.drawString("- Draw 2: The next person must draw 2 cards ", 10, 280);
            g.drawString("- Wild: Lets you choose the color", 10, 310);
            g.drawString("- Draw 4: Wild + makes the next person Draw 4", 10, 340);



            return;
        } else if (backend.checkGameOver()) {
            String s;
            for (Player player : backend.getPlayers()) {
                if (player.getHand().isEmpty()) {
                    s = player.getName();
                    s += " wins";
                }
            }
            s="Game over";
            g.drawString(s, 10, 100);
            return;
        }

        g.setColor(Color.gray);
        g.fillRect(870, 880, 100, 50);
        g.setColor(Color.black);
        g.drawString("Sort", 900, 912);

        g.setColor(Color.gray);
        g.fillRect(870, 820, 100, 50);
        g.setColor(Color.black);
        g.drawString("Draw", 895, 852);

        g.setColor(Color.gray);
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


        backend.getDeck().draw(465, 475, g);
        backend.getPlayers()[0].drawCpu(65, 475, g, 1);
        backend.getPlayers()[1].drawCpu(465, 98, g, 2);
        backend.getPlayers()[2].drawCpu(865, 475, g, 3);
        backend.getPlayers()[3].drawPlayer(500, 825, g);

    }
}
