package screens;

import actors.Pokemon;
import engines.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MenuScreen extends GameScreen {

    private ArrayList<Pokemon> pokemons;
    private ArrayList<GameButton> gameButtons;

    public MenuScreen(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public void init() {
        GameAudio.getInstance().randomMusic();
        image = GameImage.getInstance().randomBackground();
        Pokemon.offset = 0;
        pokemons = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            pokemons.add(new Pokemon());
        }
        gameButtons = new ArrayList<>();
        addScoreButton();
        addNameButton();
        addPlayButton();
        addExitButton();
        addLangButton();
        addMusicButton();
    }

    private void addScoreButton() {
        String[] highs = {GameString.HIGH[0] + GameStatus.high, GameString.HIGH[1] + GameStatus.high};
        GameButton gameButton = new GameButton(highs, GameFont.BOLD_60);
        gameButton.y = 200;
        gameButtons.add(gameButton);
    }


    private void addNameButton() {
        GameButton gameButton = new GameButton(GameString.NAME, GameFont.BOLD_90);
        gameButton.y = 50;
        gameButtons.add(gameButton);
    }

    private void addMusicButton() {
        GameButton gameButton = new GameButton(GameString.MUSIC_OFF, GameFont.ITALIC_30);
        gameButton.y = 650;
        gameButton.setOnClickListener(new GameButton.OnClickListener() {
            @Override
            public void onClick() {
                GameAudio.getInstance().setMute();
                if (GameAudio.getInstance().isMute()) {
                    gameButton.setTexts(GameString.MUSIC_ON);
                } else {
                    gameButton.setTexts(GameString.MUSIC_OFF);
                }
                gameButton.refresh();
                GameAudio.getInstance().randomMusic();
            }
        });
        gameButtons.add(gameButton);
    }

    private void addLangButton() {
        GameButton gameButton = new GameButton(GameString.LANG_ENGLISH, GameFont.ITALIC_30);
        gameButton.y = 600;
        gameButton.setOnClickListener(new GameButton.OnClickListener() {
            @Override
            public void onClick() {
                if (GameString.language == GameString.VIETNAMESE) {
                    GameString.language = GameString.ENGLISH;
                    gameButton.setTexts(GameString.LANG_VIETNAMESE);
                } else {
                    GameString.language = GameString.VIETNAMESE;
                    gameButton.setTexts(GameString.LANG_ENGLISH);
                }
                for (GameButton gameButton1 : gameButtons) {
                    gameButton1.refresh();
                }
            }
        });
        gameButtons.add(gameButton);
    }

    private void addPlayButton() {
        GameButton gameButton = new GameButton(GameString.PLAY, GameFont.PLAIN_60);
        gameButton.y = 350;
        gameButton.setOnClickListener(new GameButton.OnClickListener() {
            @Override
            public void onClick() {
                GameStatus.level = 1;
                gameBoard.changeScreen(new PlayScreen(gameBoard));
            }
        });
        gameButtons.add(gameButton);
    }

    private void addExitButton() {
        GameButton gameButton = new GameButton(GameString.EXIT, GameFont.PLAIN_60);
        gameButton.y = 450;
        gameButton.setOnClickListener(new GameButton.OnClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        });
        gameButtons.add(gameButton);
    }

    @Override
    public void update() {
        for (Pokemon pokemon : pokemons) {
            pokemon.update();
        }
//        for (GameButton gameButton : gameButtons) {
//            gameButton.update();
//        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawBody(graphics2D, false);
        for (Pokemon pokemon : pokemons) {
            pokemon.draw(graphics2D);
        }
        for (GameButton gameButton : gameButtons) {
            gameButton.draw(graphics2D);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameBoard.changeScreen(new PlayScreen(gameBoard));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            gameButton.mouseMoved(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            gameButton.mouseClicked(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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

}
