package by.garkaviy.game.location;

import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.script.GGKTTDScript;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Location {
    public final static int TEXTURE_SIZE = 50;
    private int xTileSize = 0;
    private int yTileSize = 0;
    private int xStartPos = 0;
    private int yStartPos = 0;
    private final ArrayList<ArrayList<TileEntity>> locationMap = new ArrayList<>();
    private boolean isFirst = true;
    private String title = "Undefined location";

    public Location setTitle(String title) {
        this.title = title;
        return this;
    }

    public Location setStartPoint(int xStartPos, int yStartPos) {
        this.xStartPos = xStartPos;
        this.yStartPos = yStartPos;

        return this;
    }

    public Location setSize(int xTiles, int yTiles) {
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

    public Location fillWithTile(TileEntity tileEntity) {
        for (int i = 0; i < xTileSize; i++) {
            for (int j = 0; j < yTileSize; j++) {
                locationMap.get(i).get(j).setTile(tileEntity);
            }
        }

        return this;
    }

    public Location fillWithBackground(Texture texture) {
        for (int i = 0; i < xTileSize; i++) {
            for (int j = 0; j < yTileSize; j++) {
                locationMap.get(i).get(j).setTile(new TileEntity(TileType.BACKGROUND, texture,
                        TEXTURE_SIZE * i + xStartPos, TEXTURE_SIZE * j + yStartPos));
            }
        }

        return this;
    }

    public Location generateWalls(Texture texture) {
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

    public Location fillSquare(TileEntity tileEntity, int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                locationMap.get(i).get(j).setTile(new TileEntity(tileEntity.getTileType(), tileEntity.getTexture(),
                        TEXTURE_SIZE * i + xStartPos, TEXTURE_SIZE * j + yStartPos));
            }
        }

        return this;
    }

    public void render(Batch batch) {
        batch.begin();
        locationMap.forEach(row -> row.forEach(tile -> tile.render(batch)));
        batch.end();
    }

    public void renderHouse() {
        fillWithBackground(GameContext.getInstance().getRoomTexture());
        generateWalls(TextureLib.STONE_WALL.getTexture());
    }

    public void renderDormRoom(Batch batch) {
        batch.begin();
        if (isFirst) {
            renderHouse();
            isFirst = false;
        }
        locationMap.forEach(row -> row.forEach(tile -> tile.render(batch)));
        batch.end();
    }

    public Location placeTile(int xTile, int yTile, TileEntity tileEntity) {
        TileEntity tile = new TileEntity(tileEntity.getTileType(), tileEntity.getTexture(),
                TEXTURE_SIZE * xTile + xStartPos, TEXTURE_SIZE * yTile + yStartPos);

        locationMap.get(xTile).get(yTile).setTile(tile);

        return this;
    }

    public Location placeAction(int xTile, int yTile, TileEntity tileEntity, GGKTTDScript script) {
        TileEntity tile = new TileEntity(tileEntity.getTileType(), tileEntity.getTexture(),
                TEXTURE_SIZE * xTile + xStartPos, TEXTURE_SIZE * yTile + yStartPos, script);

        locationMap.get(xTile).get(yTile).setTile(tile);

        return this;
    }

    public List<TileEntity> getWalls() {
        return getTilesByType(TileType.WALL);
    }

    public List<TileEntity> getActions() {
        return getTilesByType(TileType.ACTION);
    }

    public List<TileEntity> getTilesByType(TileType tileType) {
        return locationMap.stream()
                .flatMap(List::stream)
                .filter(tile -> tile.getTileType().equals(tileType))
                .collect(Collectors.toList());
    }

    public int getXTileSize() {
        return xTileSize;
    }

    public int getYTileSize() {
        return yTileSize;
    }
}
