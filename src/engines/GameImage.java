package engines;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class GameImage {

    public static final float HEIGHT_SCALE = 0.75f;
    public static final int STROKE_MIN = 2;
    public static final int STROKE_MAX = 4;

    public static final int NUMBER_OF_BALLS = 27;
    public static final int NUMBER_OF_POKEMONS = 554;
    public static final int NUMBER_OF_BACKGROUNDS = 680;

    public static final int SIZE_OF_BALL = 48;
    public static final int SIZE_OF_POKEMON = 80;

    public static final String KEY_BALL = "ball_";
    public static final String KEY_POKEMON = "pokemon_";
    public static final String KEY_BACKGROUND = "background_";

    private static GameImage instance;

    private HashMap<String, Image> hashMap = new HashMap();
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private int index;

    private GameImage() {
        for (int index = 0; index < NUMBER_OF_BALLS; ++index) {
            putImage(KEY_BALL + index);
        }
        for (int index = 0; index < NUMBER_OF_POKEMONS; ++index) {
            putImage(KEY_POKEMON + index);
        }
        for (int index = 0; index < NUMBER_OF_BACKGROUNDS; ++index) {
            putImage(KEY_BACKGROUND + index);
        }
    }

    public static GameImage getInstance() {
        if (instance == null) {
            instance = new GameImage();
        }
        return instance;
    }

    private void putImage(String name) {
        Image image = toolkit.getImage(GameImage.class.getClassLoader().getResource("images/" + name + ".png"));
        hashMap.put(name, image);
    }

    public Image getImage(String name) {
        return hashMap.get(name);
    }

    public Image randomBackground() {
        index = new Random().nextInt(NUMBER_OF_BACKGROUNDS);
        return getImage(KEY_BACKGROUND + index);
    }
}
