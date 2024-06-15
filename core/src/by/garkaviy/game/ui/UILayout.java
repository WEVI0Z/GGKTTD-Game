package by.garkaviy.game.ui;

import by.garkaviy.game.ui.elements.UIBalance;
import by.garkaviy.game.ui.elements.UIButton;
import by.garkaviy.game.ui.elements.UICameraButton;
import by.garkaviy.game.ui.elements.UIElement;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UILayout {
    List<UIElement> elements = new ArrayList<>();

    public UILayout addElement(UIElement element) {
        elements.add(element);
        return this;
    }

    public void render(Batch batch) {
        for (UIElement element : elements) {
            element.render(batch);
        }
    }

    public void render(Batch batch, OrthographicCamera camera) {
        for (UIElement element : elements) {
            element.render(batch, camera);
        }
    }

    public void clickAction() {
        elements.forEach(uiElement -> {
            if (uiElement instanceof UIButton) {
                ((UIButton) uiElement).clickAction();
            }

            if (uiElement instanceof UICameraButton) {
                ((UICameraButton) uiElement).clickAction();
            }

            if (uiElement instanceof UIBalance) {
                ((UIBalance) uiElement).clickAction();
            }
        });
    }
}
