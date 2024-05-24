package by.garkaviy.game.script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnyScript implements GGKTTDScript {
    private final Runnable runnable;

    @Override
    public void execute() {
        runnable.run();
    }
}
