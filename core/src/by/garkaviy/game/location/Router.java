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
                        new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(350, 150);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_UPSTAIRS);
                        });
                break;
            case DORM_UPSTAIRS:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(7, 4, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_FRONT.getTexture()), () -> {
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
                        }).placeAction(7, 0, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(150, 250);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_EXIT);
                        });
                break;
            case DORM_EXIT:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(3, 6, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_FRONT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(350, 50);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_HALL);
                        });
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(3, 0, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(900, 400);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.OUTSIDE);
                        });
                break;
            case OUTSIDE:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(18, 9, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_FRONT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(150, 50);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.DORM_EXIT);
                        })
                        .placeAction(5, 7, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_EXIT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(50, 100);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.COLLEGE_ENTRY);
                        })
                        .placeAction(5, 8, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_EXIT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(50, 150);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.COLLEGE_ENTRY);
                        })
                        .placeAction(5, 9, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_EXIT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(50, 200);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.COLLEGE_ENTRY);
                        });
                break;
            case COLLEGE_ENTRY:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(0, 4, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_EXIT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(200, 450);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.OUTSIDE);
                        })
                        .placeAction(0, 3, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_EXIT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(200, 400);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.OUTSIDE);
                        })
                        .placeAction(0, 2, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_EXIT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(200, 350);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.OUTSIDE);
                        })
                        .placeAction(11, 3, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_RIGHT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(50, 500);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.COLLEGE_HALL);
                        });
                break;
            case COLLEGE_HALL:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(0, 10, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_RIGHT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(500, 150);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.COLLEGE_ENTRY);
                        })
                        .placeAction(2, 0, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(100, 200);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.CLASSES);
                        });
                break;
            case CLASSES:
                GameContext.getInstance().getRunnableLocation().getLocation()
                        .placeAction(2, 5, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_FRONT.getTexture()), () -> {
                            GameContext.getInstance().getPlayer().setLocation(100, 50);
                            GameContext.getInstance().setRunnableLocation(LocationLibrary.COLLEGE_HALL);
                        });
                break;
        }
    }
}
