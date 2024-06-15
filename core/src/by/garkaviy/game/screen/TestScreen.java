package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.test.QuestionEntity;
import by.garkaviy.game.test.TestEntity;
import by.garkaviy.game.test.TestLibrary;
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

public class TestScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final GGKTTDGame game;
    private final TestLibrary testLibrary = new TestLibrary();
    private final Random random = new Random();
    private final TestEntity test = testLibrary.getTests().get(random.nextInt(0, testLibrary.getTests().size()));

    private boolean isButtonPressed = false;
    private int fpsCounter = 0;
    private UILayout layout;
    private int counter = 0;

    TestScreen(GGKTTDGame game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 1000);

        layout = createLayout(test.getQuestions().get(counter));
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
//        layout.clickAction();


        AtomicBoolean refreshLayout = new AtomicBoolean(false);
        AtomicBoolean isToMarkIncorrect = new AtomicBoolean(false);
        AtomicInteger x = new AtomicInteger();
        AtomicInteger width = new AtomicInteger();

        layout.getElements().forEach(element -> {
            if (element instanceof UIAnswer) {
                if (Gdx.input.isTouched() && !isButtonPressed) {
                    if (((UIAnswer) element).buttonRectangle().contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
                        Gdx.app.debug("Button", "Button " + ((UIAnswer) element).title() + " has been clicked");

                        if (((UIAnswer) element).isCorrect()) {
                            counter++;
                            if (counter == test.getQuestions().size()) {
                                GameContext.getInstance().setBalance(GameContext.getInstance().getBalance() + 100);
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
                        }
                    }
                }
            }
        });

        if (refreshLayout.get()) {
            this.layout = createLayout(test.getQuestions().get(counter));
        }

        if (isToMarkIncorrect.get()) {
            this.layout = markAsIncorrect(x.get(), width.get());
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
        int screenWidth = 1500;
        int padding = 50;
        int width = (screenWidth - (padding * (questionEntity.getAnswers().size() + 1))) / questionEntity.getAnswers().size();
        AtomicInteger target = new AtomicInteger();
        final UILayout[] layout = {new UILayout()};

        layout[0].addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title(questionEntity.getQuestion())
                .x(50)
                .y(300)
                .width(1400)
                .height(100));

        questionEntity.getAnswers().forEach(answer -> {
            target.addAndGet(padding);
            layout[0].addElement(new UIAnswer()
                    .borderColor(Color.BLACK)
                    .title(answer.getText())
                    .isCorrect(answer.isRight())
                    .x(target.get())
                    .y(150)
                    .width(width)
                    .height(50));

            target.addAndGet(width);
        });

        return layout[0];
    }

    private UILayout markAsIncorrect(int x, int width) {
        return this.layout.addElement(new UIButton()
                .borderColor(Color.WHITE)
                .title("Неправильно")
                .x(x)
                .y(75)
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
