package actors;

import engines.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Ball extends GameActor {

    public static final float ANGLE_INIT = 90.0F;
    public static final float ANGLE_MIN = 10.0F;
    public static final float ANGLE_MAX = 170.0F;
    public static final float ANGLE_SPEED = 1.5F;
    public static final float SPEED_MAX = 8.0F;
    public static final float SPEED_MIN = 3.0F;
    public static final float LENGTH = 100.0F;
    public static final int STATE_ROTATE = 0;
    public static final int STATE_SHOOT = 1;
    public static final int STATE_REWIND = 2;

    private int state;
    private float angle;
    private float angleSpeed;
    private float length;
    private float lengthSpeed;
    private boolean empty;
    private int goalIndex;
    private Image goalImage;
    private Color goalColor;
    private int tempIndex;
    private Image tempImage;
    private Color tempColor;
    private AffineTransform transform;

    public Ball() {
        super();
    }

    public int getState() {
        return state;
    }

    public boolean isEmpty() {
        return empty;
    }

    @Override
    public void init() {
        empty = true;
        transform = new AffineTransform();
        angle = ANGLE_INIT;
        width = GameImage.SIZE_OF_BALL;
        height = GameImage.SIZE_OF_BALL;
    }

    @Override
    public void update() {
        updateStateAndPosition();
    }

    private void updateStateAndPosition() {
        switch (state) {
            case STATE_ROTATE:
                rotating();
                break;
            case STATE_SHOOT:
                shooting();
                break;
            case STATE_REWIND:
                rewinding();
            default:
                break;
        }
        x = (float) (GamePanel.GAME_WIDTH / 2 + length * Math.cos(Math.toRadians(angle)));
        y = (float) (length * Math.sin(Math.toRadians(angle)));
    }

    public void resetGoal(ArrayList<Pokemon> pokemons) {
        tempIndex = random.nextInt(pokemons.size());
        Pokemon tempPokemon = pokemons.get(tempIndex);
        tempImage = tempPokemon.image;
        tempColor = tempPokemon.color;
    }

    public void checkCollision(ArrayList<Pokemon> pokemons) {
        if (state == STATE_SHOOT) {
            for (int index = 0; index < pokemons.size(); index++) {
                Pokemon pokemon = pokemons.get(index);
                float pokemonDistance = (float) Math.sqrt(Math.pow(pokemon.x - x, 2)
                        + Math.pow(pokemon.y - y, 2));
                if (pokemonDistance <= pokemon.width && pokemonDistance <= pokemon.height && index == goalIndex) {
                    empty = false;
                    rewind();
                    image = pokemon.image;
                    pokemon.alive = false;
                }
                if (!pokemon.alive) {
                    pokemons.remove(index);
                    pokemons.add(new Pokemon());
                    resetGoal(pokemons);
                    break;
                }
            }
        }
    }

    public void createParticles(ArrayList<Particle> particles) {
        switch (state) {
            case Ball.STATE_ROTATE:
                particles.add(new Particle(x + width / 2, y + height / 2,
                        Particle.SIZE_SMALL, Particle.SPEED_FAST, Particle.LIFE_SHORT, false));
                break;

            case Ball.STATE_SHOOT:
                particles.add(new Particle(x + width / 2, y + height / 2,
                        Particle.SIZE_MEDIUM, Particle.SPEED_NORMAL, Particle.LIFE_NORMAL, false));
                break;

            case Ball.STATE_REWIND:
                if (!empty) {
                    particles.add(new Particle(x + width / 2, y + height / 2,
                            Particle.SIZE_BIG, Particle.SPEED_SLOW, Particle.LIFE_LONG, true));
                } else {
                    particles.add(new Particle(x + width / 2, y + height / 2,
                            Particle.SIZE_BIG, Particle.SPEED_FAST, Particle.LIFE_SHORT, true));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int offset = (GamePanel.GAME_WIDTH - GameImage.SIZE_OF_POKEMON / 2) / 2;
        graphics2D.setColor(goalColor);
        graphics2D.fillOval(offset, 0, GameImage.SIZE_OF_POKEMON, GameImage.SIZE_OF_POKEMON);
        graphics2D.drawImage(goalImage, offset, 0, null);
        int ox = Math.round(x);
        int oy = Math.round(y);
        transform = new AffineTransform();
        transform.translate(ox, oy);
        transform.rotate(Math.toRadians(angle - 90.0F), width / 2, height / 2);
        graphics2D.drawImage(image, transform, null);
    }

    private void shoot() {
        if (state == STATE_ROTATE) {
            state = STATE_SHOOT;
            GameAudio.getInstance().getAudio(GameAudio.KEY_SOUND_SHOOT).play();
        }
    }

    private void shooting() {
        if (x >= 0.0F && x <= GamePanel.GAME_WIDTH && y <= GamePanel.GAME_HEIGHT) {
            length += lengthSpeed;
        } else {
            rewind();
        }
    }

    public void rotate() {
        start = System.currentTimeMillis();
        goalIndex = tempIndex;
        state = STATE_ROTATE;
        length = LENGTH;
        angleSpeed = ANGLE_SPEED;
        lengthSpeed = SPEED_MAX;
        if (!empty) {
            empty = true;
            GameStatus.score++;
            GameAudio.getInstance().getAudio(GameAudio.KEY_SOUND_COIN).play();
        }
        image = GameImage.getInstance().getImage(GameImage.KEY_BALL + random.nextInt(GameImage.NUMBER_OF_BALLS));
        goalImage = tempImage;
        goalColor = tempColor;
    }

    private void rotating() {
        angle += angleSpeed;
        if (angle <= ANGLE_MIN || angle >= ANGLE_MAX) {
            angleSpeed *= -1;
        }
    }

    public void rewind() {
        state = STATE_REWIND;
        if (!empty) {
            angleSpeed = SPEED_MIN;
        } else {
            angleSpeed = SPEED_MAX;
            GameAudio.getInstance().getAudio(GameAudio.KEY_SOUND_REWIND).play();
        }
    }

    private void rewinding() {
        length -= angleSpeed;
        if (length <= LENGTH) {
            rotate();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        shoot();
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
