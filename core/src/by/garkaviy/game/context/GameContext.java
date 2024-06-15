package by.garkaviy.game.context;

import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.player.Player;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GameContext {
    private static GameContext instance;

    private Player player;
    private LocationLibrary runnableLocation = LocationLibrary.DORM_ROOM;
    private boolean changeToTest = false;
    private int balance = 0;
    private Texture roomTexture = TextureLib.WOOD_FLOOR.getTexture();
    private int lastX = 100;
    private int lastY = 100;

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }
}
