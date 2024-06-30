package by.garkaviy.game.ui;

import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.List;
import java.util.stream.Collectors;

public class UiUtils {
    public static int calcWidth(int fontSize, String text) {
        return (int) (text.length() * fontSize);
    }

    public static int calcCenter(int width) {
        return Gdx.graphics.getWidth() / 2 - width / 2;
    }

    public static String getBiggestString(List<String> strings) {
        return strings.stream().sorted((a, b) -> b.length() - a.length()).collect(Collectors.toList()).get(0);
    }

    public static TextField createTextField() {
        return createTextField("", Gdx.graphics.getWidth() / 2 - 600, Gdx.graphics.getHeight() - 600);
    }

    public static TextField createTextField(String text) {
        return createTextField(text, Gdx.graphics.getWidth() / 2 - 600, Gdx.graphics.getHeight() - 600);
    }

    public static TextField createTextField(String text, int x, int y) {
        // Создание TextFieldStyle с кастомным шрифтом
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = FontUtils.getFont(40, Color.BLACK);
        textFieldStyle.fontColor = Color.BLACK;

        TextureRegion cursor = new TextureRegion(TextureLib.TEXT_MARKER.getTexture());
        textFieldStyle.cursor = new TextureRegionDrawable(cursor);


        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(TextureLib.GREY_FLAT_BUTTON.getTexture()));
        textFieldStyle.background.setLeftWidth(textFieldStyle.background.getLeftWidth() + 30);

        TextField textField = new TextField(text, textFieldStyle);

        textField.setSize(1200, 100);
        textField.setPosition(x, y);

        return textField;
    }
}
