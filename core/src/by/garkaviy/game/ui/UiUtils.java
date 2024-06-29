package by.garkaviy.game.ui;

import com.badlogic.gdx.Gdx;

import java.util.Arrays;
import java.util.Collection;
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
}
