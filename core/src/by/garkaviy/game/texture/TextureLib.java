package by.garkaviy.game.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum TextureLib {
    STONE_WALL(new Texture(Gdx.files.internal("college-walls.png"))),
    STONE_SHADOWED(new Texture(Gdx.files.internal("wall-shadowed.png"))),
    WOOD_FLOOR(new Texture(Gdx.files.internal("wood.png"))),
    WOOD_FLOOR2(new Texture(Gdx.files.internal("texture-pack/Wood/Wood_13-256x256.png"))),
    WOOD_FLOOR3(new Texture(Gdx.files.internal("texture-pack/Wood/Wood_19-256x256.png"))),
    WOOD_FLOOR4(new Texture(Gdx.files.internal("texture-pack/Wood/Wood_05-256x256.png"))),
    STAIRS_DOWN(new Texture(Gdx.files.internal("stairs-down.png"))),
    STAIRS_UP(new Texture(Gdx.files.internal("texture-pack/Stairs/Stairs_02-256x256.png"))),
    STONE_STAIRS_RIGHT(new Texture(Gdx.files.internal("stone-stairs-right.png"))),
    STAIRS_TOP(new Texture(Gdx.files.internal("stairs-top.png"))),
    STAIRS_BOTTOM(new Texture(Gdx.files.internal("stairs-bottom.png"))),
    GRASS(new Texture(Gdx.files.internal("grass.png"))),
    BRICK(new Texture(Gdx.files.internal("stone-walk.png"))),
    ROAD_TOP(new Texture(Gdx.files.internal("road-top.png"))),
    ROAD_MIDDLE(new Texture(Gdx.files.internal("road-middle.png"))),
    ROAD_BOTTOM(new Texture(Gdx.files.internal("road-bottom.png"))),
    CROSSWALK_TOP(new Texture(Gdx.files.internal("crosswalk-top.png"))),
    CROSSWALK_BOTTOM(new Texture(Gdx.files.internal("crosswalk-bottom.png"))),
    CROSSWALK_MIDDLE(new Texture(Gdx.files.internal("crosswalk-middle.png"))),
    HALL_FLOOR(new Texture(Gdx.files.internal("college-floor.png"))),
    HALL_FLOOR_SHADOWED(new Texture(Gdx.files.internal("college-floor.png"))),
    ROOF_CORNER_LEFT(new Texture(Gdx.files.internal("roof-corner-left.png"))),
    ROOF_BOTTOM(new Texture(Gdx.files.internal("roof-bottom.png"))),
    ROOF_TOP(new Texture(Gdx.files.internal("roof-top.png"))),
    ROOF_LEFT(new Texture(Gdx.files.internal("roof-left.png"))),
    ROOF_RIGHT(new Texture(Gdx.files.internal("roof-right.png"))),
    ROOF_BOTTOM_RIGHT(new Texture(Gdx.files.internal("roof-bottom-right-corner.png"))),
    ROOF_BOTTOM_LEFT(new Texture(Gdx.files.internal("roof-bottom-left-corner.png"))),
    ROOF_TOP_RIGHT(new Texture(Gdx.files.internal("roof-top-right-corner.png"))),
    ROOF_TOP_LEFT(new Texture(Gdx.files.internal("roof-top-left-corner.png"))),
    COLLEGE_DOOR_FRONT(new Texture(Gdx.files.internal("college-door-front.png"))),
    COLLEGE_DOOR_RIGHT(new Texture(Gdx.files.internal("college-door-right.png"))),
    COLLEGE_EXIT(new Texture(Gdx.files.internal("college-exit.png"))),
    COLLEGE_DOOR_BACK(new Texture(Gdx.files.internal("college-door-back.png")));

    private final Texture texture;

    TextureLib(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
