package by.garkaviy.game.context;

import by.garkaviy.game.location.TileEntity;
import by.garkaviy.game.player.Player;
import com.badlogic.gdx.Gdx;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ContextContainer {
    private Player player = new Player();
    private List<TileEntity> walls = new ArrayList<>();

    public ContextContainer() {}

    public void checkCollision() {
        walls.forEach(wall -> {
            Rectangle wallRect = new Rectangle(wall.getXCord(), wall.getYCord(), wall.getWidth(), wall.getHeight());
            if (player.intersects(wallRect)) {
                System.out.println("Collision detected with wall at (" + wall.getXCord() + ", " + wall.getYCord() + ")");
                // Обработка столкновения
                Gdx.app.log("Collision", "Collision detected with wall at (" + wall.getXCord() + ", " + wall.getYCord() + ")");
            }
        });
    }

    public void updateContext(Player player, List<TileEntity> walls) {
        this.player = player;
        this.walls = walls;
    }

    public List<TileEntity> getWalls() {
        return walls;
    }

    public void setWalls(List<TileEntity> walls) {
        this.walls = walls;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
