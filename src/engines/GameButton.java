package engines;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class GameButton extends GameActor {

    public static final int SPEED_MIN = 2;
    public static final int SPEED_MAX = 5;

    protected boolean initialized;
    protected Font font;
    protected String[] texts;
    protected FontMetrics fontMetrics;
    protected OnClickListener onClickListener;
    protected float speed;

    public GameButton(String[] texts, Font font) {
        super();
        this.texts = texts;
        this.font = font;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void initMetrics(Graphics2D graphics2D) {
        if (!initialized) {
            width = graphics2D.getFontMetrics(font).stringWidth(texts[GameString.language]);
            height = graphics2D.getFontMetrics(font).getHeight();
            x = (GamePanel.GAME_WIDTH - width) / 2;
            initialized = true;
        }
    }

    public void refresh() {
        initialized = false;
    }

    @Override
    public void init() {
        color = Color.WHITE;
        initialized = false;
        speed = SPEED_MIN + random.nextFloat() * (SPEED_MAX - SPEED_MIN);
        dx = random.nextFloat() * speed;
        dy = random.nextFloat() * speed;
        if (random.nextBoolean()) {
            dx *= -1;
        }
        if (random.nextBoolean()) {
            dy *= -1;
        }
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        if (x <= 0 || x >= GamePanel.GAME_WIDTH) {
            dx *= -1;
        }
        if (y <= 0 || y >= GamePanel.GAME_HEIGHT) {
            dy *= -1;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(font);
        initMetrics(graphics2D);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRoundRect(
                Math.round(x) - height - GameImage.STROKE_MIN,
                Math.round(y) - GameImage.STROKE_MIN,
                width + 2 * height + GameImage.STROKE_MAX,
                height + GameImage.STROKE_MAX,
                height,
                height);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(
                Math.round(x) - height,
                Math.round(y),
                width + 2 * height,
                height,
                height - GameImage.STROKE_MAX,
                height - GameImage.STROKE_MAX);
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.drawString(texts[GameString.language], x, y + height * GameImage.HEIGHT_SCALE);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= x && e.getX() <= x + width &&
                e.getY() >= y && e.getY() <= y + height) {
            color = Color.LIGHT_GRAY;
        } else {
            color = Color.WHITE;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() >= x && e.getX() <= x + width &&
                e.getY() >= y && e.getY() <= y + height) {
            onClickListener.onClick();
        }
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public interface OnClickListener {
        void onClick();
    }
}
