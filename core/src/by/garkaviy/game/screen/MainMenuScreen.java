package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.context.SaveAndLoader;
import by.garkaviy.game.test.TestLibrary;
import by.garkaviy.game.texture.TextureLib;
import by.garkaviy.game.ui.FontUtils;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.elements.UIButton;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

public class MainMenuScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final UILayout layout;
    private final GGKTTDGame game;
    private final BitmapFont header;
    private final Stage stage = new Stage(new ScreenViewport());
    private UILayout loadLayout = new UILayout();
    private UILayout saveLayout = new UILayout();
    private boolean isLoadOpened = false;
    private boolean isSaveOpened = false;
    private boolean isTextFieldOpened = false;
    private boolean isPasswordFieldOpened = false;
    private boolean isPasswordConfirmFieldOpened = false;
    private String title;
    private TextField textField;
    private UILayout textFieldLayout = new UILayout().addElement(new UIButton()
                    .title("Название:")
                    .borderColor(Color.BLACK)
                    .runnable(() -> {
                    })
                    .button(null)
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 550))
                    .y(Gdx.graphics.getHeight() - 300)
                    .width(400)
                    .height(50))
            .addElement(new UIButton()
                    .title("Ок")
                    .borderColor(Color.BLACK)
                    .runnable(() -> {
                        title = textField.getText();
                        isTextFieldOpened = false;
                        stage.clear();
                        createTextField();
                        isPasswordFieldOpened = true;
                    })
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 400))
                    .y(Gdx.graphics.getHeight() - 500)
                    .width(100)
                    .height(50));
    private UILayout passwordLayout = new UILayout().addElement(new UIButton()
                    .title("Пароль:")
                    .borderColor(Color.BLACK)
                    .runnable(() -> {
                    })
                    .button(null)
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 550))
                    .y(Gdx.graphics.getHeight() - 300)
                    .width(400)
                    .height(50))
            .addElement(new UIButton()
                    .title("Ок")
                    .borderColor(Color.BLACK)
                    .runnable(() -> {
                        saveLayout = createSaveLayout(title, textField.getText());
                        isSaveOpened = true;
                        isPasswordFieldOpened = false;
                        stage.clear();
                    })
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 250))
                    .y(Gdx.graphics.getHeight() - 500)
                    .width(100)
                    .height(50));
    private UILayout passwordConfirmLayout = new UILayout().addElement(new UIButton()
                    .title("Введите пароль:")
                    .borderColor(Color.BLACK)
                    .runnable(() -> {
                    })
                    .button(null)
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 550))
                    .y(Gdx.graphics.getHeight() - 300)
                    .width(400)
                    .height(50))
            .addElement(new UIButton()
                    .title("Ок")
                    .borderColor(Color.BLACK)
                    .runnable(() -> {
                        if (textField.getText().equals(GameContext.getInstance().getPassword())) {
                            setGameScreen();
                        }
                    })
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 250))
                    .y(Gdx.graphics.getHeight() - 500)
                    .width(100)
                    .height(50));

    public MainMenuScreen(GGKTTDGame game) {
        this.game = game;
        Gdx.input.setInputProcessor(stage);
        this.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        header = FontUtils.getFont(50, Color.BLACK);
        layout = createLayout();
//        TestLibrary.saveTests();
    }

    private void setGameScreen() {
        game.setScreen(new GameScreen(game));
        dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();

        batch.begin();
        batch.draw(TextureLib.BOOK.getTexture(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(TextureLib.COLLEGE_IMAGE.getTexture(), (float) (Gdx.graphics.getWidth() * 0.1), (float) (Gdx.graphics.getHeight() * 0.45), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        batch.draw(TextureLib.COLLEGE_IMAGE_2.getTexture(), (float) (Gdx.graphics.getWidth() * 0.1), (float) (Gdx.graphics.getHeight() * 0.05), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        header.draw(batch, "«Жизнь в колледже»", (float) (Gdx.graphics.getWidth() * 0.53), (float) (Gdx.graphics.getHeight() * 0.7), (float) (Gdx.graphics.getWidth() / 2.5), Align.center, true);
        batch.end();

        layout.render(batch);
        layout.clickAction();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            isLoadOpened = false;
            isSaveOpened = false;
            isTextFieldOpened = false;
            isPasswordConfirmFieldOpened = false;
            isPasswordFieldOpened = false;
            stage.clear();
        }

        if (isLoadOpened) {
            batch.begin();
            batch.draw(TextureLib.GREY_FLAT_BUTTON.getTexture(), (float) (Gdx.graphics.getWidth() * 0.33), (float) (Gdx.graphics.getHeight() * 0.14), Gdx.graphics.getWidth() / 3, (float) (Gdx.graphics.getHeight() / 1.5));
            batch.end();
            loadLayout.render(batch);
            loadLayout.clickAction();
        }

        if (isSaveOpened) {
            batch.begin();
            batch.draw(TextureLib.GREY_FLAT_BUTTON.getTexture(), (float) (Gdx.graphics.getWidth() * 0.33), (float) (Gdx.graphics.getHeight() * 0.14), Gdx.graphics.getWidth() / 3, (float) (Gdx.graphics.getHeight() / 1.5));
            batch.end();
            saveLayout.render(batch);
            saveLayout.clickAction();
        }

        if (isTextFieldOpened) {
            batch.begin();
            batch.draw(TextureLib.GREY_FLAT_BUTTON.getTexture(), (float) (Gdx.graphics.getWidth() * 0.33), (float) (Gdx.graphics.getHeight() * 0.44), Gdx.graphics.getWidth() / 3, (float) (Gdx.graphics.getHeight() / 3));
            batch.end();
            textFieldLayout.render(batch);
            textFieldLayout.clickAction();
            stage.act(delta); // Обновление всех актеров на сцене
            stage.draw();
        }

        if (isPasswordFieldOpened) {
            batch.begin();
            batch.draw(TextureLib.GREY_FLAT_BUTTON.getTexture(), (float) (Gdx.graphics.getWidth() * 0.33), (float) (Gdx.graphics.getHeight() * 0.44), Gdx.graphics.getWidth() / 3, (float) (Gdx.graphics.getHeight() / 3));
            batch.end();
            passwordLayout.render(batch);
            passwordLayout.clickAction();
            stage.act(delta); // Обновление всех актеров на сцене
            stage.draw();
        }

        if (isPasswordConfirmFieldOpened) {
            batch.begin();
            batch.draw(TextureLib.GREY_FLAT_BUTTON.getTexture(), (float) (Gdx.graphics.getWidth() * 0.33), (float) (Gdx.graphics.getHeight() * 0.44), Gdx.graphics.getWidth() / 3, (float) (Gdx.graphics.getHeight() / 3));
            batch.end();
            passwordConfirmLayout.render(batch);
            passwordConfirmLayout.clickAction();
            stage.act(delta); // Обновление всех актеров на сцене
            stage.draw();
        }
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

    private void createTextField() {
        // Создание TextFieldStyle с кастомным шрифтом
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = FontUtils.getFont(30, Color.BLACK);
        textFieldStyle.fontColor = Color.BLACK;

        TextureRegion cursor = new TextureRegion(TextureLib.TEXT_MARKER.getTexture());
        textFieldStyle.cursor = new TextureRegionDrawable(cursor);

        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(TextureLib.BLUE_BUTTON.getTexture()));
        textFieldStyle.focusedBackground = new TextureRegionDrawable(new TextureRegion(TextureLib.RED_BUTTON.getTexture()));

        TextField textField = new TextField("", textFieldStyle);

        textField.setSize(400, 50);
        textField.setPosition((float) (Gdx.graphics.getWidth() * 0.73 - 550), Gdx.graphics.getHeight() - 400);
        textField.setTextFieldListener((field, c) -> {
            // Добавление внутренних отступов (например, добавление пробелов)
            textField.setText("  " + textField.getText().strip() + "  ");
            textField.setCursorPosition(textField.getText().length() - 2);
        });
// Добавление TextField на сцену (Stage)

        this.textField = textField;
        stage.addActor(textField);
    }

    private UILayout createLayout() {
        UILayout layout = new UILayout();

        int counter = 0;
        if (GameContext.checkInstance()) {
            layout.addElement(new UIButton()
                    .borderColor(Color.BLACK)
                    .title("Продолжить")
                    .runnable(() -> {
                        if (!GameContext.checkInstance()) return;
                        dispose();
                        SaveAndLoader.load();
                        game.setScreen(new GameScreen(game));
                    })
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 150))
                    .y(Gdx.graphics.getHeight() - 500)
                    .width(300)
                    .height(50));

            counter++;
        }

        layout.addElement(new UIButton()
                        .borderColor(Color.BLACK)
                        .title("Новая игра")
                        .runnable(() -> {
                            createTextField();
                            isTextFieldOpened = true;
                        })
                        .x((int) (Gdx.graphics.getWidth() * 0.73 - 150))
                        .y(Gdx.graphics.getHeight() - 500 - counter++ * 100)
                        .width(300)
                        .height(50))
                .addElement(new UIButton()
                        .borderColor(Color.BLACK)
                        .title("Загрузить")
                        .runnable(() -> {
                            loadLayout = createLoadLayout();
                            isLoadOpened = true;
                        })
                        .x((int) (Gdx.graphics.getWidth() * 0.73 - 150))
                        .y(Gdx.graphics.getHeight() - 500 - counter++ * 100)
                        .width(300)
                        .height(50))
                .addElement(new UIButton()
                        .borderColor(Color.BLACK)
                        .title("Выход")
                        .runnable(() -> {
                            SaveAndLoader.save();
                            Gdx.app.exit();
                        })
                        .x((int) (Gdx.graphics.getWidth() * 0.73 - 150))
                        .y(Gdx.graphics.getHeight() - 500 - counter++ * 100)
                        .width(300)
                        .height(50))
                .addElement(new UIButton()
                        .borderColor(Color.BLACK)
                        .title("Редактировать тесты")
                        .runnable(() -> {
                            game.setScreen(new CreateScreen(game));
                            dispose();
                        })
                        .x((int) (Gdx.graphics.getWidth() * 0.73 - 250))
                        .y(Gdx.graphics.getHeight() - 930)
                        .width(500)
                        .height(50));

        return layout;
    }

    private UILayout createLoadLayout() {
        List<String> names = SaveAndLoader.loadSaves();
        UILayout ui = new UILayout();

        ui.addElement(new UIButton()
                .title("Сохранения:")
                .button(null)
                .x((int) (Gdx.graphics.getWidth() * 0.73 - 550))
                .y(Gdx.graphics.getHeight() - 300)
                .width(400)
                .height(50));

        for (int i = 0; i < 5; i++) {
            String title = names.size() > i ? names.get(i) : "Пусто";
            ui.addElement(new UIButton()
                    .title(title)
                    .runnable(() -> {
                        Gdx.app.log("Load", title);
                        if (title.equals("Пусто")) return;
                        SaveAndLoader.load(title);
                        isPasswordConfirmFieldOpened = true;
                        isLoadOpened = false;
                        stage.clear();
                        createTextField();
//                        game.setScreen(new GameScreen(game));
//                        dispose();
                    })
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 550))
                    .y((Gdx.graphics.getHeight() - 400) - (100 * i))
                    .width(400)
                    .height(50));
        }

        return ui;
    }

    private UILayout createSaveLayout(String newTitle, String password) {
        List<String> names = SaveAndLoader.loadSaves();
        UILayout ui = new UILayout();

        ui.addElement(new UIButton()
                .title("Сохранить в:")
                .button(null)
                .x((int) (Gdx.graphics.getWidth() * 0.73 - 550))
                .y(Gdx.graphics.getHeight() - 300)
                .width(400)
                .height(50));

        for (int i = 0; i < 5; i++) {
            String title = names.size() > i ? names.get(i) : "Пусто";
            ui.addElement(new UIButton()
                    .borderColor(Color.BLACK)
                    .title(title)
                    .runnable(() -> {
                        Gdx.app.log("Save", title);
                        if (!title.equals("Пусто")) SaveAndLoader.delete(title);
                        GameContext.setInstance(GameContext.getDefaultInstance());
                        GameContext.getInstance().setSaveName(newTitle);
                        GameContext.getInstance().setPassword(password);
                        SaveAndLoader.save();
                        dispose();
                        SaveAndLoader.load();
                        game.setScreen(new GameScreen(game));
                    })
                    .x((int) (Gdx.graphics.getWidth() * 0.73 - 550))
                    .y((Gdx.graphics.getHeight() - 400) - (100 * i))
                    .width(400)
                    .height(50));
        }

        return ui;
    }
}
