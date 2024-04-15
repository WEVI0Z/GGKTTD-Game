package by.garkaviy.game.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum TextureLib {
    COBBLESTONE(new Texture(Gdx.files.internal("texture-pack/Tile/Tile_01-256x256.png"))),
    STONE_WALL(new Texture(Gdx.files.internal("texture-pack/Stone/Stone_08-256x256.png"))),
    PLAYER(new Texture(Gdx.files.internal("drop.png")));

    private final Texture texture;

    private TextureLib(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
