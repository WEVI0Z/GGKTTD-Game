package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.CollisionChecker;
import by.garkaviy.game.location.LocationBuilder;
import by.garkaviy.game.location.TileEntity;
import by.garkaviy.game.location.TileType;
import by.garkaviy.game.player.Player;
import by.garkaviy.game.player.PlayerBuilder;
import by.garkaviy.game.script.SetScreenScript;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final GGKTTDGame game;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Texture playerTexture;
    private final LocationBuilder locationBuilder;
    private final Player player;

    GameScreen(GGKTTDGame game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        locationBuilder = LocationBuilder.getInstance()
                .setSize(10, 15)
                .fillWithBackground(TextureLib.COBBLESTONE.getTexture())
                .generateWalls(TextureLib.STONE_WALL.getTexture())
                .placeTile(4, 4, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
                .placeAction(9, 9, new TileEntity(TileType.ACTION,
                        TextureLib.ACTION_EXAMPLE.getTexture()),
                        new SetScreenScript(() -> {
                            dispose();
                            game.setScreen(new MainMenuScreen(game));
                        }));

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

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        locationBuilder.render(batch);

        batch.begin();
        // Отрисовываем игрока
        batch.draw(playerTexture, player.x, player.y, player.width, player.height);

        float offset = 200 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.x -= (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.x += (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.y += (int) offset;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.y -= (int) offset;
        }

        // Обновляем позицию камеры
        camera.position.set(player.x + player.width / 2, player.y + player.height / 2, 0);
        camera.update();

        CollisionChecker.checkCollision(player, locationBuilder.getWalls());
        CollisionChecker.checkActionCollision(player, locationBuilder.getActions());

        batch.end();
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
        batch.dispose();
    }
}
