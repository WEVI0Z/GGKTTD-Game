package by.garkaviy.game.location;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class LocationBuilder {
    public final static int TEXTURE_SIZE = 50;
    private int xTileSize = 0;
    private int yTileSize = 0;
    private final ArrayList<ArrayList<TileEntity>> locationMap = new ArrayList<>();

    public LocationBuilder setSize(int xTiles, int yTiles) {
        this.xTileSize = xTiles;
        this.yTileSize = yTiles;

        fillWithEmpty();

        return this;
    }

    private void fillWithEmpty() {
        for (int i = 0; i < xTileSize; i++) {
            ArrayList<TileEntity> yTiles = new ArrayList<>();

            for (int j = 0; j < yTileSize; j++) {
                yTiles.add(new TileEntity(TileType.EMPTY));
            }

            locationMap.add(yTiles);
        }
    }

    public LocationBuilder fillWithTile(TileEntity tileEntity) {
        for (int i = 0; i < xTileSize; i++) {
            for (int j = 0; j < yTileSize; j++) {
                locationMap.get(i).get(j).setTitle(tileEntity);
            }
        }

        return this;
    }

    public void render(Batch batch) {
        batch.begin();
        for (int i = 0; i < xTileSize; i++) {
            for (int j = 0; j < yTileSize; j++) {
                TileEntity tileEntity = locationMap.get(i).get(j);
                if (!tileEntity.getTileType().equals(TileType.EMPTY)) {
                    batch.draw(tileEntity.getTexture(), TEXTURE_SIZE * i,
                            TEXTURE_SIZE * j, TEXTURE_SIZE, TEXTURE_SIZE);
                }
            }
        }
        batch.end();
    }

    public LocationBuilder placeTile(int xTile, int yTile, TileEntity tileEntity) {
        locationMap.get(xTile).get(yTile).setTitle(tileEntity);

        return this;
    }

    public int getXTileSize() {
        return xTileSize;
    }

    public int getYTileSize() {
        return yTileSize;
    }
}
