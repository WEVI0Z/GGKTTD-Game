package by.garkaviy.game.context;

import by.garkaviy.game.location.Location;
import by.garkaviy.game.location.LocationLibrary;
import by.garkaviy.game.player.Player;
import lombok.Data;
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

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }
}
