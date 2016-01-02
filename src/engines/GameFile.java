package engines;

import java.io.*;

public class GameFile {

    public static final String PATH = System.getProperty("user.dir") + File.separator + "data.epu";
    private static GameFile instance;

    private GameFile() {
        System.out.print(PATH);
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
        int score = 0;
        try {
            File file = new File((PATH));
            if (file.exists()) {
                InputStream fileInputStream = new FileInputStream(PATH);
                score = fileInputStream.read();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }

}
