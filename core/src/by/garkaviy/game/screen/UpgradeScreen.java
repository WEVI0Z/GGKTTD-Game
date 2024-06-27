package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.location.Location;
import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.location.PropsController;
import by.garkaviy.game.test.TestLibrary;
import by.garkaviy.game.texture.TextureLib;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.elements.UIButton;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class UpgradeScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final GGKTTDGame game;
    private final TestLibrary testLibrary = new TestLibrary();
    private final Random random = new Random();
    private final Location location = LocationLibrary.DORM_ROOM.getLocation();

    private UILayout layout;
    private boolean isEnough = false;
    private boolean isUsed = false;
    private boolean isChosen = false;
    private int balance = 0;
    private TextureLib roomTexture = GameContext.getInstance().getRoomTexture();
    private TextureLib windowTexture = GameContext.getInstance().getWindowTexture();
    private TextureLib bedTexture = GameContext.getInstance().getBedTexture();

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

        LocationLibrary library = GameContext.getInstance().getRunnableLocation();
        GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_ROOM);
        TextureLib window = GameContext.getInstance().getWindowTexture();
        GameContext.getInstance().setWindowTexture(windowTexture);
        TextureLib floor = GameContext.getInstance().getRoomTexture();
        GameContext.getInstance().setRoomTexture(roomTexture);
        TextureLib bed = GameContext.getInstance().getBedTexture();
        GameContext.getInstance().setBedTexture(bedTexture);
        location.renderDormRoom(batch);
        PropsController.render(batch);
        GameContext.getInstance().setRunnableLocation(library);
        GameContext.getInstance().setWindowTexture(window);
        GameContext.getInstance().setRoomTexture(floor);
        GameContext.getInstance().setBedTexture(bed);

        layout.render(batch);
        layout.clickAction();

        if (isEnough || isUsed || isChosen) {
            layout = createLayout();
            isEnough = false;
            isUsed = false;
            isChosen = false;
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
                    if (GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_1)) {
                        if (!GameContext.getInstance().getWindowTexture().equals(windowTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        windowTexture = TextureLib.WINDOW_1;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!windowTexture.equals(TextureLib.WINDOW_1)) {
                            balance -= 100;
                        }
                        windowTexture = TextureLib.WINDOW_1;
                        isChosen = true;
                        isEnough = false;
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
                    if (GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_2)) {
                        if (!GameContext.getInstance().getWindowTexture().equals(windowTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        windowTexture = TextureLib.WINDOW_2;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!windowTexture.equals(TextureLib.WINDOW_2)) {
                            balance -= 100;
                        }
                        windowTexture = TextureLib.WINDOW_2;
                        isChosen = true;
                        isEnough = false;
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
                    if (GameContext.getInstance().getBedTexture().equals(TextureLib.BED_1)) {
                        if (!GameContext.getInstance().getBedTexture().equals(bedTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        bedTexture = TextureLib.BED_1;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!bedTexture.equals(TextureLib.BED_1)) {
                            balance -= 100;
                        }
                        bedTexture = TextureLib.BED_1;
                        isChosen = true;
                        isEnough = false;
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
                    if (GameContext.getInstance().getBedTexture().equals(TextureLib.BED_2)) {
                        if (!GameContext.getInstance().getBedTexture().equals(bedTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        bedTexture = TextureLib.BED_1;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!bedTexture.equals(TextureLib.BED_2)) {
                            balance -= 100;
                        }
                        bedTexture = TextureLib.BED_2;
                        isChosen = true;
                        isEnough = false;
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
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR)) {
                            balance -= 100;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR;
                        isChosen = true;
                        isEnough = false;
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
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR2)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR2;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR2)) {
                            balance -= 100;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR2;
                        isChosen = true;
                        isEnough = false;
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
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR3)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR3;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR3)) {
                            balance -= 100;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR3;
                        isChosen = true;
                        isEnough = false;
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
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR4)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR4;
                    } else if (GameContext.getInstance().getBalance() + balance < 100 ) {
                        isEnough = true;
                        isChosen = false;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR4)) {
                            balance -= 100;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR4;
                        isChosen = true;
                        isEnough = false;
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
                    .x(650)
                    .y(250)
                    .width(800)
                    .height(100));
        } else if (isEnough) {
            layout.addElement(new UIButton()
                    .borderColor(Color.RED)
                    .title("Недостаточно баллов")
                    .runnable(() -> {})
                    .x(650)
                    .y(100)
                    .width(500)
                    .height(100));
        } else if (isChosen && balance < 0) {
            layout.addElement(new UIButton()
                    .borderColor(Color.RED)
                    .title("Подтвердить (Цена " + Math.abs(balance) + ")")
                    .runnable(() -> {
                        GameContext.getInstance().setBedTexture(bedTexture);
                        GameContext.getInstance().setWindowTexture(windowTexture);
                        GameContext.getInstance().setRoomTexture(roomTexture);
                        GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() + balance);
                        dispose();
                        game.setScreen(new GameScreen(game));
                    })
                    .x(650)
                    .y(100)
                    .width(700)
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
