package by.garkaviy.game.context;

import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.player.Player;
import by.garkaviy.game.test.TestEnum;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Texture roomTexture = TextureLib.WOOD_FLOOR.getTexture();
    private Texture windowTexture = TextureLib.WINDOW_1.getTexture();
    private Texture bedTexture = TextureLib.BED_1.getTexture();
    private int lastX = 100;
    private int lastY = 100;
    private boolean exitHint = false;
    private boolean bonusHint = false;

    public static GameContext getDefaultInstance() {
        GameContext instance = new GameContext();

        instance.runnableLocation = LocationLibrary.DORM_ROOM;
        instance.changeToTest = false;
        instance.balance = 0;
        instance.roomTexture = TextureLib.WOOD_FLOOR.getTexture();
        instance.windowTexture = TextureLib.WINDOW_1.getTexture();
        instance.bedTexture = TextureLib.BED_1.getTexture();
        instance.lastX = 100;
        instance.lastY = 100;
        instance.exitHint = false;
        instance.bonusHint = false;
        Player player = new Player();
        player.setLocation(100, 100);
        instance.player = player;

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
