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

        backend.getDeck().draw(465, 475, g);
        backend.getPlayers()[0].drawCpu(65, 475, g, 1);
        backend.getPlayers()[1].drawCpu(465, 98, g, 2);
        backend.getPlayers()[2].drawCpu(865, 475, g, 3);
        backend.getPlayers()[3].drawPlayer(500, 825, g);

    }
}
