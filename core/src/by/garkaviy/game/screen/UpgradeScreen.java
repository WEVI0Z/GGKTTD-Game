package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.test.QuestionEntity;
import by.garkaviy.game.test.TestEntity;
import by.garkaviy.game.test.TestLibrary;
import by.garkaviy.game.texture.TextureLib;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.elements.UIAnswer;
import by.garkaviy.game.ui.elements.UIButton;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class UpgradeScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final GGKTTDGame game;
    private final TestLibrary testLibrary = new TestLibrary();
    private final Random random = new Random();

    private UILayout layout;
    private boolean isEnough = false;
    private boolean isUsed = false;

    UpgradeScreen(GGKTTDGame game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 1000);

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

        layout.render(batch);
        layout.clickAction();

        if (isEnough || isUsed) {
            layout = createLayout();
            isEnough = false;
            isUsed = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            game.setScreen(new GameScreen(game));
        }
    }

    private UILayout createLayout() {
        int screenWidth = 1500;
        int padding = 50;
        final UILayout layout = new UILayout();

        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("Доступные улучшения своей комнаты (100 баллов):")
                .runnable(() -> {})
                .x(50)
                .y(800)
                .width(1400)
                .height(100));

        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("Стены:")
                .runnable(() -> {})
                .x(150)
                .y(650)
                .width(200)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("Окна:")
                .runnable(() -> {})
                .x(150)
                .y(500)
                .width(200)
                .height(100));

        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .texture(TextureLib.WINDOW_1)
                .button(TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_1.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setWindowTexture(TextureLib.WINDOW_1.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(400)
                .y(500)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .button(TextureLib.GRAY_SQUARE)
                .texture(TextureLib.WINDOW_2)
                .runnable(() -> {
                    if (GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_2.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setWindowTexture(TextureLib.WINDOW_2.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(600)
                .y(500)
                .width(100)
                .height(100));

        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("Кровати:")
                .runnable(() -> {})
                .x(130)
                .y(350)
                .width(220)
                .height(100));

        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .texture(TextureLib.BED_1)
                .button(TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getBedTexture().equals(TextureLib.BED_1.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setBedTexture(TextureLib.BED_1.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(400)
                .y(350)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .button(TextureLib.GRAY_SQUARE)
                .texture(TextureLib.BED_2)
                .runnable(() -> {
                    if (GameContext.getInstance().getBedTexture().equals(TextureLib.BED_2.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setBedTexture(TextureLib.BED_2.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(600)
                .y(350)
                .width(100)
                .height(100));

        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .texture(TextureLib.WOOD_FLOOR)
                .button(TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setRoomTexture(TextureLib.WOOD_FLOOR.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(400)
                .y(650)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .button(TextureLib.GRAY_SQUARE)
                .texture(TextureLib.WOOD_FLOOR2)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR2.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setRoomTexture(TextureLib.WOOD_FLOOR2.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(600)
                .y(650)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .texture(TextureLib.WOOD_FLOOR3)
                .button(TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR3.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setRoomTexture(TextureLib.WOOD_FLOOR3.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(800)
                .y(650)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .texture(TextureLib.WOOD_FLOOR4)
                .button(TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR4.getTexture())) {
                        isUsed = true;
                    } else if (GameContext.getInstance().getBalance() < 100 ) {
                        isEnough = true;
                    } else {
                        GameContext.getInstance().setRoomTexture(TextureLib.WOOD_FLOOR4.getTexture());
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() - 100);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    }
                })
                .x(1000)
                .y(650)
                .width(100)
                .height(100));

        if (isUsed) {
            layout.addElement(new UIButton()
                    .borderColor(Color.RED)
                    .title("Улучшение уже используется")
                    .runnable(() -> {})
                    .x(100)
                    .y(100)
                    .width(800)
                    .height(100));
        } else if (isEnough) {
            layout.addElement(new UIButton()
                    .borderColor(Color.RED)
                    .title("Недостаточно баллов")
                    .runnable(() -> {})
                    .x(100)
                    .y(100)
                    .width(500)
                    .height(100));
        }

        return layout;
    }

    private void setMainMenu() {
        dispose();
        game.setScreen(new GameScreen(game));
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
