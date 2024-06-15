package by.garkaviy.game.ui.elements;

import by.garkaviy.game.location.TileEntity;
import by.garkaviy.game.location.TileType;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
public class UIButton extends UIElement {
    private Color borderColor;
    private String title = "Button";
    private Rectangle buttonRectangle;
    private Runnable runnable;
    private BitmapFont font = getFont();
    private boolean isFirst = true;
    private TextureLib texture;

    @Override
    public void render(Batch batch) {
        if (Objects.nonNull(texture)) {
            TileEntity tileEntity = new TileEntity(TileType.WALL, texture.getTexture(), 100, 100, x, y);
            title = "";
            batch.begin();
            tileEntity.render(batch);
            batch.end();
        }
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
        shapeRenderer.setColor(borderColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    @Override
    public void render(Batch batch, OrthographicCamera camera) {

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
