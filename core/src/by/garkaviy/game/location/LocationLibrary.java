package by.garkaviy.game.location;

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
    OUTSIDE((new Location()
            .setSize(20, 20)
            .fillWithBackground(TextureLib.GRASS.getTexture())
            .generateWalls(TextureLib.GRASS.getTexture())));

    private final Location location;

    LocationLibrary(Location location) {
        this.location = location;
    }
    }
