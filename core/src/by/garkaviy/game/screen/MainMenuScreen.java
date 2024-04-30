package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.elements.UIButton;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    private final GGKTTDGame game;
    private final OrthographicCamera camera;
    private final UILayout layout;

    public MainMenuScreen(final GGKTTDGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

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
        layout.render(game.batch);
        layout.clickAction();
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

    private UILayout createLayout() {
        UIButton exitButton = (UIButton) new UIButton()
                .borderColor(Color.BLACK)
                .title("Выход")
                .runnable(() -> Gdx.app.exit())
                .x(100)
                .y(100)
                .width(300)
                .height(50);

        UIButton settingsButton = (UIButton) new UIButton()
                .borderColor(Color.BLACK)
                .title("Настройки")
                .x(100)
                .y(200)
                .width(300)
                .height(50);

        UIButton playButton = (UIButton) new UIButton()
                .borderColor(Color.BLACK)
                .title("Играть")
                .runnable(() -> game.setScreen(new GameScreen(game)))
                .x(100)
                .y(300)
                .width(300)
                .height(50);

        UILayout layout = new UILayout();
        return layout.addElement(playButton)
                .addElement(settingsButton)
                .addElement(exitButton);
    }
}
