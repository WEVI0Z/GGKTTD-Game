package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.test.QuestionEntity;
import by.garkaviy.game.test.TestEntity;
import by.garkaviy.game.test.TestEnum;
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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TestScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final GGKTTDGame game;
    private final TestLibrary testLibrary = new TestLibrary();
    private final Random random = new Random();
    private List<TestEntity> tests;
    private TestEntity test = TestLibrary.getTests().get(random.nextInt(0, testLibrary.getTests().size()));
    private boolean isFirst = true;

    private boolean isButtonPressed = false;
    private int fpsCounter = 0;
    private UILayout layout;
    private boolean isLayout = false;
    private UILayout menuLayout;
    private boolean isMenuLayout = true;
    private int counter = 0;

    private final TestEnum testEnum;

    TestScreen(GGKTTDGame game, TestEnum testEnum) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 1000);

        this.testEnum = testEnum;

        tests = TestLibrary.getTests();
        menuLayout = createMenu();
//        layout = createLayout(test.getQuestions().get(counter));
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

        Texture workspace = TextureLib.WORKSPACE.getTexture();
        switch (testEnum) {
            case FIRST_TESTS:
                workspace = TextureLib.WORKSPACE.getTexture();
                break;
            case SECOND_TESTS:
                workspace = TextureLib.WORKSPACE_2.getTexture();
                break;
            case THIRD_TESTS:
                workspace = TextureLib.WORKSPACE_3.getTexture();
                break;
            case FOURTH_TESTS:
                workspace = TextureLib.WORKSPACE_4.getTexture();
                break;
        }
        batch.begin();
        batch.draw(workspace, 0, 0, 1500, 1000);
        batch.end();

        if (isMenuLayout) {
            menuLayout.render(batch);
            menuLayout.clickAction();
        }

        AtomicBoolean refreshLayout = new AtomicBoolean(false);
        AtomicBoolean isToMarkIncorrect = new AtomicBoolean(false);
        AtomicInteger x = new AtomicInteger();
        AtomicInteger width = new AtomicInteger();
        AtomicReference<String> string = new AtomicReference<>("Неправильно");

        if (isLayout) {
            layout.render(batch);
            layout.getElements().forEach(element -> {
                if (element instanceof UIAnswer) {
                    if (Gdx.input.isTouched() && !isButtonPressed) {
                        if (((UIAnswer) element).buttonRectangle().contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
                            Gdx.app.debug("Button", "Button " + ((UIAnswer) element).title() + " has been clicked");

                            if (((UIAnswer) element).isCorrect()) {
                                counter++;
                                if (counter == test.getQuestions().size()) {
                                    GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() + 100);
                                    GameContext.getInstance().setScore(GameContext.getInstance().getScore() + 100);
                                    GameContext.getInstance().setBonusHint(true);
                                    GameContext.getInstance().getTests().put(test.getTitle(), true);
                                    setMainMenu();
                                } else {
                                    isButtonPressed = true;
                                    refreshLayout.set(true);
                                }
                            } else {
                                isButtonPressed = true;
                                isToMarkIncorrect.set(true);
                                x.set(element.x());
                                width.set(element.width());
                                string.set(((UIAnswer) element).title());
                            }
                        }
                    }
                }
            });
        }

        if (refreshLayout.get()) {
            this.layout = createLayout(test.getQuestions().get(counter));
        }

        if (isToMarkIncorrect.get()) {
            this.layout = markAsIncorrect(x.get(), width.get(), string.get());
        }

        if (isButtonPressed) {
            fpsCounter++;
            if (fpsCounter == 15) {
                fpsCounter = 0;
                isButtonPressed = false;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            setMainMenu();
        }
    }

    private UILayout createLayout(QuestionEntity questionEntity) {
        int padding = 10;
        int width = (650 - (padding * (questionEntity.getAnswers().size() + 1))) / questionEntity.getAnswers().size();
        AtomicInteger target = new AtomicInteger();
        final UILayout[] layout = {new UILayout()};
        int externalPadding = 450;

        layout[0].addElement(new UIButton(26)
                .borderColor(Color.WHITE)
                .title(questionEntity.getQuestion())
                .runnable(() -> {
                })
                .x(470)
                .y(450)
                .width(600)
                .height(300));

        questionEntity.getAnswers().forEach(answer -> {
            target.addAndGet(padding);
            layout[0].addElement(new UIAnswer()
                    .borderColor(Color.BLACK)
                    .title(answer.getText())
                    .isCorrect(answer.isRight())
                    .x(externalPadding + target.get())
                    .y(350)
                    .width(width)
                    .height(50));

            target.addAndGet(width);
        });

        return layout[0];
    }

    private UILayout createMenu() {
        int padding = 10;
        AtomicInteger target = new AtomicInteger();
        final UILayout layout = new UILayout();
        AtomicInteger increment = new AtomicInteger(1);

        this.tests = TestLibrary.getTests();
        switch (testEnum) {
            case FIRST_TESTS:
                this.tests = TestLibrary.getTests();
                break;
            case SECOND_TESTS:
                this.tests = TestLibrary.getSecondTests();
                break;
            case THIRD_TESTS:
                this.tests = TestLibrary.getThirdTests();
                break;
            case FOURTH_TESTS:
                this.tests = TestLibrary.getFourthTests();
                break;
        }

        String title = "Тесты";
        switch (testEnum) {
            case FIRST_TESTS:
                title = "Базы данных";
                break;
            case SECOND_TESTS:
                title = "Компьютерные сети";
                break;
            case THIRD_TESTS:
                title = "Программирование";
                break;
            case FOURTH_TESTS:
                title = "Технологии";
                break;
        }

        layout.addElement(new UIButton(36)
                .borderColor(Color.WHITE)
                .title(title)
                .runnable(() -> {
                })
                .x(485)
                .y(690)
                .width(600)
                .height(80));

        target.addAndGet(padding);
        layout.addElement(new UIButton(24)
                .borderColor(Color.BLACK)
                .title(tests.get(0).getTitle())
                .runnable(() -> {
                    isButtonPressed = true;
                    test = tests.get(0);
                    this.layout = createLayout(test.getQuestions().get(0));
                    isLayout = true;
                    isMenuLayout = false;
                    isFirst = false;
                })
                .x(530)
                .y(680 - (80 * (increment.getAndIncrement())))
                .width(500)
                .height(60));

        target.addAndGet(padding);
        layout.addElement(new UIButton(24)
                .borderColor(Color.BLACK)
                .title(tests.get(1).getTitle())
                .runnable(() -> {
                    isButtonPressed = true;
                    test = tests.get(1);
                    this.layout = createLayout(test.getQuestions().get(0));
                    isLayout = true;
                    isMenuLayout = false;
                    isFirst = false;
                })
                .x(530)
                .y(680 - (80 * (increment.getAndIncrement())))
                .width(500)
                .height(60));

        target.addAndGet(padding);
        layout.addElement(new UIButton(24)
                .borderColor(Color.BLACK)
                .title(tests.get(2).getTitle())
                .runnable(() -> {
                    isButtonPressed = true;
                    test = tests.get(2);
                    this.layout = createLayout(test.getQuestions().get(0));
                    isLayout = true;
                    isMenuLayout = false;
                    isFirst = false;
                })
                .x(530)
                .y(680 - (80 * (increment.getAndIncrement())))
                .width(500)
                .height(60));

        target.addAndGet(padding);
        layout.addElement(new UIButton(24)
                .borderColor(Color.BLACK)
                .title(tests.get(3).getTitle())
                .runnable(() -> {
                    isButtonPressed = true;
                    test = tests.get(3);
                    this.layout = createLayout(test.getQuestions().get(0));
                    isLayout = true;
                    isMenuLayout = false;
                    isFirst = false;
                })
                .x(530)
                .y(680 - (80 * (increment.getAndIncrement())))
                .width(500)
                .height(60));

        return layout;
    }

    private UILayout markAsIncorrect(int x, int width, String title) {
        return this.layout.addElement(new UIButton(15, TextureLib.RED_BUTTON)
                .borderColor(Color.WHITE)
                .title(title)
                .x(x)
                .y(350)
                .width(width)
                .height(50));
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
