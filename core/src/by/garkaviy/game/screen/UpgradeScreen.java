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
                .button(GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_1) ? TextureLib.BLUE_SQUARE : windowTexture.equals(TextureLib.WINDOW_1) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_1)) {
                        if (!GameContext.getInstance().getWindowTexture().equals(windowTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        windowTexture = TextureLib.WINDOW_1;
                    } else {
                        if (!windowTexture.equals(TextureLib.WINDOW_1)) {
                            if (GameContext.getInstance().getWindowTexture().equals(windowTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    balance -= 100;
                                    isChosen = true;
                                    windowTexture = TextureLib.WINDOW_1;
                                    isEnough = false;
                                }
                            }
                        }
                    }
                })
                .x(400)
                .y(500)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .button(GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_2) ? TextureLib.BLUE_SQUARE : windowTexture.equals(TextureLib.WINDOW_2) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .texture(TextureLib.WINDOW_2)
                .runnable(() -> {
                    if (GameContext.getInstance().getWindowTexture().equals(TextureLib.WINDOW_2)) {
                        if (!GameContext.getInstance().getWindowTexture().equals(windowTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        windowTexture = TextureLib.WINDOW_2;
                    } else {
                        if (!windowTexture.equals(TextureLib.WINDOW_2)) {
                            if (GameContext.getInstance().getWindowTexture().equals(windowTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    balance -= 100;
                                    windowTexture = TextureLib.WINDOW_2;
                                    isChosen = true;
                                    isEnough = false;
                                }
                            }
                        }
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
                .button(GameContext.getInstance().getBedTexture().equals(TextureLib.BED_1) ? TextureLib.BLUE_SQUARE : bedTexture.equals(TextureLib.BED_1) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getBedTexture().equals(TextureLib.BED_1)) {
                        if (!GameContext.getInstance().getBedTexture().equals(bedTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        bedTexture = TextureLib.BED_1;
                    } else {
                        if (!bedTexture.equals(TextureLib.BED_1)) {
                            if (GameContext.getInstance().getBedTexture().equals(bedTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    balance -= 100;
                                    bedTexture = TextureLib.BED_1;
                                    isChosen = true;
                                    isEnough = false;
                                }
                            }
                        }
                    }
                })
                .x(400)
                .y(350)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .button(GameContext.getInstance().getBedTexture().equals(TextureLib.BED_2) ? TextureLib.BLUE_SQUARE : bedTexture.equals(TextureLib.BED_2) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .texture(TextureLib.BED_2)
                .runnable(() -> {
                    if (GameContext.getInstance().getBedTexture().equals(TextureLib.BED_2)) {
                        if (!GameContext.getInstance().getBedTexture().equals(bedTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        bedTexture = TextureLib.BED_2;
                    } else {
                        if (!bedTexture.equals(TextureLib.BED_2)) {
                            if (GameContext.getInstance().getBedTexture().equals(bedTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    balance -= 100;
                                    bedTexture = TextureLib.BED_2;
                                    isChosen = true;
                                    isEnough = false;
                                }
                            }
                        }
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
                .button(GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR) ? TextureLib.BLUE_SQUARE : roomTexture.equals(TextureLib.WOOD_FLOOR) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR)) {
                            if (GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    balance -= 100;
                                    roomTexture = TextureLib.WOOD_FLOOR;
                                    isChosen = true;
                                    isEnough = false;
                                }
                            }
                        }
                    }
                })
                .x(400)
                .y(650)
                .width(100)
                .height(100));
        layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("")
                .button(GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR2) ? TextureLib.BLUE_SQUARE : roomTexture.equals(TextureLib.WOOD_FLOOR2) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .texture(TextureLib.WOOD_FLOOR2)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR2)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR2;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR2)) {
                            if (GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    balance -= 100;
                                    roomTexture = TextureLib.WOOD_FLOOR2;
                                    isChosen = true;
                                    isEnough = false;
                                }
                            }
                        }
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
                .button(GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR3) ? TextureLib.BLUE_SQUARE : roomTexture.equals(TextureLib.WOOD_FLOOR3) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR3)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR3;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR3)) {
                            if (GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    balance -= 100;
                                    roomTexture = TextureLib.WOOD_FLOOR3;
                                    isChosen = true;
                                    isEnough = false;
                                }
                            }
                        }
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
                .button(GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR4) ? TextureLib.BLUE_SQUARE : roomTexture.equals(TextureLib.WOOD_FLOOR4) ? TextureLib.RED_SQUARE : TextureLib.GRAY_SQUARE)
                .runnable(() -> {
                    if (GameContext.getInstance().getRoomTexture().equals(TextureLib.WOOD_FLOOR4)) {
                        if (!GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                            balance += 100;
                            isChosen = true;
                            isEnough = false;
                        }
                        roomTexture = TextureLib.WOOD_FLOOR4;
                    } else {
                        if (!roomTexture.equals(TextureLib.WOOD_FLOOR4)) {
                            if (GameContext.getInstance().getRoomTexture().equals(roomTexture)) {
                                if (GameContext.getInstance().getBalance() + balance < 100 ) {
                                    isEnough = true;
                                    isChosen = false;
                                } else {
                                    roomTexture = TextureLib.WOOD_FLOOR4;
                                    isChosen = true;
                                    isEnough = false;
                                    balance -= 100;
                                }
                            }
                        }
                    }
                })
                .x(1000)
                .y(650)
                .width(100)
                .height(100));

        if (isUsed) {
            isUsed = false;
        } else if (isEnough) {
            layout.addElement(new UIButton()
                    .borderColor(Color.RED)
                    .title("Недостаточно баллов")
                    .runnable(() -> {})
                    .x(950)
                    .y(140)
                    .width(500)
                    .height(100));
            layout.addElement(new UIButton()
                    .borderColor(Color.RED)
                    .title("Сброс")
                    .runnable(() -> {
                        isChosen = false;
                        balance = 0;
                        roomTexture = GameContext.getInstance().getRoomTexture();
                        windowTexture = GameContext.getInstance().getWindowTexture();
                        bedTexture = GameContext.getInstance().getBedTexture();
                        isUsed = true;
                    })
                    .x(1050)
                    .y(20)
                    .width(400)
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
                    .x(750)
                    .y(140)
                    .width(700)
                    .height(100));
            layout.addElement(new UIButton()
                    .borderColor(Color.RED)
                    .title("Сброс")
                    .runnable(() -> {
                        isChosen = false;
                        balance = 0;
                        roomTexture = GameContext.getInstance().getRoomTexture();
                        windowTexture = GameContext.getInstance().getWindowTexture();
                        bedTexture = GameContext.getInstance().getBedTexture();
                        isUsed = true;
                    })
                    .x(1050)
                    .y(20)
                    .width(400)
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
