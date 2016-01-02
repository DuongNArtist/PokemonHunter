package engines;

public class GameThread extends Thread {

    public static final int FPS = 60;
    public static final int SPF = 1000 / FPS;

    private GamePanel gamePanel;

    public GameThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void run() {
        while (true) {
            long start = System.currentTimeMillis();
            gamePanel.repaint();
            long delta = System.currentTimeMillis() - start;
            long sleep = SPF - delta;
            if (sleep < 0L) {
                sleep = 0L;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
