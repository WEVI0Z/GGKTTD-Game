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
                Gdx.app.debug("Collision", "Collision detected with wall at (" + wall.getXCord() + ", " + wall.getYCord() + ")");

                // Определяем, с какой стороны стены произошло столкновение
                float playerCenterX = player.x + player.width / 2;
                float playerCenterY = player.y + player.height / 2;
                float wallCenterX = wallRect.x + wallRect.width / 2;
                float wallCenterY = wallRect.y + wallRect.height / 2;

                float deltaX = playerCenterX - wallCenterX;
                float deltaY = playerCenterY - wallCenterY;

                if (Math.abs(deltaX) > Math.abs(deltaY)) { // Столкновение слева или справа
                    if (deltaX > 0) { // Справа
                        player.x = wallRect.x + wallRect.width;
                    } else { // Слева
                        player.x = wallRect.x - player.width;
                    }
                } else { // Столкновение сверху или снизу
                    if (deltaY > 0) { // Снизу
                        player.y = wallRect.y + wallRect.height;
                    } else { // Сверху
                        player.y = wallRect.y - player.height;
                    }
                }
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
