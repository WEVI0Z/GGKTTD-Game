package by.garkaviy.game.context;

import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.player.Player;
import by.garkaviy.game.test.TestEnum;
import by.garkaviy.game.test.TestLibrary;
import by.garkaviy.game.texture.TextureLib;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class GameContext {
    private static GameContext instance;

    private String saveName;
    private String password = "123";
    private Player player = new Player();
    private LocationLibrary runnableLocation = LocationLibrary.CLASSES;
    private boolean changeToTest = false;
    private TestEnum whatTest = TestEnum.FIRST_TESTS;
    private int balance = 0;
    private int score = 0;
    private TextureLib roomTexture = TextureLib.WOOD_FLOOR;
    private TextureLib windowTexture = TextureLib.WINDOW_1;
    private TextureLib bedTexture = TextureLib.BED_1;
    private int lastX = 100;
    private int lastY = 100;
    private boolean exitHint = false;
    private boolean bonusHint = false;
    private boolean isStatScreen = false;
    private boolean isCreate = false;
    private Map<String, Boolean> tests = getDefaultTests();


    private static Map<String, Boolean> getDefaultTests() {
        Map<String, Boolean> tests = new HashMap<>();

        TestLibrary.getAllTitles().forEach(title -> {
            tests.put(title, false);
        });

        return tests;
    }

    public static GameContext getDefaultInstance() {
        GameContext instance = new GameContext();

        instance.runnableLocation = LocationLibrary.DORM_ROOM;
        instance.changeToTest = false;
        instance.balance = 0;
        instance.score = 0;
        instance.roomTexture = TextureLib.WOOD_FLOOR;
        instance.windowTexture = TextureLib.WINDOW_1;
        instance.bedTexture = TextureLib.BED_1;
        instance.lastX = 100;
        instance.lastY = 100;
        instance.exitHint = false;
        instance.bonusHint = false;
        Player player = new Player();
        player.setLocation(100, 100);
        instance.player = player;
        instance.tests = getDefaultTests();

        return instance;
    }

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }

    public static GameContext setInstance(GameContext gameContext) {
        instance = gameContext;
        return instance;
    }

    public static boolean checkInstance() {
        return Objects.nonNull(instance);
    }
}
