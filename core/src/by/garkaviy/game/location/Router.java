package by.garkaviy.game.location;

import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.texture.TextureLib;

public class Router {
    public static void route() {
        switch (GameContext.getInstance().getRunnableLocation()) {
            case FIRST_TEST_LOCATION:
                GameContext.getInstance().getRunnableLocation().getLocation().placeAction(9, 9,
                        new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(50, 100);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.SECOND_TEST_LOCATION);
                        });
                break;
            case SECOND_TEST_LOCATION:
                GameContext.getInstance().getRunnableLocation().getLocation().placeAction(0, 2,
                        new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(400, 450);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.FIRST_TEST_LOCATION);
                        });
                break;
            case DORM_ROOM:
                GameContext.getInstance().getRunnableLocation().getLocation().placeAction(3, 0,
                        new TileEntity(TileType.ACTION, TextureLib.WOOD_FLOOR.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(350, 150);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_UPSTAIRS);
                        });
                break;
            case DORM_UPSTAIRS:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(7, 4, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(150, 50);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_ROOM);
                        })
                        .placeAction(3, 4, new TileEntity(TileType.ACTION, TextureLib.STAIRS_DOWN.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(150, 50);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_HALL);
                        });
                break;
            case DORM_HALL:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(3, 0, new TileEntity(TileType.ACTION, TextureLib.STAIRS_DOWN.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(150, 150);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_UPSTAIRS);
                        }).placeAction(7, 0, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(150, 250);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_EXIT);
                        });
                break;
            case DORM_EXIT:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(3, 6, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(350, 50);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_HALL);
                        });
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(3, 0, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(350, 50);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.OUTSIDE);
                        });
                break;
        }
    }
}
