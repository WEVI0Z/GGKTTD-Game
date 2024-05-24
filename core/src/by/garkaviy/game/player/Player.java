package by.garkaviy.game.player;

import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Player extends Rectangle {
    private final static int DEFAULT_WIDTH = 50;
    private final static int DEFAULT_HEIGHT = 50;
    private final static int DEFAULT_X_POS = 0;
    private final static int DEFAULT_Y_POS = 0;
    private final static Texture DEFAULT_TEXTURE = TextureLib.PLAYER.getTexture();

    public Texture texture;

    public Player() {
        texture = DEFAULT_TEXTURE;
        x = DEFAULT_X_POS;
        y = DEFAULT_Y_POS;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }

    public void watchControls(Camera camera) {
        float offset = 200 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= (int) offset;
        }

        // Обновляем позицию камеры
        camera.position.set(x + width / 2, y + height / 2, 0);
        camera.update();
    }

}
