package engines;

import screens.MenuScreen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameBoard implements KeyListener, MouseListener, MouseMotionListener, GameAction {

    private ArrayList<GameScreen> gameScreens;

    public GameBoard() {
        init();
    }

    public void changeScreen(GameScreen gameScreen) {
        gameScreens.add(gameScreen);
        gameScreens.remove(0);
    }

    @Override
    public void init() {
        gameScreens = new ArrayList();
        gameScreens.add(new MenuScreen(this));
    }

    @Override
    public void update() {
        gameScreens.get(0).update();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        gameScreens.get(0).draw(graphics2D);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameScreens.get(0).mouseClicked(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gameScreens.get(0).mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gameScreens.get(0).mouseExited(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameScreens.get(0).mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameScreens.get(0).mouseReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameScreens.get(0).keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameScreens.get(0).keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        gameScreens.get(0).keyTyped(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gameScreens.get(0).mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gameScreens.get(0).mouseMoved(e);
    }
}
