package engines;

import javax.swing.*;

public class GameFrame extends JFrame {

    public static final String NAME = "Pokemon Hunter";

    public GameFrame() {
        setTitle(NAME);
        setContentPane(new GamePanel());
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
