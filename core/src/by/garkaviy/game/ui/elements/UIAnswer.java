package by.garkaviy.game.ui.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
public class UIAnswer extends UIElement {
    private Color borderColor;
    private String title = "Button";
    private Rectangle buttonRectangle;
    private BitmapFont font = getFont();
    private boolean isFirst = true;
    private boolean isCorrect = false;

    public boolean intersects() {
        if (buttonRectangle.contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
            Gdx.app.debug("Button", "Button " + title + " has been clicked");
            return true;
        }

        return false;
    }

    @Override
    public void render(Batch batch) {
        if (isFirst) {
            isFirst = false;
            buttonRectangle = new Rectangle(x, y, width, height);
        }
        // Вертикальное центрирование текста
        float textHeight = font.getLineHeight();
        float centerY = y + height / 2 + textHeight / 2;

        batch.begin();
        font.draw(batch, title, x, centerY, width, Align.center, true);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
}
