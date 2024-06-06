package by.garkaviy.game.location;

import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.script.AnyScript;
import by.garkaviy.game.script.GGKTTDScript;
import by.garkaviy.game.texture.TextureLib;
import lombok.Getter;

@Getter
public enum LocationLibrary {
    FIRST_TEST_LOCATION(new Location()
            .setSize(10, 15)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture())
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))),
    SECOND_TEST_LOCATION(new Location()
            .setSize(10, 10)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture())
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))),
    DORM_ROOM((new Location()
            .setSize(6, 5)
            .fillWithBackground(TextureLib.WOOD_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    DORM_UPSTAIRS((new Location()
            .setSize(10, 5)
            .fillWithBackground(TextureLib.WOOD_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    DORM_HALL((new Location()
            .setSize(10, 5)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    DORM_EXIT((new Location()
            .setSize(7, 7)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    COLLEGE_ENTRY((new Location()
            .setSize(12, 7)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    COLLEGE_HALL(new Location()
            .setSize(13, 13)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.HALL_FLOOR.getTexture()), 1, 9, 11, 11)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.HALL_FLOOR.getTexture()), 1, 1, 3, 8)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 0, 12, 12, 12)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 0, 0, 0, 12)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 0, 0, 4, 0)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 4, 0, 4, 8)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 4, 8, 12, 8)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 12, 8, 12, 12)
            .placeTile(12, 10, new TileEntity(TileType.WALL, TextureLib.HALL_FLOOR_SHADOWED.getTexture()))),
    CLASSES((new Location()
            .setSize(14, 6)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))
            .placeAction(2, 0, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR_SHADOWED.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })
            .placeAction(5, 0, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR_SHADOWED.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })
            .placeAction(8, 0, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR_SHADOWED.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })
            .placeAction(11, 0, new TileEntity(TileType.ACTION, TextureLib.HALL_FLOOR_SHADOWED.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })),
    OUTSIDE((new Location()
            .setSize(20, 10)
            .fillWithBackground(TextureLib.GRASS.getTexture())
            .generateWalls(TextureLib.GRASS.getTexture()))
            .placeTile(15, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(16, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(18, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(19, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(4, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(5, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(6, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(7, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(8, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(9, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(10, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(11, 9, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(4, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(5, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(6, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(7, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(8, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(9, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(10, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(11, 8, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(4, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(5, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(6, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(7, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(8, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(9, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(10, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(11, 7, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(4, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(6, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(7, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(8, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(9, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(10, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(11, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(4, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(5, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(6, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(7, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(8, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(9, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(10, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(11, 5, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))
            .placeTile(3, 8, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_STAIRS_RIGHT.getTexture()))
            .placeTile(3, 7, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_STAIRS_RIGHT.getTexture()))
            .placeTile(3, 6, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_STAIRS_RIGHT.getTexture()))
            .placeTile(4, 8, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_SHADOWED.getTexture()))
            .placeTile(4, 7, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_SHADOWED.getTexture()))
            .placeTile(4, 6, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_SHADOWED.getTexture()))
            .placeTile(17, 8, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(17, 7, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(17, 6, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(17, 5, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(17, 4, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(17, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(16, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(15, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(14, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(13, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(12, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(11, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(10, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(9, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(8, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(7, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(6, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(5, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(4, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(3, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(2, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 3, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 4, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 5, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 6, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 7, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(2, 7, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(2, 8, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(2, 6, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 8, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 6, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(0, 7, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(0, 8, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(0, 6, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(0, 9, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 9, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(2, 9, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(3, 9, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(0, 5, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(1, 5, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(2, 5, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(3, 5, new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()))
            .placeTile(17, 9, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_SHADOWED.getTexture())));

    private final Location location;

    LocationLibrary(Location location) {
        this.location = location;
    }
}
