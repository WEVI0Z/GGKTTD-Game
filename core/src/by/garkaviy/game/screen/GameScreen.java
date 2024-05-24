package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.CollisionChecker;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.location.Router;
import by.garkaviy.game.player.Player;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final GGKTTDGame game;

    GameScreen(GGKTTDGame game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        GameContext.getInstance().setPlayer(new Player());
        GameContext.getInstance().getPlayer().setLocation(100, 100);
        GameContext.getInstance().setRunnableLocation(LocationLibrary.FIRST_TEST_LOCATION);
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

        GameContext.getInstance().getRunnableLocation().getLocation().render(batch);

        Router.route();

        batch.begin();
        Player player = GameContext.getInstance().getPlayer();
        batch.draw(player.texture, player.x, player.y, player.width, player.height);
        player.watchControls(camera);
        CollisionChecker.checkCollision(player, GameContext.getInstance()
                .getRunnableLocation().getLocation().getWalls());
        CollisionChecker.checkActionCollision(player, GameContext.getInstance()
                .getRunnableLocation().getLocation().getActions());
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            setMainMenu();
        }
    }

    private void setMainMenu() {
        dispose();
        game.setScreen(new MainMenuScreen(game));
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
