package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.elements.UIButton;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatScreen implements Screen {
    private final GGKTTDGame game;
    private final UILayout layout;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;

    StatScreen(GGKTTDGame game) {
        batch = new SpriteBatch();
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 1000);
        layout = createLayout();
    }

    @Override
    public void render(float delta) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            setMainMenu();
        }

        layout.render(batch);
        layout.clickAction();
    }

    private UILayout createLayout() {
        UILayout ui = new UILayout();

        ui.addElement(new UIButton(50)
                .title("Статистика")
                .runnable(() -> {})
                .x(500)
                .y(850)
                .width(500)
                .height(100));

        ui.addElement(new UIButton()
                .title("Статистика баллов:")
                .runnable(() -> {})
                .x(50)
                .y(740)
                .width(500)
                .height(50));

        ui.addElement(new UIButton(24)
                .button(null)
                .title("Всего баллов заработано - " + GameContext.getInstance().getScore())
                .runnable(() -> {})
                .align(Align.left)
                .x(50)
                .y(680)
                .width(1000)
                .height(50));

        ui.addElement(new UIButton()
                .title("Статистика тестов:")
                .runnable(() -> {})
                .x(50)
                .y(600)
                .width(500)
                .height(50));

        Map<String, Boolean> tests = GameContext.getInstance().getTests();
        AtomicInteger i = new AtomicInteger();
        AtomicInteger j = new AtomicInteger();
        tests.forEach((title, value) -> {
            ui.addElement(new UIButton(24)
                    .title(title + " - " + (value ? "пройден" : "не пройден"))
                    .borderColor(Color.BLACK)
                    .button(null)
                    .align(Align.left)
                    .runnable(() -> {
                    })
                    .fontColor(value ? Color.GREEN : Color.RED)
                    .width(700)
                    .height(50)
                    .x(50 + j.get() * 700)
                    .y(530 - i.getAndIncrement() * 60));
            if (530 - (i.get() * 60) < 0) {
                i.set(0);
                j.getAndIncrement();
            }
        });

        return ui;
    }

    private void setMainMenu() {
        dispose();
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void show() {

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
