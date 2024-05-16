package by.garkaviy.game.context;

import by.garkaviy.game.location.TileEntity;
import by.garkaviy.game.location.TileType;
import by.garkaviy.game.player.Player;
import com.badlogic.gdx.Gdx;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class CollisionChecker {

    public static void checkCollision(Player player, List<TileEntity> walls) {
        walls.forEach(wall -> {
            if (wall.getTileType().equals(TileType.WALL)) {
                checkWallCollision(player, wall);
            }
        });
    }

    public static void checkActionCollision(Player player, List<TileEntity> walls) {
        walls.forEach(wall -> {
            if (wall.getTileType().equals(TileType.ACTION)) {
                checkActionCollision(player, wall);
            }
        });
    }

    public static void checkActionCollision(Player player, TileEntity actionTile) {
        Rectangle wallRect = new Rectangle(actionTile.getXCord(), actionTile.getYCord(),
                actionTile.getWidth(), actionTile.getHeight());

        if (player.intersects(wallRect)) {
            Gdx.app.debug("Collision", "Collision detected with action at (" + actionTile.getXCord() + ", " + actionTile.getYCord() + ")");
            if (Objects.nonNull(actionTile.getScript())) {
                actionTile.getScript().execute();
            } else {
                Gdx.app.error("Collision", "No script found for action at (" + actionTile.getXCord() + ", " + actionTile.getYCord() + ")");
            }
        }
    }

    public static void checkWallCollision(Player player, TileEntity wall) {
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
    }
}
