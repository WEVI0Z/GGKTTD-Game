package by.garkaviy.game.location;

import com.badlogic.gdx.graphics.Texture;

public class TileEntity {
    private Texture texture = null;
    private TileType tileType;

    public TileEntity(TileType tileType, Texture texture) {
        this.tileType = tileType;
        this.texture = texture;
    }

    public TileEntity(TileType tileType) {
        this.tileType = tileType;
    }

    public Texture getTexture() {
        return texture;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTitle(TileEntity tileEntity) {
        this.texture = tileEntity.texture;
        this.tileType = tileEntity.tileType;
    }
}
