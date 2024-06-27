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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter
@Accessors(fluent = true)
@NoArgsConstructor
public class UIButton extends UIElement {
    private Color borderColor;
    private String title = "Button";
    private Rectangle buttonRectangle;
    private Runnable runnable;
    private BitmapFont font = getFont();
    private boolean isFirst = true;
    private TextureLib texture;
    private TextureLib button = TextureLib.BLUE_BUTTON;
    private int align = Align.center;

    public UIButton(int fontSize) {
        this.fontSize = fontSize;
        font = getFont();
    }

    public UIButton(int fontSize, TextureLib texture) {
        this.fontSize = fontSize;
        font = getFont();
        this.button = texture;
    }

    @Override
    public void render(Batch batch) {
        if (isFirst) {
            isFirst = false;
            buttonRectangle = new Rectangle(x, y, width, height);
        }
        // Вертикальное центрирование текста
        int lineCount = (title.length() * fontSize) / width;
        float textHeight = font.getLineHeight();
        float centerY = y + height / 2 + (((lineCount == 0 ? 1 : lineCount) * textHeight) / 2);

        batch.begin();
        if (Objects.nonNull(button)) batch.draw(button.getTexture(), x, y, width, height);
        font.draw(batch, title, x + 10, centerY, width - 10, align, true);
        batch.end();

        if (Objects.nonNull(texture)) {
            TileEntity tileEntity = new TileEntity(TileType.WALL, texture.getTexture(), (int) (width * 0.8), (int) (height * 0.8), (int) (x + width * 0.1), (int) (y + height * 0.1));
            title = "";
            batch.begin();
            tileEntity.render(batch);
            batch.end();
        }
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
