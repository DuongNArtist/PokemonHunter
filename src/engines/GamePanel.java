package engines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;

    private Graphics2D graphics2D;
    private BufferedImage bufferedImage;
    private GameThread gameThread;
    private GameBoard gameBoard;

    public GamePanel() {
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        bufferedImage = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        gameBoard = new GameBoard();
        gameThread = new GameThread(this);
        gameThread.start();
    }

    @Override
    public void paint(Graphics graphics) {
        gameBoard.update();
        gameBoard.draw(graphics2D);
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
    }

    public void mouseClicked(MouseEvent e) {
        gameBoard.mouseClicked(e);
    }

    public void mouseEntered(MouseEvent e) {
        gameBoard.mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        gameBoard.mouseExited(e);
    }

    public void mousePressed(MouseEvent e) {
        gameBoard.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        gameBoard.mouseReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        gameBoard.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        gameBoard.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {
        gameBoard.keyTyped(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gameBoard.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gameBoard.mouseMoved(e);
    }
}
