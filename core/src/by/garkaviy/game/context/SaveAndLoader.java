package by.garkaviy.game.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.List;

public class SaveAndLoader {
    public static void save() {
        // Предположим, что у вас есть объект data, который вы хотите сохранить в формате JSON
        GameContext.getInstance().setLastX(GameContext.getInstance().getPlayer().x);
        GameContext.getInstance().setLastY(GameContext.getInstance().getPlayer().y);
        Json json = new Json();
        String jsonData = json.prettyPrint(GameContext.getInstance()); // преобразование объекта в JSON строку

        FileHandle file = Gdx.files.local("saves/" + GameContext.getInstance().getSaveName() + ".json"); // указываем путь к файлу
        file.writeString(jsonData, false); // запись JSON строки в файл
    }

    public static void load() {
        String fileName = GameContext.getInstance().getSaveName() + ".json";
        if (!Gdx.files.local("saves/" + fileName).exists()) {
            return;
        }
        FileHandle file = Gdx.files.local("saves/" + fileName); // указываем путь к файлу
        String jsonData = file.readString(); // чтение JSON строки из файла

        Json json = new Json();
        GameContext.setInstance(json.fromJson(GameContext.class, jsonData)); // преобразование JSON строки в объект
    }

    public static void delete(String fileName) {
        FileHandle file = Gdx.files.local("saves/" + fileName + ".json"); // указываем путь к файлу
        file.delete();
    }

    public static void load(String name) {
        String fileName = name + ".json";
        if (!Gdx.files.local("saves/" + fileName).exists()) {
            throw new RuntimeException("File not found: " + fileName);
        }

        FileHandle file = Gdx.files.local("saves/" + fileName); // указываем путь к файлу
        String jsonData = file.readString(); // чтение JSON строки из файла
        Json json = new Json();
        GameContext.setInstance(json.fromJson(GameContext.class, jsonData)); // преобразование JSON строки в объект
        GameContext.getInstance().setSaveName(name);
    }

    public static List<String> loadSaves() {
        List<String> saves = new ArrayList<>();
        FileHandle folder = Gdx.files.local("saves");
        FileHandle[] files = folder.list();

        for (FileHandle file : files) {
            saves.add(file.nameWithoutExtension());
        }

        return saves;
    }
}
