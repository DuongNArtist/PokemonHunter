package engines;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameStatus extends GameActor {

    public static final int TIMER_INIT = 60;
    public static final int GOAL_INIT = 3;

    public static int goal;
    public static int timer;
    public static int level;
    public static int score;
    public static int high;

    @Override
    public void init() {
        start = System.currentTimeMillis();
        timer = TIMER_INIT;
        goal = GameStatus.level * GOAL_INIT;
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - start >= 1000) {
            timer--;
            start = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.fillRect(0, 0, GamePanel.GAME_WIDTH, GameImage.SIZE_OF_POKEMON);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(GameFont.PLAIN_30);
        graphics2D.drawString(GameString.SCORE[GameString.language] + GameStatus.score, 20, 30);
        graphics2D.drawString(GameString.GOAL[GameString.language] + goal, 20, 70);
        graphics2D.drawString(GameString.TIMER[GameString.language] + timer, 1100, 30);
        graphics2D.drawString(GameString.LEVEL[GameString.language] + +GameStatus.level, 1100, 70);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
