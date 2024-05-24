package by.garkaviy.game.location;

import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.texture.TextureLib;

public class Router {
    public static void route() {
        switch (GameContext.getInstance().getRunnableLocation()) {
            case FIRST_TEST_LOCATION:
                GameContext.getInstance().getRunnableLocation().getLocation().placeAction(9, 9,
                        new TileEntity(TileType.ACTION, TextureLib.ACTION_EXAMPLE.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(50, 100);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.SECOND_TEST_LOCATION);
                        });
                break;
            case SECOND_TEST_LOCATION:
                GameContext.getInstance().getRunnableLocation().getLocation().placeAction(0, 2,
                        new TileEntity(TileType.ACTION, TextureLib.ACTION_EXAMPLE.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(400, 450);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.FIRST_TEST_LOCATION);
                        });
                break;
        }
    }
}
