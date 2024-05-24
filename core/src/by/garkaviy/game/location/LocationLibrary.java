package by.garkaviy.game.location;

import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.script.AnyScript;
import by.garkaviy.game.texture.TextureLib;
import lombok.Data;
import lombok.Getter;

@Getter
public enum LocationLibrary {
    FIRST_TEST_LOCATION(new Location()
            .setSize(10, 15)
            .fillWithBackground(TextureLib.COBBLESTONE.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture())
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture()))),
    SECOND_TEST_LOCATION(new Location()
            .setSize(10, 10)
            .fillWithBackground(TextureLib.COBBLESTONE.getTexture())
            .generateWalls(TextureLib.STONE_WALL.getTexture())
            .placeTile(5, 6, new TileEntity(TileType.WALL, TextureLib.STONE_WALL.getTexture())));

    private final Location location;

    LocationLibrary(Location location) {
        this.location = location;
    }
}
