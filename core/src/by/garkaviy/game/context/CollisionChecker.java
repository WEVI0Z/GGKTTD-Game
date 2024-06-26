package by.garkaviy.game.context;

import by.garkaviy.game.location.Location;
import by.garkaviy.game.location.TileEntity;
import by.garkaviy.game.location.TileType;
import by.garkaviy.game.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class CollisionChecker {

    private static int timeout = 0;

    public static void checkCollision(Player player, List<TileEntity> walls) {
        walls.forEach(wall -> {
            if (wall.getTileType().equals(TileType.WALL)) {
                checkWallCollision(player, wall);
            }
        });
    }

    public static void checkCollision(Player player, Location location) {
        Rectangle leftWallRect = new Rectangle(location.getXStartPos(), location.getYStartPos(), 10, location.getYTileSize() * Location.TEXTURE_SIZE);
        Rectangle rightWallRect = new Rectangle(location.getXTileSize() * Location.TEXTURE_SIZE, location.getYStartPos(), 10, location.getXTileSize() * Location.TEXTURE_SIZE);
        Rectangle bottomWallRect = new Rectangle(location.getXStartPos(), location.getYStartPos(), location.getXTileSize() * Location.TEXTURE_SIZE, 10);
        Rectangle topWallRect = new Rectangle(location.getXStartPos(), location.getYTileSize() * Location.TEXTURE_SIZE, location.getXTileSize() * Location.TEXTURE_SIZE, 10);

        checkCollisionFromLeft(player, leftWallRect);
        checkCollisionFromRight(player, rightWallRect);
        checkCollisionFromBottom(player, bottomWallRect);
        checkCollisionFromTop(player, topWallRect);
    }

    private static void checkCollisionFromLeft(Player player, Rectangle wallRect) {
        if (player.intersects(wallRect)) {
            player.x = wallRect.x + wallRect.width;
        }
    }

    private static void checkCollisionFromRight(Player player, Rectangle wallRect) {
        if (player.intersects(wallRect)) {
            player.x = wallRect.x - player.width;
        }
    }

    private static void checkCollisionFromBottom(Player player, Rectangle wallRect) {
        if (player.intersects(wallRect)) {
            player.y = wallRect.y + wallRect.height;
        }
    }

    private static void checkCollisionFromTop(Player player, Rectangle wallRect) {
        if (player.intersects(wallRect)) {
            player.y = wallRect.y - player.height;
        }
    }

    public static void checkActionCollision(Player player, List<TileEntity> walls) {
        walls.forEach(wall -> {
            if (wall.getTileType().equals(TileType.ACTION)) {
                checkActionCollision(player, wall);
            }
        });
    }

    public static void checkActionCollision(Player player, TileEntity actionTile) {
        Rectangle wallRect = new Rectangle(actionTile.getXCord() - 10, actionTile.getYCord() - 10,
                actionTile.getWidth() + 20, actionTile.getHeight() + 20);

        if (player.intersects(wallRect) && timeout == 0) {
            Gdx.app.debug("Collision", "Collision detected with action at (" + actionTile.getXCord() + ", " + actionTile.getYCord() + ")");

            GameContext.getInstance().setExitHint(true);
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                if (Objects.nonNull(actionTile.getScript())) {
                    actionTile.getScript().execute();
                    timeout = 30;
                } else {
                    Gdx.app.error("Collision", "No script found for action at (" + actionTile.getXCord() + ", " + actionTile.getYCord() + ")");
                }
            }
        } else {
            if (timeout > 0) {
                timeout--;
            }
        }
    }

    public static void checkWallCollision(Player player, TileEntity wall) {
        Rectangle wallRect = new Rectangle(wall.getXCord(), wall.getYCord(), wall.getWidth(), wall.getHeight());

        if (player.intersects(wallRect)) {
            Gdx.app.debug("Collision", "Collision detected with wall at ("
                    + wall.getXCord() + ", " + wall.getYCord() + ")");

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
