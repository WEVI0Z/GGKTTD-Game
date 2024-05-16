package by.garkaviy.game.script;

import by.garkaviy.game.GGKTTDGame;
import com.badlogic.gdx.Screen;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetScreenScript implements GGKTTDScript {
    private final Runnable runnable;

    @Override
    public void execute() {
        runnable.run();
    }
}
