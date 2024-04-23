package by.garkaviy.game.location;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationBuilder {
    public final static int TEXTURE_SIZE = 50;
    private int xTileSize = 0;
    private int yTileSize = 0;
    private int xStartPos = 0;
    private int yStartPos = 0;
    private final ArrayList<ArrayList<TileEntity>> locationMap = new ArrayList<>();

    public static LocationBuilder getInstance() {
        return new LocationBuilder();
    }

    public LocationBuilder setStartPoint(int xStartPos, int yStartPos) {
        this.xStartPos = xStartPos;
        this.yStartPos = yStartPos;

        return this;
    }

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
                locationMap.get(i).get(j).setTile(tileEntity);
            }
        }

        return this;
    }

    public LocationBuilder fillWithBackground(Texture texture) {
        for (int i = 0; i < xTileSize; i++) {
            for (int j = 0; j < yTileSize; j++) {
                locationMap.get(i).get(j).setTile(new TileEntity(TileType.BACKGROUND, texture,
                        TEXTURE_SIZE * i + xStartPos, TEXTURE_SIZE * j + yStartPos));
            }
        }

        return this;
    }

    public LocationBuilder generateWalls(Texture texture) {
        for (int i = 0; i < xTileSize; i++) {
            locationMap.get(i).get(0).setTile(new TileEntity(TileType.WALL, texture,
                    TEXTURE_SIZE * i + xStartPos, 0));

            locationMap.get(i).get(yTileSize - 1).setTile(new TileEntity(TileType.WALL, texture,
                    TEXTURE_SIZE * i + xStartPos, (yTileSize - 1) * TEXTURE_SIZE));
        }

        for (int i = 0; i < yTileSize; i++) {
            locationMap.get(0).get(i).setTile(new TileEntity(TileType.WALL, texture,
                    0, i * TEXTURE_SIZE + xStartPos));

            locationMap.get(xTileSize - 1).get(i).setTile(new TileEntity(TileType.WALL, texture,
                    (xTileSize - 1) * TEXTURE_SIZE, i * TEXTURE_SIZE + xStartPos));
        }

        return this;
    }

    public void render(Batch batch) {
        batch.begin();
        locationMap.forEach(row -> row.forEach(tile -> tile.render(batch)));
        batch.end();
    }

    public LocationBuilder placeTile(int xTile, int yTile, TileEntity tileEntity) {
        TileEntity tile = new TileEntity(tileEntity.getTileType(), tileEntity.getTexture(),
                TEXTURE_SIZE * xTile + xStartPos, TEXTURE_SIZE * yTile + yStartPos);

        locationMap.get(xTile).get(yTile).setTile(tile);

        return this;
    }

    public List<TileEntity> getWalls() {
        return locationMap.stream()
                .flatMap(List::stream)
                .filter(tile -> tile.getTileType().equals(TileType.WALL))
                .collect(Collectors.toList());
    }

    public int getXTileSize() {
        return xTileSize;
    }

    public int getYTileSize() {
        return yTileSize;
    }
}
