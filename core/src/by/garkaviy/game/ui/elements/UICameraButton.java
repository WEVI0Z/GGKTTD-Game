package by.garkaviy.game.ui.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;


@Getter
@Setter
@Accessors(fluent = true)
public class UICameraButton extends UIElement {
    private Color borderColor;
    private String title = "Button";
    private Rectangle buttonRectangle;
    private Runnable runnable;
    private BitmapFont font;
    private boolean isFirst = true;

    public UICameraButton(int fontSize) {
        this.fontSize = fontSize;
        font = getFont();
    }

    @Override
    public void render(Batch batch) {
    }

    @Override
    public void render(Batch batch, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.projection);
        if (isFirst) {
            isFirst = false;
            buttonRectangle = new Rectangle(x, y, width, height);
        }
        // Вертикальное центрирование текста
        float textHeight = font.getLineHeight();
        float centerY = y + height / 2 + textHeight / 2;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(borderColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();


        batch.begin();
        font.draw(batch, title, x - 515, centerY - 305, width, Align.center, true);
        batch.end();
    }

    public void clickAction() {
        if (Gdx.input.isTouched()) {
            if (buttonRectangle.contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
                Gdx.app.debug("Button", "Button " + title + " has been clicked");
                runnable.run();
            }
        }
    }
}
