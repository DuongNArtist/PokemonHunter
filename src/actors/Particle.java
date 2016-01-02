package actors;

import engines.GameActor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by DuongNArtist on 10/9/2015.
 */
public class Particle extends GameActor {

    public static final int SIZE_SMALL = 2;
    public static final int SIZE_MEDIUM = 4;
    public static final int SIZE_BIG = 6;

    public static final float SPEED_SLOW = 0.75f;
    public static final float SPEED_NORMAL = 1.25f;
    public static final float SPEED_FAST = 1.75f;

    public static final int LIFE_SHORT = 500;
    public static final int LIFE_NORMAL = 750;
    public static final int LIFE_LONG = 1250;

    private int timeLife;
    private long current;
    private float speed;
    private boolean rect;

    public Particle(float x, float y, float size, float speed, int timeLife, boolean rect) {
        super();
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.timeLife = timeLife;
        this.rect = rect;
        this.width = Math.round(size);
        this.height = width;
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
        current = System.currentTimeMillis();
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        if (System.currentTimeMillis() - current >= timeLife) {
            alive = false;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        int bx = Math.round(x - width / 2 - 1);
        int by = Math.round(y - height / 2 - 1);
        if (rect) {
            graphics2D.fillRect(bx, by, width + 2, height + 2);
        } else {
            graphics2D.fillOval(bx, by, width + 2, height + 2);
        }
        graphics2D.setColor(color);
        int ox = Math.round(x - width / 2);
        int oy = Math.round(y - height / 2);
        if (rect) {
            graphics2D.fillRect(ox, oy, width, height);
        } else {
            graphics2D.fillOval(ox, oy, width, height);
        }
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
