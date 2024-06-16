package by.garkaviy.game.location;

import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.texture.TextureLib;
import lombok.Getter;

@Getter
public enum LocationLibrary {
    FIRST_TEST_LOCATION(new Location()
            .setTitle("First test location")
            .setSize(10, 15)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture())
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))),
    SECOND_TEST_LOCATION(new Location()
            .setTitle("Second test location")
            .setSize(10, 10)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture())
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))),
    DORM_ROOM((new Location()
            .setTitle("Моя комната")
            .setSize(6, 5)
            .fillWithBackground(GameContext.getInstance().getRoomTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    DORM_UPSTAIRS((new Location()
            .setTitle("Общежитие: второй этаж")
            .setSize(10, 5)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    DORM_HALL((new Location()
            .setTitle("Общежитие: первый этаж")
            .setSize(10, 5)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    DORM_EXIT((new Location()
            .setTitle("Общежитие: выход")
            .setSize(7, 7)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    COLLEGE_ENTRY((new Location()
            .setTitle("Колледж: вход")
            .setSize(12, 7)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))),
    COLLEGE_HALL(new Location()
            .setTitle("Колледж: коридор")
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
            .setTitle("Колледж: аудитории")
            .setSize(14, 6)
            .fillWithBackground(TextureLib.HALL_FLOOR.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture()))
            .placeAction(2, 0, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })
            .placeAction(5, 0, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })
            .placeAction(8, 0, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })
            .placeAction(11, 0, new TileEntity(TileType.ACTION, TextureLib.COLLEGE_DOOR_BACK.getTexture()),
                    () -> {
                        GameContext.getInstance().setChangeToTest(true);
                    })),
    OUTSIDE(new Location()
            .setTitle("Улица")
            .setSize(22, 12)
            .fillWithBackground(TextureLib.GRASS.getTexture())
            .generateWalls(TextureLib.GRASS.getTexture())
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_SHADOWED.getTexture()), 16, 9, 20, 9)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.ROOF_BOTTOM.getTexture()), 17, 10, 19, 10)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 17, 11, 19, 11)
            .placeTile(16, 10, new TileEntity(TileType.WALL, TextureLib.ROOF_BOTTOM_LEFT.getTexture()))
            .placeTile(20, 10, new TileEntity(TileType.WALL, TextureLib.ROOF_BOTTOM_RIGHT.getTexture()))
            .placeTile(16, 11, new TileEntity(TileType.WALL, TextureLib.ROOF_LEFT.getTexture()))
            .placeTile(20, 11, new TileEntity(TileType.WALL, TextureLib.ROOF_RIGHT.getTexture()))
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()), 7, 7, 11, 9)
            .placeTile(5, 10, new TileEntity(TileType.WALL, TextureLib.ROOF_CORNER_LEFT.getTexture()))
            .placeTile(4, 9, new TileEntity(TileType.BACKGROUND, TextureLib.STAIRS_TOP.getTexture()))
            .placeTile(4, 8, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_STAIRS_RIGHT.getTexture()))
            .placeTile(4, 7, new TileEntity(TileType.BACKGROUND, TextureLib.STONE_STAIRS_RIGHT.getTexture()))
            .placeTile(4, 6, new TileEntity(TileType.BACKGROUND, TextureLib.STAIRS_BOTTOM.getTexture()))
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.ROOF_BOTTOM.getTexture()), 6, 6, 11, 6)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.ROOF_TOP.getTexture()), 6, 10, 11, 10)
            .placeTile(12, 10, new TileEntity(TileType.WALL, TextureLib.ROOF_TOP_RIGHT.getTexture()))
            .placeTile(12, 6, new TileEntity(TileType.WALL, TextureLib.ROOF_BOTTOM_RIGHT.getTexture()))
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.ROOF_CORNER_LEFT.getTexture()))
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.ROOF_LEFT.getTexture()), 6, 7, 6, 9)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.ROOF_RIGHT.getTexture()), 12, 7, 12, 9)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.STONE_SHADOWED.getTexture()), 5, 5, 12, 5)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()), 17, 4, 19, 8)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()), 0, 4, 3, 9)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.BRICK.getTexture()), 0, 3, 21, 3)
            .fillSquare(new TileEntity(TileType.WALL, TextureLib.ROAD_TOP.getTexture()), 0, 2, 21, 2)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.ROAD_MIDDLE.getTexture()), 0, 1, 21, 1)
            .fillSquare(new TileEntity(TileType.BACKGROUND, TextureLib.ROAD_BOTTOM.getTexture()), 0,  0, 21, 0)
            .placeTile(1, 2, new TileEntity(TileType.WALL, TextureLib.CROSSWALK_TOP.getTexture()))
            .placeTile(1, 1, new TileEntity(TileType.WALL, TextureLib.CROSSWALK_MIDDLE.getTexture()))
            .placeTile(1, 0, new TileEntity(TileType.WALL, TextureLib.CROSSWALK_BOTTOM.getTexture()))
            .placeTile(18, 2, new TileEntity(TileType.WALL, TextureLib.CROSSWALK_TOP.getTexture()))
            .placeTile(18, 1, new TileEntity(TileType.WALL, TextureLib.CROSSWALK_MIDDLE.getTexture()))
            .placeTile(18, 0, new TileEntity(TileType.WALL, TextureLib.CROSSWALK_BOTTOM.getTexture())));

    private final Location location;

    LocationLibrary(Location location) {
        this.location = location;
    }
}
