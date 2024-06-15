package by.garkaviy.game.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum TextureLib {
    STONE_WALL(new Texture(Gdx.files.internal("texture-pack/Stone/Stone_08-256x256.png"))),
    STONE_SHADOWED(new Texture(Gdx.files.internal("texture-pack/Stone/Stone_08_Shadow-256x256.png"))),
    WOOD_FLOOR(new Texture(Gdx.files.internal("texture-pack/Wood/Wood_04-256x256.png"))),
    WOOD_FLOOR2(new Texture(Gdx.files.internal("texture-pack/Wood/Wood_13-256x256.png"))),
    WOOD_FLOOR3(new Texture(Gdx.files.internal("texture-pack/Wood/Wood_19-256x256.png"))),
    WOOD_FLOOR4(new Texture(Gdx.files.internal("texture-pack/Wood/Wood_05-256x256.png"))),
    STAIRS_DOWN(new Texture(Gdx.files.internal("texture-pack/Stairs/Stairs_01-256x256.png"))),
    STAIRS_UP(new Texture(Gdx.files.internal("texture-pack/Stairs/Stairs_02-256x256.png"))),
    STONE_STAIRS_RIGHT(new Texture(Gdx.files.internal("texture-pack/Stairs/Stairs_03-256x256.png"))),
    GRASS(new Texture(Gdx.files.internal("texture-pack/Dirt/Dirt_19-256x256.png"))),
    BRICK(new Texture(Gdx.files.internal("texture-pack/Brick/Brick_08-256x256.png"))),
    HALL_FLOOR(new Texture(Gdx.files.internal("texture-pack/Plaster/Plaster_08-256x256.png"))),
    HALL_FLOOR_SHADOWED(new Texture(Gdx.files.internal("texture-pack/Plaster/Plaster_08_Shadow-256x256.png")));

    private final Texture texture;

    TextureLib(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
