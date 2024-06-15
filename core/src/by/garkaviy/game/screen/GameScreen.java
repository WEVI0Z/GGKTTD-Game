package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.location.Router;
import by.garkaviy.game.player.Player;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.elements.UIBalance;
import by.garkaviy.game.ui.elements.UIButton;
import by.garkaviy.game.ui.elements.UICameraButton;
import by.garkaviy.game.ui.elements.UIElement;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final GGKTTDGame game;
    private final UILayout layout;

    GameScreen(GGKTTDGame game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        GameContext.getInstance().setPlayer(new Player());
        GameContext.getInstance().getPlayer().setLocation(GameContext.getInstance().getLastX(), GameContext.getInstance().getLastY());

        layout = createLayout();
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

        if (GameContext.getInstance().getRunnableLocation().equals(LocationLibrary.DORM_ROOM)) {
            GameContext.getInstance().getRunnableLocation().getLocation().renderDormRoom(batch);
        } else {
            GameContext.getInstance().getRunnableLocation().getLocation().render(batch);
        }

        Router.route();

        GameContext.getInstance().getPlayer().render(batch, camera);

        if (GameContext.getInstance().isChangeToTest()) {
            GameContext.getInstance().setChangeToTest(false);
            dispose();
            game.setScreen(new TestScreen(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            setMainMenu();
        }

        layout.render(batch, camera);
        layout.clickAction();
    }

    private UILayout createLayout() {
        UIElement exitButton = new UICameraButton(16)
                .borderColor(Color.BLACK)
                .title("Выход")
                .runnable(() -> Gdx.app.exit())
                .x(100)
                .y(100)
                .width(300)
                .height(50);

        UIElement balance = new UIBalance(16)
                .borderColor(Color.BLACK)
                .title("Баланс: 0")
                .runnable(() -> {
                    dispose();
                    game.setScreen(new UpgradeScreen(game));
                    GameContext.getInstance().setLastX(GameContext.getInstance().getPlayer().x);
                    GameContext.getInstance().setLastY(GameContext.getInstance().getPlayer().y);
                })
                .x(100)
                .y(200)
                .width(300)
                .height(50);

        UILayout layout = new UILayout();
        layout.addElement(exitButton);
        layout.addElement(balance);
        return layout;
    }

    private void setMainMenu() {
        dispose();
        game.setScreen(new MainMenuScreen(game));
        GameContext.getInstance().setLastX(GameContext.getInstance().getPlayer().x);
        GameContext.getInstance().setLastY(GameContext.getInstance().getPlayer().y);
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
