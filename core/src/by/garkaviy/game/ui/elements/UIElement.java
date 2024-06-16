package by.garkaviy.game.ui.elements;

import by.garkaviy.game.ui.FontUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public abstract class UIElement {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int fontSize = 32;
    protected Color fontColor = Color.BLACK;

    protected final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public abstract void render(Batch batch);
    public abstract void render(Batch batch, OrthographicCamera camera);

    protected BitmapFont getFont() {
        return FontUtils.getFont(fontSize, fontColor);
    }
}
