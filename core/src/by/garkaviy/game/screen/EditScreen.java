package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import by.garkaviy.game.test.AnswerEntity;
import by.garkaviy.game.test.QuestionEntity;
import by.garkaviy.game.test.TestEntity;
import by.garkaviy.game.test.TestLibrary;
import by.garkaviy.game.texture.TextureLib;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class EditScreen implements Screen {
    private final GGKTTDGame game;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Stage stage = new Stage(new ScreenViewport());
    private final TestEntity testEntity;
    private String originalTestName;;

    private QuestionEntity chosenQuestion;
    private String originalQuestionName;

    private AnswerEntity chosenAnswer;
    private String originalAnswerName;

    private QuestionEntity currentQuestion;

    private TextField textField;

    private int page = 0;
    private int fpsCounter = 15;
    private int answerCounter = 0;
    private boolean isRight = false;

    // Layouts
    private UILayout createQuestionLayout;
    private UILayout createAnswerLayout;
    private UILayout chooseQuestionLayout;
    private UILayout editQuestionLayout;
    private UILayout answerLayout;
    private UILayout againLayout;
    private boolean isReload = false;
    private boolean deleteEditLayout = false;
    private boolean deleteAnswerLayout = false;
    private boolean deleteQuestionLayout = false;
    private boolean deleteNewAnswerLayout = false;
    private boolean deleteAgainLayout = false;

    EditScreen(GGKTTDGame game, TestEntity testEntity) {
        batch = new SpriteBatch();
        this.game = game;
        camera = new OrthographicCamera();
        Gdx.input.setInputProcessor(stage);
        camera.setToOrtho(false, 1500, 1000);

        this.testEntity = testEntity;
        originalTestName = testEntity.getTitle();

        chooseQuestionLayout = createChooseLayout();
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

        if (deleteNewAnswerLayout) {
            createAnswerLayout = null;
            deleteNewAnswerLayout = false;
            isReload = true;
        }

        if (deleteQuestionLayout) {
            createQuestionLayout = null;
            deleteQuestionLayout = false;
            isReload = true;
        }

        if (deleteAgainLayout) {
            againLayout = null;
            deleteAgainLayout = false;
            isReload = true;
        }

        if (deleteEditLayout) {
            editQuestionLayout = null;
            deleteEditLayout = false;
            isReload = true;
        }

        if (deleteAnswerLayout) {
            answerLayout = null;
            deleteAnswerLayout = false;
            isReload = true;
        }

        if (isReload) {
            if (Objects.nonNull(chooseQuestionLayout)) {
                chooseQuestionLayout = createChooseLayout();
            }
            if (Objects.nonNull(againLayout)) {
                againLayout = againLayout();
            }
            if (Objects.nonNull(createAnswerLayout)) {
                createAnswerLayout = createNewAnswerLayout();
            }
            if (Objects.nonNull(createQuestionLayout)) {
                createQuestionLayout = createQuestionLayout();
            }
            if (Objects.nonNull(editQuestionLayout)) {
                editQuestionLayout = createQuestionEditLayout();
            }
            if (Objects.nonNull(answerLayout)) {
                answerLayout = createAnswerLayout();
            }
            isReload = false;
        }

        if (Objects.nonNull(againLayout)) {
            againLayout.render(batch);
            if (fpsCounter <= 0) {
                againLayout.clickAction();
            } else {
                fpsCounter--;
            }
        } else if (Objects.nonNull(createAnswerLayout)) {
            createAnswerLayout.render(batch);
            if (fpsCounter <= 0) {
                createAnswerLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act();
            stage.draw();
        } else if (Objects.nonNull(createQuestionLayout)) {
            createQuestionLayout.render(batch);
            if (fpsCounter <= 0) {
                createQuestionLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act();
            stage.draw();
        } else if (Objects.nonNull(answerLayout)) {
            answerLayout.render(batch);
            if (fpsCounter <= 0) {
                answerLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act();
            stage.draw();
        } else if (Objects.nonNull(editQuestionLayout)) {
            editQuestionLayout.render(batch);
            if (fpsCounter <= 0) {
                editQuestionLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act();
            stage.draw();
        } else if (Objects.nonNull(chooseQuestionLayout)) {
            chooseQuestionLayout.render(batch);
            if (fpsCounter <= 0) {
                chooseQuestionLayout.clickAction();
            } else {
                fpsCounter--;
            }
            stage.act();
            stage.draw();
        }
    }

    private UILayout createAnswerLayout() {
        UILayout ui = new UILayout();
        String title = "Подтвердить";
        int fontSize = 50;
        int width = UiUtils.calcWidth(fontSize, title);
        int x = UiUtils.calcCenter(width);
        stage.clear();
        createTextField(chosenAnswer.getText());

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                    if (textField.getText().isEmpty()) return;
                    isReload = true;
                    chosenAnswer.setText(textField.getText());
                    deleteAnswerLayout = true;
                    editQuestionLayout = createQuestionEditLayout();
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
                .title(title + (chosenAnswer.isRight() ? "Да" : "Нет"))
                .runnable(() -> {
                    isReload = true;
                    chosenAnswer.setRight(!chosenAnswer.isRight());
                    fpsCounter = 15;
                })
                .x(x)
                .y(250)
                .width(width)
                .height(100));

        return ui;
    }

    private UILayout againLayout() {
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
                    deleteAgainLayout = true;
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
                    deleteAgainLayout = true;
                    deleteNewAnswerLayout = true;
                    deleteQuestionLayout = true;
                    isReload = true;
                    testEntity.getQuestions().add(currentQuestion);
                    fpsCounter = 15;
                })
                .x(x)
                .y(250)
                .width(width)
                .height(100));

        return ui;
    }

    private UILayout createNewAnswerLayout() {
        answerCounter++;
        if (answerCounter > 4) {
            deleteAnswerLayout = true;
            answerCounter = 0;
            againLayout = againLayout();
            againLayout = againLayout();
            fpsCounter = 15;
            return null;
        }

        UILayout ui = new UILayout();
        String title = "Подтвердить";
        int fontSize = 50;
        int width = UiUtils.calcWidth(fontSize, title);
        int x = UiUtils.calcCenter(width);
        stage.clear();
        createTextField("");

        ui.addElement(new UIButton(fontSize)
                .borderColor(Color.BLACK)
                .title(title)
                .runnable(() -> {
                    if (textField.getText().isEmpty()) return;
                    AnswerEntity answerEntity = new AnswerEntity();
                    answerEntity.setText(textField.getText().trim());
                    answerEntity.setRight(isRight);
                    currentQuestion.getAnswers().add(answerEntity);
                    isReload = true;
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
                    isReload = true;
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
                    createAnswerLayout = createNewAnswerLayout();
                    isReload = true;
                    answerCounter = 0;
                    fpsCounter = 15;
                })
                .x(x)
                .y(250)
                .width(width)
                .height(100));

        return ui;
    }

    private UILayout createQuestionEditLayout() {
        UILayout ui = new UILayout();
        stage.clear();
        createTextField(chosenQuestion.getQuestion(), Gdx.graphics.getWidth() / 2 - 600, Gdx.graphics.getHeight() - 150);

        List<AnswerEntity> answers = chosenQuestion.getAnswers();
        int height = 150;
        int fontSize = 30;
        String biggest =  UiUtils.getBiggestString(answers.stream()
                .map(AnswerEntity::getText)
                .collect(Collectors.toList()));
        int width = UiUtils.calcWidth(fontSize, biggest);
        if (biggest.length() > 50) {
            width /= 3;
        }
        int x = UiUtils.calcCenter(width);
        int pages = answers.size() % 4 == 0 ? answers.size() / 4 - 1 : answers.size() / 4;
        int startIndex = (answers.size() - 1)  * page;
        int endIndex = (answers.size() - 1) * page + 4;
        AtomicInteger iterator = new AtomicInteger();
        answers = answers.stream().filter(test -> {
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
                        isReload = true;
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
                        isReload = true;
                        fpsCounter = 15;
                    })
                    .x(Gdx.graphics.getWidth() - 100)
                    .y(Gdx.graphics.getHeight() / 2 - 50)
                    .width(50)
                    .height(50));
        }

        for (int i = 0; i < answers.size(); i++) {
            String title = answers.get(i).getText();
            AnswerEntity answerEntity = answers.get(i);

            ui.addElement(new UIButton(fontSize)
                    .title(title)
                    .button(TextureLib.BLUE_BUTTON)
                    .runnable(() -> {
                        chosenAnswer = answerEntity;
                        originalAnswerName = answerEntity.getText();
                        isReload = true;
                        answerLayout = createAnswerLayout();
                        fpsCounter = 15;
                    })
                    .x(x)
                    .y(650 - i * (height + 20))
                    .width(width)
                    .height(height));
        }

        ui.addElement(new UIButton(40)
                .borderColor(Color.BLACK)
                .title("Сохранить")
                .runnable(() -> {
                    page = 0;
                    if (textField.getText().isEmpty()) return;
                    testEntity.setQuestions(testEntity.getQuestions()
                            .stream()
                            .filter(question -> !question.getQuestion().equals(originalQuestionName))
                            .collect(Collectors.toList()));
                    chosenQuestion.setQuestion(textField.getText());
                    testEntity.getQuestions().add(chosenQuestion);
                    chooseQuestionLayout = createChooseLayout();
                    deleteEditLayout = true;
                    fpsCounter = 15;
                })
                .x(100)
                .y(20)
                .width(300)
                .height(100));

        return ui;
    }

    private UILayout createChooseLayout() {
        UILayout ui = new UILayout();

        stage.clear();
        createTextField(testEntity.getTitle(), Gdx.graphics.getWidth() / 2 - 600, Gdx.graphics.getHeight() - 150);

        List<QuestionEntity> questions = testEntity.getQuestions();
        int height = 150;
        int fontSize = 30;
        String biggest =  UiUtils.getBiggestString(questions.stream()
                .map(QuestionEntity::getQuestion)
                .collect(Collectors.toList()));
        int width = UiUtils.calcWidth(fontSize, biggest);
        if (biggest.length() > 50) {
            width /= 3;
        }
        int x = UiUtils.calcCenter(width);
        int pages = questions.size() % 4 == 0 ? questions.size() / 4 - 1 : questions.size() / 4;
        int startIndex = (questions.size() - 1)  * page;
        int endIndex = (questions.size() - 1) * page + 4;
        AtomicInteger iterator = new AtomicInteger();
        questions = questions.stream().filter(test -> {
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
                        isReload = true;
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
                        isReload = true;
                        fpsCounter = 15;
                    })
                    .x(Gdx.graphics.getWidth() - 100)
                    .y(Gdx.graphics.getHeight() / 2 - 50)
                    .width(50)
                    .height(50));
        }

        for (int i = 0; i < questions.size(); i++) {
            String title = questions.get(i).getQuestion();
            QuestionEntity questionEntity = questions.get(i);

            if (Objects.isNull(chosenQuestion)) {
                QuestionEntity entity = new QuestionEntity();
                entity.setQuestion("undefined");
                chosenQuestion = entity;
            }

            ui.addElement(new UIButton(fontSize)
                    .title(title)
                    .button(chosenQuestion.getQuestion().equals(title) ? TextureLib.BLUE_SQUARE : TextureLib.GRAY_SQUARE)
                    .runnable(() -> {
                        isReload = true;
                        chosenQuestion = questionEntity;
                        fpsCounter = 15;
                    })
                    .x(x)
                    .y(650 - i * (height + 20))
                    .width(width)
                    .height(height));
        }


        width = 300;
        ui.addElement(new UIButton(40)
                .borderColor(Color.BLACK)
                .title("Редактировать")
                .runnable(() -> {
                    page = 0;
                    originalQuestionName = chosenQuestion.getQuestion();
                    isReload = true;
                    fpsCounter = 15;
                    editQuestionLayout = createQuestionEditLayout();
                })
                .x(100)
                .y(20)
                .width(width + 130)
                .height(100));

        ui.addElement(new UIButton(40)
                .borderColor(Color.BLACK)
                .title("Удалить")
                .runnable(() -> {
                    page = 0;
                    if (Objects.nonNull(chosenQuestion) && !chosenQuestion.getQuestion().equals("undefined")) {
                        testEntity.setQuestions(testEntity.getQuestions()
                                .stream()
                                .filter(question -> !question.getQuestion().equals(chosenQuestion.getQuestion()))
                                .collect(Collectors.toList()));
                        isReload = true;
                        fpsCounter = 15;
                    }
                })
                .x(Gdx.graphics.getWidth() - width - 620)
                .y(20)
                .width(width)
                .height(100));

        ui.addElement(new UIButton(40)
                .borderColor(Color.BLACK)
                .title("Сохранить")
                .runnable(() -> {
                    page = 0;
                    if (textField.getText().isEmpty()) return;
                    TestLibrary.delete(originalTestName);
                    testEntity.setTitle(textField.getText());
                    TestLibrary.saveTest(testEntity);
                    setMainMenu();
                })
                .x(Gdx.graphics.getWidth() - width - 270)
                .y(20)
                .width(width)
                .height(100));

        ui.addElement(new UIButton(40)
                .borderColor(Color.BLACK)
                .title("+")
                .runnable(() -> {
                    page = 0;
                    currentQuestion = new QuestionEntity();
                    isReload = true;
                    createQuestionLayout = createQuestionLayout();
                    fpsCounter = 15;
                })
                .x(Gdx.graphics.getWidth() - 220)
                .y(20)
                .width(100)
                .height(100));

        return ui;
    }

    private void createTextField() {
        stage.clear();
        textField = UiUtils.createTextField();
        stage.addActor(textField);
    }

    private void createTextField(String text) {
        stage.clear();
        textField = UiUtils.createTextField(text);
        stage.addActor(textField);
    }

    private void createTextField(String text, int x, int y) {
        textField = UiUtils.createTextField(text, x, y);
        stage.addActor(textField);
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
