package engines;

public class GameMain {
    public static void main(String[] args) {
        GameAudio.getInstance();
        GameImage.getInstance();
        GameFile.getInstance();
        new GameFrame();
    }
}

