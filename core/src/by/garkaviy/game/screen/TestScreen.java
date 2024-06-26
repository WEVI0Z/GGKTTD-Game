package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.test.*;
import by.garkaviy.game.texture.TextureLib;
import by.garkaviy.game.ui.FontUtils;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
    private int number;
    private BitmapFont font = FontUtils.getFont(70, Color.WHITE);
    private int page = 0;
    private boolean isReloadMenu = false;

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

        batch.begin();
        Texture workspace = TextureLib.WORKSPACE.getTexture();
        switch (testEnum) {
            case FIRST_TESTS:
                workspace = TextureLib.WORKSPACE.getTexture();
                number = 1;
                break;
            case SECOND_TESTS:
                workspace = TextureLib.WORKSPACE_2.getTexture();
                number = 2;
                break;
            case THIRD_TESTS:
                workspace = TextureLib.WORKSPACE_3.getTexture();
                number = 3;
                break;
            case FOURTH_TESTS:
                workspace = TextureLib.WORKSPACE_4.getTexture();
                number = 4;
                break;
        }
        batch.end();

        batch.begin();
        batch.draw(workspace, 0, 0, 1500, 1000);
        batch.end();

        if (isMenuLayout) {
            menuLayout.render(batch);
            if (fpsCounter <= 0) {
                menuLayout.clickAction();
            } else {
                fpsCounter--;
            }
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
        batch.begin();
        font.draw(batch, String.valueOf(number), 1300, 900, 100, Align.center, true);
        batch.end();

        if (isReloadMenu) {
            menuLayout = createMenu();
            isReloadMenu = false;
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

        questionEntity.getAnswers().stream().sorted(Comparator.comparing(AnswerEntity::getText)).forEach(answer -> {
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

        int pages = tests.size() % 4 == 0 ? tests.size() / 4 - 1 : tests.size() / 4;
        int startIndex = (tests.size() - 1)  * page;
        int endIndex = (tests.size() - 1) * page + 4;
        AtomicInteger iterator = new AtomicInteger();
        tests = tests.stream().filter(test -> {
            boolean result = iterator.get() >= startIndex && iterator.get() < endIndex;
            iterator.getAndIncrement();
            return result;
        }).collect(Collectors.toList());

        if (page != 0) {
            layout.addElement(new UIButton()
                    .borderColor(Color.BLACK)
                    .title("")
                    .button(null)
                    .texture(TextureLib.ARROW_LEFT)
                    .runnable(() -> {
                        page--;
                        isReloadMenu = true;
                        fpsCounter = 15;
                    })
                    .x(450)
                    .y(Gdx.graphics.getHeight() / 2 - 50)
                    .width(50)
                    .height(50));
        }

        if (page != pages) {
            layout.addElement(new UIButton()
                    .borderColor(Color.BLACK)
                    .title("")
                    .button(null)
                    .texture(TextureLib.ARROW_RIGHT)
                    .runnable(() -> {
                        page++;
                        isReloadMenu = true;
                    })
                    .x(Gdx.graphics.getWidth() - 450)
                    .y(Gdx.graphics.getHeight() / 2 - 50)
                    .width(50)
                    .height(50));
        }

        target.addAndGet(padding);
        for (int i = 0; i < tests.size(); i++) {
            String testTitle = tests.get(i).getTitle();
            TestEntity testEntity = tests.get(i);

            layout.addElement(new UIButton(24)
                    .title(testTitle)
                    .runnable(() -> {
                        isButtonPressed = true;
                        test = testEntity;
                        this.layout = createLayout(test.getQuestions().get(0));
                        isLayout = true;
                        isMenuLayout = false;
                        isFirst = false;
                    })
                    .x(530)
                    .y(680 - (80 * (increment.getAndIncrement())))
                    .width(500)
                    .height(60));
        }

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
