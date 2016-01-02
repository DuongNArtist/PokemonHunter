package screens;

import actors.Ball;
import actors.Particle;
import actors.Pokemon;
import engines.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlayScreen extends GameScreen {

    private ArrayList<Pokemon> pokemons;
    private ArrayList<Particle> particles;
    private Ball ball;
    private GameStatus gameStatus;

    public PlayScreen(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public void init() {
        image = GameImage.getInstance().randomBackground();
        GameAudio.getInstance().randomMusic();
        gameStatus = new GameStatus();
        particles = new ArrayList<Particle>();
        pokemons = new ArrayList();
        Pokemon.offset = 3 * GameImage.SIZE_OF_POKEMON;
        for (int i = 0; i < GameStatus.level * Pokemon.INIT + GameStatus.goal; ++i) {
            pokemons.add(new Pokemon());
        }
        ball = new Ball();
        ball.resetGoal(pokemons);
        ball.rotate();
    }

    @Override
    public void update() {
        gameStatus.update();
        ball.update();
        ball.createParticles(particles);
        ball.checkCollision(pokemons);
        for (Pokemon pokemon : pokemons) {
            pokemon.update();
        }
        if (GameStatus.timer < 0) {
            gameBoard.changeScreen(new ResultScreen(gameBoard));
        }
        for (int index = 0; index < particles.size(); index++) {
            Particle particle = particles.get(index);
            particle.update();
            if (!particle.alive) {
                particles.remove(index);
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawBody(graphics2D, false);
        gameStatus.draw(graphics2D);
        for (Pokemon pokemon : pokemons) {
            pokemon.draw(graphics2D);
        }
        ball.draw(graphics2D);
        for (Particle particle : particles) {
            particle.draw(graphics2D);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        ball.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameBoard.changeScreen(new MenuScreen(gameBoard));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ball.mouseClicked(e);
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
