package by.garkaviy.game.location;

import by.garkaviy.game.script.GGKTTDScript;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;

@Getter
public class TileEntity {
    public static final int STANDARD_WIDTH = 50;
    public static final int STANDARD_HEIGHT = 50;
    public static final int STANDARD_Y_CORD = 0;
    public static final int STANDARD_X_CORD = 0;

    private Texture texture = null;
    private TileType tileType;
    private GGKTTDScript script;
    private int width = STANDARD_WIDTH;
    private int height = STANDARD_HEIGHT;
    private int xCord = STANDARD_X_CORD;
    private int yCord = STANDARD_Y_CORD;

    public TileEntity(TileType tileType, Texture texture) {
        this.tileType = tileType;
        this.texture = texture;
    }

    public TileEntity(TileType tileType, Texture texture, int width, int height, int xCord, int yCord) {
        this.tileType = tileType;
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public TileEntity(TileType tileType, Texture texture,
                      int width, int height, int xCord, int yCord,
                      GGKTTDScript script) {
        this.tileType = tileType;
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.xCord = xCord;
        this.yCord = yCord;
        this.script = script;
    }

    public TileEntity(TileType tileType, Texture texture, int xCord, int yCord) {
        this.tileType = tileType;
        this.texture = texture;
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public TileEntity(TileType tileType, Texture texture, int xCord, int yCord, GGKTTDScript script) {
        this.tileType = tileType;
        this.texture = texture;
        this.xCord = xCord;
        this.yCord = yCord;
        this.script = script;
    }

    public TileEntity(TileType tileType) {
        this.tileType = tileType;
    }

    public void setTile(TileEntity tileEntity) {
        this.texture = tileEntity.texture;
        this.tileType = tileEntity.tileType;
        this.width = tileEntity.width;
        this.height = tileEntity.height;
        this.xCord = tileEntity.xCord;
        this.yCord = tileEntity.yCord;
        this.script = tileEntity.script;
    }

    public void render(Batch batch) {
        if (!tileType.equals(TileType.EMPTY)) {
            batch.draw(texture, xCord, yCord, width, height);
        }
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }
}
