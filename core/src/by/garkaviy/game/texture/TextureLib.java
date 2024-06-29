package by.garkaviy.game.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;

@Getter
public enum TextureLib {
    STONE_WALL(new Texture(Gdx.files.internal("college-walls.png"))),
    STONE_SHADOWED(new Texture(Gdx.files.internal("wall-shadowed.png"))),
    WOOD_FLOOR(new Texture(Gdx.files.internal("wood.png"))),
    WOOD_FLOOR2(new Texture(Gdx.files.internal("wood-2.png"))),
    WOOD_FLOOR3(new Texture(Gdx.files.internal("wood-3.png"))),
    WOOD_FLOOR4(new Texture(Gdx.files.internal("wood-4.png"))),
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
    COLLEGE_DOOR_BACK(new Texture(Gdx.files.internal("college-door-back.png"))),
    WHITESPACE(new Texture(Gdx.files.internal("whitespace.png"))),
    GARBAGE(new Texture(Gdx.files.internal("garbage.png"))),
    TREE(new Texture(Gdx.files.internal("tree.png"))),
    SMALL_TREE(new Texture(Gdx.files.internal("small-tree.png"))),
    BARRICADE(new Texture(Gdx.files.internal("barricade.png"))),
    LIGHT(new Texture(Gdx.files.internal("light.png"))),
    SEAT_LEFT(new Texture(Gdx.files.internal("seat.png"))),
    SEAT_RIGHT(new Texture(Gdx.files.internal("seat-right.png"))),
    SEAT_DOWN(new Texture(Gdx.files.internal("seat-down.png"))),
    WINDOW_1(new Texture(Gdx.files.internal("window-1.png"))),
    WINDOW_2(new Texture(Gdx.files.internal("window-2.png"))),
    BED_1(new Texture(Gdx.files.internal("bed-1.png"))),
    BED_2(new Texture(Gdx.files.internal("bed-2.png"))),
    CAR_1(new Texture(Gdx.files.internal("car-1.png"))),
    CAR_2(new Texture(Gdx.files.internal("car-2.png"))),
    CAR_3(new Texture(Gdx.files.internal("car-3.png"))),
    CAR_1_REVERSED(new Texture(Gdx.files.internal("car-1-reversed.png"))),
    CAR_2_REVERSED(new Texture(Gdx.files.internal("car-2-reversed.png"))),
    CAR_3_REVERSED(new Texture(Gdx.files.internal("car-3-reversed.png"))),
    BLUE_BUTTON(new Texture(Gdx.files.internal("button_rectangle_depth_border.png"))),
    GRAY_SQUARE(new Texture(Gdx.files.internal("button_square_border.png"))),
    BLUE_SQUARE(new Texture(Gdx.files.internal("blue-square.png"))),
    ARROW_RIGHT(new Texture(Gdx.files.internal("arrow_decorative_e.png"))),
    ARROW_LEFT(new Texture(Gdx.files.internal("arrow_decorative_w.png"))),
    RED_SQUARE(new Texture(Gdx.files.internal("red-square.png"))),
    RED_BUTTON(new Texture(Gdx.files.internal("red-button_rectangle_depth_border.png"))),
    GREY_FLAT_BUTTON(new Texture(Gdx.files.internal("button_rectangle_flat.png"))),
    MONITOR(new Texture(Gdx.files.internal("monitor.png"))),
    BOOK(new Texture(Gdx.files.internal("book.png"))),
    COLLEGE_IMAGE(new Texture(Gdx.files.internal("college-image.png"))),
    COLLEGE_IMAGE_2(new Texture(Gdx.files.internal("college-image-2.png"))),
    TEXT_MARKER(new Texture(Gdx.files.internal("text-marker.png"))),
    HUMAN(new Texture(Gdx.files.internal("human.png"))),
    WORKSPACE(new Texture(Gdx.files.internal("workspace.png"))),
    WORKSPACE_2(new Texture(Gdx.files.internal("workspace-2.png"))),
    WORKSPACE_3(new Texture(Gdx.files.internal("workspace-3.png"))),
    WORKSPACE_4(new Texture(Gdx.files.internal("workspace-4.png")));

    private final Texture texture;

    TextureLib(Texture texture) {
        this.texture = texture;
    }

}
