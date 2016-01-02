package engines;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public abstract class GameActor implements GameAction, KeyListener, MouseListener, MouseMotionListener {
    public float x;
    public float y;
    public int width;
    public int height;
    public Image image;
    public float dx;
    public float dy;
    public boolean alive;
    public long start;
    public Random random;
    public Color color;

    public GameActor() {
        start = System.currentTimeMillis();
        random = new Random();
        color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        alive = true;
        init();
    }

    public void drawBody(Graphics2D graphics2D, boolean body) {
        int ox = Math.round(x - width / 2);
        int oy = Math.round(y - height / 2);
        if (body) {
            graphics2D.setColor(color);
            graphics2D.fillOval(ox, oy, width, height);
        }
        graphics2D.drawImage(image, ox, oy, width, height, null);
    }
}
