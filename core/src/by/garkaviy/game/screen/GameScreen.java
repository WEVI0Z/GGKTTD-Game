package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.context.SaveAndLoader;
import by.garkaviy.game.location.*;
import by.garkaviy.game.player.Player;
import by.garkaviy.game.texture.TextureLib;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.elements.*;
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
    private final CarController carController;
    private final CarController reversedCarController;
    private final UIElement exitHint;
    private final UIElement bonusHint;
    private final UICameraButton statButton;

    private int fpsCount = 15;

    GameScreen(GGKTTDGame game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        GameContext.getInstance().setPlayer(new Player());
        GameContext.getInstance().getPlayer().setLocation(GameContext.getInstance().getLastX(), GameContext.getInstance().getLastY());

        exitHint = new UICameraButton(16)
                .borderColor(Color.BLACK)
                .title("'E' для использования")
                .runnable(() -> {})
                .x(490)
                .y(440)
                .width(300)
                .height(30);

        bonusHint = new UICameraButton(16)
                .borderColor(Color.BLACK)
                .title("Вы получили 100 бонусных баллов. Вы можете воспользоваться ими при нажатии на кнопку \"Баллы\"")
                .runnable(() -> GameContext.getInstance().setBonusHint(false))
                .x(250)
                .y(150)
                .width(300)
                .height(200);

        statButton = (UICameraButton) new UICameraButton(16)
                .borderColor(Color.BLACK)
                .title("Статистика")
                .texture(TextureLib.HUMAN)
                .runnable(() -> GameContext.getInstance().setStatScreen(true))
                .x(10)
                .y(410)
                .width(60)
                .height(60);

        layout = createLayout();
        carController = new CarController(1100, 80, 120, 80,
                LocationLibrary.OUTSIDE.getLocation().getXTileSize() * Location.TEXTURE_SIZE,
                LocationLibrary.OUTSIDE.getLocation().getYTileSize() * Location.TEXTURE_SIZE, false);
        reversedCarController = new CarController(1100, 10, 120, 80,
                LocationLibrary.OUTSIDE.getLocation().getXTileSize() * Location.TEXTURE_SIZE,
                LocationLibrary.OUTSIDE.getLocation().getYTileSize() * Location.TEXTURE_SIZE, true);
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

        PropsController.render(batch);
        GameContext.getInstance().getPlayer().render(batch, camera);

        if (GameContext.getInstance().isChangeToTest()) {
            GameContext.getInstance().setChangeToTest(false);
            dispose();
            game.setScreen(new TestScreen(game, GameContext.getInstance().getWhatTest()));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && fpsCount == 0) {
            setMainMenu();
        }
        fpsCount = fpsCount > 0 ? fpsCount - 1 : 0;

        if (GameContext.getInstance().getRunnableLocation().equals(LocationLibrary.OUTSIDE)) {
            batch.begin();
            carController.render(batch);
            reversedCarController.render(batch);
            batch.end();
        }

        if (GameContext.getInstance().isExitHint()) {
            exitHint.render(batch, camera);
            GameContext.getInstance().setExitHint(false);
        }

        if (GameContext.getInstance().isBonusHint()) {
            GameContext.getInstance().setBonusHint(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY));
            bonusHint.render(batch, camera);
        }

        statButton.render(batch, camera);
        statButton.clickAction();

        if (GameContext.getInstance().isStatScreen()) {
            dispose();
            game.setScreen(new StatScreen(game));
            GameContext.getInstance().setStatScreen(false);
        }

        layout.render(batch, camera);
        layout.clickAction();
    }

    private UILayout createLayout() {
        UIElement exitButton = new UICameraButton(16)
                .borderColor(Color.BLACK)
                .title("Выход")
                .runnable(() -> {
                    SaveAndLoader.save();
                    Gdx.app.exit();
                })
                .x(10)
                .y(10)
                .width(100)
                .height(30);

        UIElement balance = new UIBalance(16)
                .borderColor(Color.BLACK)
                .title("Баланс: 0")
                .runnable(() -> {
                    dispose();
                    game.setScreen(new UpgradeScreen(game));
                    GameContext.getInstance().setLastX(GameContext.getInstance().getPlayer().x);
                    GameContext.getInstance().setLastY(GameContext.getInstance().getPlayer().y);
                })
                .x(120)
                .y(10)
                .width(150)
                .height(30);

        UIElement uiLocation = new UILocation(16)
                .borderColor(Color.BLACK)
                .runnable(() -> {})
                .x(490)
                .y(10)
                .width(300)
                .height(30);

        UILayout layout = new UILayout();
        layout.addElement(exitButton);
        layout.addElement(balance);
        layout.addElement(uiLocation);
        return layout;
    }

    private void setMainMenu() {
        dispose();
        SaveAndLoader.save();
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
