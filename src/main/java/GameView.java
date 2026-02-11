import javax.swing.*;
import java.awt.*;


public class GameView extends JFrame {
    private Game backend;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;

    public GameView(Game backend) {
        this.backend = backend;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Uno");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(new Color(2, 82, 8));
        g.fillRect(0, 0, 1000, 1000);

        Player p = new Player("J");
        p.drawCpuHand(10, 10, g);

    }
}
