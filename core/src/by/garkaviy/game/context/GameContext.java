package by.garkaviy.game.context;

import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GameContext {
    private static GameContext instance;

    private Player player;
    private LocationLibrary runnableLocation;
    private boolean changeToTest = false;

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }
}
