package by.garkaviy.game.ui.elements;

import by.garkaviy.game.location.TileEntity;
import by.garkaviy.game.location.TileType;
import by.garkaviy.game.texture.TextureLib;
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
import java.util.Objects;

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

    public UIAnswer() {
        this.fontSize = 15;
        font = getFont();
    }

    @Override
    public void render(Batch batch) {
        if (isFirst) {
            isFirst = false;
            buttonRectangle = new Rectangle(x, y, width, height);
            TileEntity tileEntity = new TileEntity(TileType.WALL, TextureLib.BLUE_BUTTON.getTexture(), 100, 100, x, y);
            batch.begin();
            tileEntity.render(batch);
            batch.end();
        }
        // Вертикальное центрирование текста
        int lineCount = (title.length() * fontSize) / width;
        float textHeight = font.getLineHeight();
        float centerY = y + height / 2 + (((lineCount == 0 ? 1 : lineCount) * textHeight) / 2);

        batch.begin();
        batch.draw(TextureLib.BLUE_BUTTON.getTexture(), x, y, width, height);
        font.draw(batch, title, x + 10, centerY, width - 10, Align.center, true);
        batch.end();
    }

    @Override
    public void render(Batch batch, OrthographicCamera camera) {

    }
}
