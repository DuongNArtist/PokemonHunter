package actors;

import engines.GameActor;
import engines.GameImage;
import engines.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Pokemon extends GameActor {

    public static final int INIT = 2;
    public static final int TIME = 5000;
    public static final int SPEED = 2;

    public static int offset;

    private long timer;
    private float speed;
    private int index;

    public Pokemon() {
        super();
    }

    private void randomTickTimer() {
        timer = TIME + random.nextInt(TIME);
        start = System.currentTimeMillis();
    }

    private void randomSpeed() {
        speed = SPEED + random.nextInt(SPEED);
        dx = random.nextFloat() * speed;
        dy = random.nextFloat() * speed;
        if (random.nextBoolean()) {
            dx *= -1.0F;
        }
        if (random.nextBoolean()) {
            dy *= -1.0F;
        }
    }

    @Override
    public void init() {
        index = random.nextInt(GameImage.NUMBER_OF_POKEMONS);
        image = GameImage.getInstance().getImage(GameImage.KEY_POKEMON + index);
        width = GameImage.SIZE_OF_POKEMON;
        height = GameImage.SIZE_OF_POKEMON;
        x = random.nextInt(GamePanel.GAME_WIDTH);
        y = offset + random.nextInt(GamePanel.GAME_HEIGHT - offset);
        randomSpeed();
        randomTickTimer();
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        if (x <= 0 || x >= GamePanel.GAME_WIDTH) {
            dx *= -1;
        }
        if (y <= offset || y >= GamePanel.GAME_HEIGHT) {
            dy *= -1;
        }
        if (System.currentTimeMillis() - start >= timer) {
            randomSpeed();
            randomTickTimer();
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawBody(graphics2D, true);
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
