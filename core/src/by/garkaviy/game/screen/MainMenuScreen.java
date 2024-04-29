package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.ui.elements.UIButton;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.tools.javac.util.List;

public class MainMenuScreen implements Screen {
    private final GGKTTDGame game;
    private final OrthographicCamera camera;
    private final UIButton playButton;
    private final UIButton settingsButton;
    private final UIButton exitButton;

    public MainMenuScreen(final GGKTTDGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        exitButton = (UIButton) new UIButton()
                .borderColor(Color.BLACK)
                .title("Выход")
                .x(100)
                .y(100)
                .width(300)
                .height(50);

        settingsButton = (UIButton) new UIButton()
                .borderColor(Color.BLACK)
                .title("Настройки")
                .x(100)
                .y(200)
                .width(300)
                .height(50);

        playButton = (UIButton) new UIButton()
                .borderColor(Color.BLACK)
                .title("Играть")
                .x(100)
                .y(300)
                .width(300)
                .height(50);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();
        playButton.render(game.batch);
        settingsButton.render(game.batch);
        exitButton.render(game.batch);

        playButton.clickAction(() -> {
            game.setScreen(new GameScreen(game));
            dispose();
        });
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
