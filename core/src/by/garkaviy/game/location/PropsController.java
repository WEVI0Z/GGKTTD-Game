package by.garkaviy.game.location;

import by.garkaviy.game.context.GameContext;
import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropsController {

    public static void render(Batch batch) {
        batch.begin();

        switch (GameContext.getInstance().getRunnableLocation()) {
            case OUTSIDE:
                batch.draw(TextureLib.WINDOW_1.getTexture(), 285, 260, 30, 30);
                batch.draw(TextureLib.WINDOW_1.getTexture(), 385, 260, 30, 30);
                batch.draw(TextureLib.WINDOW_1.getTexture(), 485, 260, 30, 30);
                batch.draw(TextureLib.WINDOW_1.getTexture(), 585, 260, 30, 30);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 550, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 500, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 450, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 400, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 350, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 300, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 250, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1050, 200, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 1000, 200, 50, 100);
                batch.draw(TextureLib.TREE.getTexture(), 0, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 50, 550, 50, 50);
                batch.draw(TextureLib.TREE.getTexture(), 100, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 150, 550, 50, 50);
                batch.draw(TextureLib.TREE.getTexture(), 200, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 250, 550, 50, 50);
                batch.draw(TextureLib.TREE.getTexture(), 300, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 350, 550, 50, 50);
                batch.draw(TextureLib.TREE.getTexture(), 400, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 450, 550, 50, 50);
                batch.draw(TextureLib.TREE.getTexture(), 500, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 550, 550, 50, 50);
                batch.draw(TextureLib.TREE.getTexture(), 600, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 650, 550, 50, 50);
                batch.draw(TextureLib.TREE.getTexture(), 700, 550, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 750, 550, 50, 50);
                batch.draw(TextureLib.BARRICADE.getTexture(), 0, 510, 125, 50);
                batch.draw(TextureLib.BARRICADE.getTexture(), 125, 510, 125, 50);
                batch.draw(TextureLib.BARRICADE.getTexture(), 650, 510, 150, 50);
                batch.draw(TextureLib.LIGHT.getTexture(), 20, 460, 50, 100);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 200, 250, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 200, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 250, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 300, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 350, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 400, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 450, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 500, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 550, 200, 50, 50);
                batch.draw(TextureLib.SMALL_TREE.getTexture(), 600, 200, 50, 50);
                batch.draw(TextureLib.SEAT_DOWN.getTexture(), 70, 460, 100, 50);
                batch.draw(TextureLib.GARBAGE.getTexture(), 967, 407, 25, 25);
                batch.draw(TextureLib.SEAT_LEFT.getTexture(), 965, 380, 30, 30);
                batch.draw(TextureLib.SEAT_LEFT.getTexture(), 965, 362, 30, 30);
                batch.draw(TextureLib.SEAT_RIGHT.getTexture(), 855, 260, 30, 30);
                batch.draw(TextureLib.SEAT_RIGHT.getTexture(), 855, 242, 30, 30);
                break;
            case COLLEGE_ENTRY:
                batch.draw(TextureLib.WINDOW_2.getTexture(), 80, 310, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 180, 310, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 280, 310, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 380, 310, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 480, 310, 30, 30);
                break;
            case COLLEGE_HALL:
                batch.draw(TextureLib.WINDOW_2.getTexture(), 65, 610, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 165, 610, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 265, 610, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 365, 610, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 465, 610, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 565, 610, 30, 30);
                break;
            case CLASSES:
                batch.draw(TextureLib.WINDOW_2.getTexture(), 190, 260, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 290, 260, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 390, 260, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 490, 260, 30, 30);
                batch.draw(TextureLib.WINDOW_2.getTexture(), 590, 260, 30, 30);
                break;
            case DORM_ROOM:
                batch.draw(GameContext.getInstance().getWindowTexture(), 140, 210, 35, 35);
                batch.draw(GameContext.getInstance().getBedTexture(), 50, 100, 50, 100);
        }

        batch.end();
    }
}
