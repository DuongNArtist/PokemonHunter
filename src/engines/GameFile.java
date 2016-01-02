package engines;

import java.io.*;

/**
 * Created by DuongNArtist on 10/15/2015.
 */
public class GameFile {

    public static final String PATH = "D:\\data.epu";
    private static GameFile instance;

    private GameFile() {
        GameStatus.high = readFile();
    }

    public static GameFile getInstance() {
        if (instance == null) {
            instance = new GameFile();
        }
        return instance;
    }

    private boolean isExist() {
        return new File(PATH).exists();
    }

    public void writeFile(int score) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(PATH);
            fileOutputStream.write(score);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int readFile() {
        try {
            InputStream fileInputStream = new FileInputStream(PATH);
            int score = fileInputStream.read();
            fileInputStream.close();
            return score;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
