package by.garkaviy.game.screen;

import by.garkaviy.game.GGKTTDGame;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MainMenuScreen implements Screen {
    private final GGKTTDGame game;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final ShapeRenderer shapeRenderer;

    private final Rectangle playButtonRectangle;
    private final Rectangle settingsButtonRectangle;
    private final Rectangle exitButtonRectangle;

    public MainMenuScreen(final GGKTTDGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/minecraft.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;

        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);

        playButtonRectangle = new Rectangle(100, 100, 200, 50);
        settingsButtonRectangle = new Rectangle(100, 200, 200, 50);
        exitButtonRectangle = new Rectangle(100, 300, 200, 50);
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
        font.draw(batch, "Play", playButtonRectangle.x + 50, playButtonRectangle.y + 40);
        font.draw(batch, "Settings", settingsButtonRectangle.x + 40, settingsButtonRectangle.y + 40);
        font.draw(batch, "Exit", exitButtonRectangle.x + 50, exitButtonRectangle.y + 40);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(playButtonRectangle.x, playButtonRectangle.y, playButtonRectangle.width, playButtonRectangle.height);
        shapeRenderer.rect(settingsButtonRectangle.x, settingsButtonRectangle.y, settingsButtonRectangle.width, settingsButtonRectangle.height);
        shapeRenderer.rect(exitButtonRectangle.x, exitButtonRectangle.y, exitButtonRectangle.width, exitButtonRectangle.height);
        shapeRenderer.end();

        if (Gdx.input.isTouched()) {
            Gdx.app.debug("Touch",
                    "Touched x: " + Gdx.input.getX() + " y: " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
            Gdx.app.debug("Touch", playButtonRectangle.x + " " + playButtonRectangle.y);
            if (playButtonRectangle.contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
                // Обработка нажатия на кнопку "Play"
                game.setScreen(new GameScreen(game));
                dispose();
            }
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
        font.dispose();
    }
}
