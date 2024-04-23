package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.ContextContainer;
import by.garkaviy.game.location.LocationBuilder;
import by.garkaviy.game.location.TileEntity;
import by.garkaviy.game.location.TileType;
import by.garkaviy.game.player.Player;
import by.garkaviy.game.player.PlayerBuilder;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class GameScreen implements Screen {
    private final GGKTTDGame game;
    private final OrthographicCamera camera;
    private final Texture playerTexture;
    private final LocationBuilder locationBuilder;
    private final Player player;
    private final ContextContainer contextContainer = new ContextContainer();

    GameScreen(GGKTTDGame game) {
        this.game = game;
        game.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        locationBuilder = LocationBuilder.getInstance()
                .setSize(10, 15)
                .fillWithBackground(TextureLib.COBBLESTONE.getTexture())
                .generateWalls(TextureLib.STONE_WALL.getTexture())
                .placeTile(4, 4, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()));

        playerTexture = TextureLib.PLAYER.getTexture();
        player = PlayerBuilder.getInstance()
                .setXPos(100)
                .setYPos(100)
                .build();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        ScreenUtils.clear(1, 1, 1, 1);

        contextContainer.updateContext(player, locationBuilder.getWalls());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        locationBuilder.render(game.batch);

        game.batch.begin();
        // Отрисовываем игрока
        game.batch.draw(playerTexture, player.x, player.y, player.width, player.height);

        float offset = 200 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.x > 0) {
            player.x -= (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.x + player.width < locationBuilder.getXTileSize() * LocationBuilder.TEXTURE_SIZE) {
            player.x += (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.y + player.height < locationBuilder.getYTileSize() * LocationBuilder.TEXTURE_SIZE) {
            player.y += (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.y > 0) {
            player.y -= (int) offset;
        }

        // Обновляем позицию камеры
        camera.position.set(player.x + player.width / 2, player.y + player.height / 2, 0);
        camera.update();

        contextContainer.checkCollision();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
