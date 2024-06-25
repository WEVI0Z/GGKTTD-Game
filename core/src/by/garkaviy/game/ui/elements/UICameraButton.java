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
public class UICameraButton extends UIElement {
    private Color borderColor;
    private String title = "Button";
    private Rectangle buttonRectangle;
    private Runnable runnable;
    private BitmapFont font;
    private TextureLib texture;
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
            x -= 400;
            y -= 240;
            isFirst = false;
            buttonRectangle = new Rectangle((int) (Gdx.graphics.getWidth() / 2 + (x * 1.9)), (int) (Gdx.graphics.getHeight() / 2 + (y * 2.1)), (int) (width * 1.85), height * 2);
        }
        // Вертикальное центрирование текста
        int lineCount = (title.length() * fontSize) / width;
        float textHeight = font.getLineHeight();
        float centerY = y + height / 2 + (((lineCount == 0 ? 1 : lineCount) * textHeight) / 2);

        batch.begin();
        batch.draw(TextureLib.BLUE_BUTTON.getTexture(), x, y, width, height);
        font.draw(batch, title, x, centerY, width, Align.center, true);
        batch.end();

        if (Objects.nonNull(texture)) {
            TileEntity tileEntity = new TileEntity(TileType.WALL, texture.getTexture(), (int) (width * 0.7), (int) (height * 0.7), (int) (x + width * 0.15), (int) (y + height * 0.17));
            title = "";
            batch.begin();
            tileEntity.render(batch);
            batch.end();
        }
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
