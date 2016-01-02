package screens;

import engines.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ResultScreen extends GameScreen {

    private ArrayList<GameButton> gameButtons;

    public ResultScreen(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public void init() {
        GameAudio.getInstance().randomMusic();
        image = GameImage.getInstance().randomBackground();
        gameButtons = new ArrayList<>();
        addGameMessages();
        addQuitButton();
        addPlayButton();
    }

    private void addPlayButton() {
        String[] texts;
        if (GameStatus.score >= GameStatus.goal) {
            if (GameStatus.score > GameStatus.high) {
                GameStatus.high = GameStatus.score;
                GameFile.getInstance().writeFile(GameStatus.high);
            }
            texts = GameString.NEXT;
            GameStatus.level++;
        } else {
            texts = GameString.REPLAY;
            GameStatus.level = 1;
            GameStatus.score = 0;
        }
        GameButton gameButton = new GameButton(texts, GameFont.ITALIC_30);
        gameButton.y = 400;
        gameButton.setOnClickListener(new GameButton.OnClickListener() {
            @Override
            public void onClick() {
                changeScreen(new PlayScreen(gameBoard));
            }
        });
        gameButtons.add(gameButton);
    }

    private void addQuitButton() {
        GameButton gameButton = new GameButton(GameString.QUIT, GameFont.ITALIC_30);
        gameButton.y = 500;
        gameButton.setOnClickListener(new GameButton.OnClickListener() {
            @Override
            public void onClick() {
                GameStatus.level = 1;
                gameBoard.changeScreen(new MenuScreen(gameBoard));
            }
        });
        gameButtons.add(gameButton);
    }

    private void addGameMessages() {
        String[] messages = {GameString.YOUR_SCORE[0] + GameStatus.score, GameString.YOUR_SCORE[1] + GameStatus.score};
        GameButton gameButton = new GameButton(messages, GameFont.ITALIC_60);
        gameButton.y = 200;
        gameButtons.add(gameButton);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawBody(graphics2D, false);
        for (GameButton gameButton : gameButtons) {
            gameButton.draw(graphics2D);
        }
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.fillRect(0, 0, GamePanel.GAME_WIDTH, GameImage.SIZE_OF_POKEMON);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(GameFont.PLAIN_30);
        graphics2D.drawString("Score: " + GameStatus.score, 20, 30);
        graphics2D.drawString("Goal: " + GameStatus.goal, 20, 70);
        graphics2D.drawString("Timer: " + GameStatus.timer, 1100, 30);
        graphics2D.drawString("Level: " + GameStatus.level, 1100, 70);
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
        for (GameButton gameButton : gameButtons) {
            gameButton.mouseClicked(e);
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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            gameButton.mouseMoved(e);
        }
    }
}
