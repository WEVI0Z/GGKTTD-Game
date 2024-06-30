package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.test.*;
import by.garkaviy.game.texture.TextureLib;
import by.garkaviy.game.ui.FontUtils;
import by.garkaviy.game.ui.UILayout;
import by.garkaviy.game.ui.UiUtils;
import by.garkaviy.game.ui.elements.UIButton;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CreateScreen implements Screen {
    private final GGKTTDGame game;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Stage stage = new Stage(new ScreenViewport());

    private PartsEnum chosenPart;
    private String chosenTest = "undefined";
    private int page = 0;
    private String title;

    private UILayout mainLayout;
    private UILayout partsLayout;
    private UILayout testsLayout;
    private UILayout createNameLayout;
    private UILayout createQuestionLayout;
    private UILayout createAnswerLayout;
    private UILayout againLayout;

    private boolean ifParts = true;
    private boolean isReloadTitle = false;
    private boolean isTests = false;
    private boolean isReloadTests = false;
    private boolean isCreateName = false;
    private boolean isCreateQuestion = false;
    private boolean isCreateAnswer = false;
    private boolean isReloadAnswer = false;
    private boolean isAgain = false;
    private boolean isRight = false;

    private TextField textField;
    private TestEntity newTestEntity = new TestEntity();
    private QuestionEntity currentQuestion = new QuestionEntity();

    private int questionCounter = 0;
    private int answerCounter = 0;
    private int fpsCounter = 0;

    CreateScreen(GGKTTDGame game) {
        batch = new SpriteBatch();
        this.game = game;
        camera = new OrthographicCamera();
        Gdx.input.setInputProcessor(stage);
        camera.setToOrtho(false, 1500, 1000);

        title = "Выберите раздел";

        mainLayout = mainLayout();
        partsLayout = partsLayout();
        createNameLayout = createNameLayout();
//        createQuestionLayout = createQuestionLayout();
        createAnswerLayout = createAnswerLayout();
        createAnswerLayout = againLayout();

        title = "Выберите раздел";
    }

    @Override
    public void render(float delta) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (isReloadTitle) {
            mainLayout = mainLayout();
            isReloadTitle = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            setMainMenu();
        }

        mainLayout.render(batch);

        if (ifParts) {
            partsLayout.render(batch);
            if (fpsCounter == 0) {
                partsLayout.clickAction();
            } else {
                fpsCounter--;
            }
        }

        if (isReloadTests) {
            testsLayout = testsLayout();
            isReloadTests = false;
        }

        if (isReloadAnswer) {
            createAnswerLayout = createAnswerLayout();
            isReloadAnswer = false;
        }

        if (isTests) {
            testsLayout.render(batch);
            if (fpsCounter <= 0) {
                testsLayout.clickAction();
            } else {
                fpsCounter--;
            }
        }

        if (isCreateName) {
            createNameLayout.render(batch);
            if (fpsCounter <= 0) {
                createNameLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act(delta); // Обновление всех актеров на сцене
            stage.draw();
        }

        if (isCreateQuestion) {
            createQuestionLayout.render(batch);
            if (fpsCounter <= 0) {
                createQuestionLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act(delta); // Обновление всех актеров на сцене
            stage.draw();
        }

        if (isCreateAnswer) {
            createAnswerLayout.render(batch);
            if (fpsCounter == 0) {
                createAnswerLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act(delta); // Обновление всех актеров на сцене
            stage.draw();
        }

        if (isAgain) {
            againLayout.render(batch);
            if (fpsCounter == 0) {
                againLayout.clickAction();
            } else {
                fpsCounter--;
            }
        }
    }

    private UILayout againLayout() {
        title = "Управление";
        isReloadTitle = true;

        UILayout ui = new UILayout();
        String title = "Создать еще один вопрос";
        int fontSize = 50;
        int width = UiUtils.calcWidth(fontSize, title);
        int x = UiUtils.calcCenter(width);
        stage.clear();

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                    isCreateQuestion = true;
                    isAgain = false;
                    createQuestionLayout = createQuestionLayout();
                    fpsCounter = 15;
                })
                .x(x)
                .y(100)
                .width(width)
                .height(100));


        title = "Законичить";
        width = UiUtils.calcWidth(fontSize, title);
        x = UiUtils.calcCenter(width);
        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                    TestLibrary.saveTest(newTestEntity);
                    setMainMenu();
                })
                .x(x)
                .y(250)
                .width(width)
                .height(100));

        return ui;
    }

    private UILayout createAnswerLayout() {
        title = "Создайте ответ (" + ++answerCounter + ")";
        isReloadTitle = true;

        if (answerCounter > 4) {
            isCreateAnswer = false;
            newTestEntity.getQuestions().add(currentQuestion);
            answerCounter = 0;
            againLayout = againLayout();
            isAgain = true;
            fpsCounter = 15;
            return null;
        }

        UILayout ui = new UILayout();
        String title = "Подтвердить";
        int fontSize = 50;
        int width = UiUtils.calcWidth(fontSize, title);
        int x = UiUtils.calcCenter(width);
        stage.clear();
        createTextField();

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                    if (textField.getText().isEmpty()) return;
                    AnswerEntity answerEntity = new AnswerEntity();
                    answerEntity.setText(textField.getText().trim());
                    answerEntity.setRight(isRight);
                    currentQuestion.getAnswers().add(answerEntity);
                    isReloadAnswer = true;
                    fpsCounter = 15;
                })
                .x(x)
                .y(100)
                .width(width)
                .height(100));


        title = "Ответ правильный: ";
        width = UiUtils.calcWidth(fontSize, title);
        x = UiUtils.calcCenter(width);
        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title + (isRight ? "Да" : "Нет"))
                .runnable(() -> {
                    isReloadAnswer = true;
                    isRight = !isRight;
                    fpsCounter = 15;
                    answerCounter--;
                })
                .x(x)
                .y(250)
                .width(width)
                .height(100));

        return ui;
    }

    private UILayout createQuestionLayout() {
        title = "Создайте вопрос (" + (++questionCounter) + ")";
        isReloadTitle = true;

        UILayout ui = new UILayout();
        String title = "Подтвердить";
        int fontSize = 50;
        int width = UiUtils.calcWidth(fontSize, title);
        int x = UiUtils.calcCenter(width);
        stage.clear();
        createTextField();

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                    if (textField.getText().isEmpty()) return;
                    currentQuestion = new QuestionEntity();
                    currentQuestion.setQuestion(textField.getText().trim());
                    currentQuestion.setAnswers(new ArrayList<>());
                    createAnswerLayout = createAnswerLayout();
                    isCreateQuestion = false;
                    isCreateAnswer = true;
                    isReloadAnswer = true;
                    answerCounter = 0;
                    fpsCounter = 15;
                })
                .x(x)
                .y(250)
                .width(width)
                .height(100));

        return ui;
    }

    private UILayout createNameLayout() {
        UILayout ui = new UILayout();
        String title = "Подтвердить";
        int fontSize = 50;
        int width = UiUtils.calcWidth(fontSize, title);
        int x = UiUtils.calcCenter(width);
        stage.clear();
        createTextField();

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                    if (textField.getText().isEmpty()) return;
                    newTestEntity = new TestEntity();
                    newTestEntity.setPart(chosenPart.getTitle());
                    newTestEntity.setTitle(textField.getText().trim());
                    newTestEntity.setQuestions(new ArrayList<>());
                    isCreateName = false;
                    isCreateQuestion = true;
                    createQuestionLayout = createQuestionLayout();
                    fpsCounter = 15;
                })
                .x(x)
                .y(100)
                .width(width)
                .height(100));

        return ui;
    }

    private void createTextField() {
        // Создание TextFieldStyle с кастомным шрифтом
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = FontUtils.getFont(40, Color.BLACK);
        textFieldStyle.fontColor = Color.BLACK;

        TextureRegion cursor = new TextureRegion(TextureLib.TEXT_MARKER.getTexture());
        textFieldStyle.cursor = new TextureRegionDrawable(cursor);


        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(TextureLib.GREY_FLAT_BUTTON.getTexture()));
        textFieldStyle.background.setLeftWidth(textFieldStyle.background.getLeftWidth() + 30);

        TextField textField = new TextField("", textFieldStyle);

        textField.setSize(1200, 100);
        textField.setPosition(Gdx.graphics.getWidth() / 2 - 600, Gdx.graphics.getHeight() - 600);

        this.textField = textField;
        stage.addActor(textField);
    }

    private UILayout mainLayout() {
        UILayout ui = new UILayout();
        int fontSize = 50;
        int width = UiUtils.calcWidth(fontSize, title);
        int x = UiUtils.calcCenter(width);

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                })
                .x(x)
                .y(850)
                .width(width)
                .height(100));

        return ui;
    }

    private UILayout partsLayout() {
        UILayout ui = new UILayout();
        PartsEnum[] parts = PartsEnum.values();
        int height = 70;
        int fontSize = 40;
        int width = UiUtils.calcWidth(fontSize, UiUtils.getBiggestString(Arrays.stream(parts).map(PartsEnum::getTitle).collect(Collectors.toList())));
        int x = UiUtils.calcCenter(width);

        for (int i = 0; i < PartsEnum.values().length; i++) {
            String title = parts[i].getTitle();
            PartsEnum chosenPart = parts[i];

            ui.addElement(new UIButton(fontSize)
                    .title(title)
                    .runnable(() -> {
                        ifParts = false;
                        isTests = true;
                        isReloadTests = true;
                        this.title = "Выберите или создайте тест";
                        isReloadTitle = true;
                        this.chosenPart = chosenPart;
                        fpsCounter = 15;
                    })
                    .x(x)
                    .y(650 - i * (height + 20))
                    .width(width)
                    .height(height));
        }

        return ui;
    }

    private UILayout testsLayout() {
        UILayout ui = new UILayout();
        List<TestEntity> tests = TestLibrary.load(chosenPart.getTitle());
        int height = 100;
        int fontSize = 40;
        int width = UiUtils.calcWidth(fontSize, UiUtils.getBiggestString(tests.stream().map(TestEntity::getTitle).collect(Collectors.toList())));
        int x = UiUtils.calcCenter(width);
        int pages = tests.size() % 4 == 0 ? tests.size() / 4 - 1 : tests.size() / 4;
        int startIndex = (tests.size() - 1) * page;
        int endIndex = (tests.size() - 1) * page + 4;
        AtomicInteger iterator = new AtomicInteger();
        tests = tests.stream().filter(test -> {
            boolean result = iterator.get() >= startIndex && iterator.get() < endIndex;
            iterator.getAndIncrement();
            return result;
        }).collect(Collectors.toList());


        if (page != 0) {
            ui.addElement(new UIButton(fontSize)
                    .borderColor(Color.BLACK)
                    .title("")
                    .button(null)
                    .texture(TextureLib.ARROW_LEFT)
                    .runnable(() -> {
                        page--;
                        isReloadTests = true;
                        fpsCounter = 15;
                    })
                    .x(100)
                    .y(Gdx.graphics.getHeight() / 2 - 50)
                    .width(50)
                    .height(50));
        }

        if (page != pages) {
            ui.addElement(new UIButton(fontSize)
                    .borderColor(Color.BLACK)
                    .title("")
                    .button(null)
                    .texture(TextureLib.ARROW_RIGHT)
                    .runnable(() -> {
                        page++;
                        isReloadTests = true;
                    })
                    .x(Gdx.graphics.getWidth() - 100)
                    .y(Gdx.graphics.getHeight() / 2 - 50)
                    .width(50)
                    .height(50));
        }

        for (int i = 0; i < tests.size(); i++) {
            String title = tests.get(i).getTitle();

            ui.addElement(new UIButton(fontSize)
                    .title(title)
                    .button(chosenTest.equals(title) ? TextureLib.BLUE_SQUARE : TextureLib.GRAY_SQUARE)
                    .runnable(() -> {
                        isReloadTests = true;
                        chosenTest = title;
                        fpsCounter = 15;
                    })
                    .x(x)
                    .y(650 - i * (height + 20))
                    .width(width)
                    .height(height));
        }

        width = 300;
        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title("Удалить")
                .runnable(() -> {
                    TestLibrary.delete(chosenTest);
                    isReloadTests = true;
                    chosenTest = "undefined";
                    fpsCounter = 15;
                })
                .x(200)
                .y(50)
                .width(width)
                .height(100));

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title("Редактировать")
                .runnable(() -> {
                    if (Objects.nonNull(chosenTest) && !chosenTest.equals("undefined")) {
                        dispose();
                        game.setScreen(new EditScreen(game, TestLibrary.load(chosenPart.getTitle())
                                .stream()
                                .filter(test -> test.getTitle().equals(chosenTest))
                                .collect(Collectors.toList())
                                .get(0)));

                    }
                })
                .x(Gdx.graphics.getWidth() - width - 660)
                .y(50)
                .width(width + 120)
                .height(100));

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title("Создать")
                .runnable(() -> {
                    isTests = false;
                    page = 0;
                    this.title = "Введите имя нового теста";
                    isReloadTitle = true;
                    isCreateName = true;
                    createNameLayout = createNameLayout();
                    questionCounter = 0;
                    fpsCounter = 15;
                })
                .x(Gdx.graphics.getWidth() - width - 200)
                .y(50)
                .width(300)
                .height(100));

        return ui;
    }

    private void setMainMenu() {
        dispose();
        game.setScreen(new MainMenuScreen(game));
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
