package engines;

public abstract class GameScreen extends GameActor {

    protected GameBoard gameBoard;

    public GameScreen(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        x = GamePanel.GAME_WIDTH / 2;
        y = GamePanel.GAME_HEIGHT / 2;
        width = GamePanel.GAME_WIDTH;
        height = GamePanel.GAME_HEIGHT;
    }

    public void changeScreen(GameScreen gameScreen) {
        gameBoard.changeScreen(gameScreen);
    }
}
