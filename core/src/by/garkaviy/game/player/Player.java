package by.garkaviy.game.player;

import by.garkaviy.game.context.CollisionChecker;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

import java.awt.*;

@Getter
public class Player extends Rectangle {
    private final static int DEFAULT_WIDTH = 50;
    private final static int DEFAULT_HEIGHT = 50;
    private final static int DEFAULT_X_POS = 0;
    private final static int DEFAULT_Y_POS = 0;
    private final static Texture DEFAULT_TEXTURE = TextureLib.PLAYER.getTexture();

    private PlayerState state = PlayerState.FRONT_IDLE;
    private float stateTime = 0f;

    public Texture texture;

    public Player() {
        texture = DEFAULT_TEXTURE;
        x = DEFAULT_X_POS;
        y = DEFAULT_Y_POS;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }

    public void render(Batch batch, Camera camera) {
        batch.begin();
        Player player = GameContext.getInstance().getPlayer();
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = player.getState().getAnimation().getKeyFrame(stateTime, true);
        float scaledWight = player.width * 2.5f;
        float scaledHeight = player.height * 2.5f;
        batch.draw(currentFrame, player.x - 37.5f, player.y - 37.5f, scaledWight, scaledHeight); // Draw current frame at (50, 50)
        player.watchControls(camera);
        CollisionChecker.checkCollision(player, GameContext.getInstance()
                .getRunnableLocation().getLocation().getWalls());
        CollisionChecker.checkActionCollision(player, GameContext.getInstance()
                .getRunnableLocation().getLocation().getActions());
        batch.end();
    }

    public void watchControls(Camera camera) {
        float offset = 200 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= (int) offset;
            state = PlayerState.LEFT_WALKING;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += (int) offset;
            state = PlayerState.RIGHT_WALKING;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += (int) offset;
            state = PlayerState.BACK_WALKING;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= (int) offset;
            state = PlayerState.FRONT_WALKING;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            state = PlayerState.LEFT_WALKING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            state = PlayerState.RIGHT_WALKING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            state = PlayerState.BACK_WALKING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            state = PlayerState.FRONT_WALKING;
        } else {
            switch (state) {
                case LEFT_WALKING:
                    state = PlayerState.LEFT_IDLE;
                    break;
                case RIGHT_WALKING:
                    state = PlayerState.RIGHT_IDLE;
                    break;
                case BACK_WALKING:
                    state = PlayerState.BACK_IDLE;
                    break;
                case FRONT_WALKING:
                    state = PlayerState.FRONT_IDLE;
                    break;
            }
        }

        // Обновляем позицию камеры
        camera.position.set(x + width / 2, y + height / 2, 0);
        camera.update();
    }

}
