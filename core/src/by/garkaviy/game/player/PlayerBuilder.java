package by.garkaviy.game.player;

public class PlayerBuilder {
    public static int DEFAULT_WIDTH = 50;
    public static int DEFAULT_HEIGHT = 50;
    public static int DEFAULT_X_POS = 0;
    public static int DEFAULT_Y_POS = 0;

    private Player player;

    public PlayerBuilder() {
        player = new Player();
        player.x = DEFAULT_X_POS;
        player.y = DEFAULT_Y_POS;
        player.width = DEFAULT_WIDTH;
        player.height = DEFAULT_HEIGHT;
    }

    public static PlayerBuilder getInstance() {
        return new PlayerBuilder();
    }

    public PlayerBuilder setXPos(int xPos) {
        player.x = xPos;

        return this;
    }

    public PlayerBuilder setYPos(int yPos) {
        player.y = yPos;

        return this;
    }

    public PlayerBuilder setWidth(int width) {
        player.width = width;

        return this;
    }

    public PlayerBuilder setHeight(int height) {
        player.height = height;

        return this;
    }

    public Player build() {
        return player;
    }
}
