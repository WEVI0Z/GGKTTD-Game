package by.garkaviy.game.location;

import by.garkaviy.game.texture.TextureLib;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
@Setter
public class CarController {

    private final List<Texture> cars = List.of(TextureLib.CAR_1.getTexture(), TextureLib.CAR_2.getTexture(), TextureLib.CAR_3.getTexture());
    private final List<Texture> reversedCars = List.of(TextureLib.CAR_1_REVERSED.getTexture(), TextureLib.CAR_2_REVERSED.getTexture(), TextureLib.CAR_3_REVERSED.getTexture());
    private Texture car;
    private final Texture whitespace = TextureLib.WHITESPACE.getTexture();
    private final int xCord;
    private final int yCord;
    private final int width;
    private final int height;
    private final int mapWidth;
    private final int mapHeight;
    private boolean isReversed;

    private int currentPos;

    public CarController(int xCord, int yCord, int width, int height, int mapWidth, int mapHeight, boolean isReversed) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.width = width;
        this.height = height;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.isReversed = isReversed;
        this.currentPos = isReversed ? -width : xCord;
        this.car = isReversed ? pickRandomElement(reversedCars) : pickRandomElement(cars);
    }

    public void render(Batch batch) {
        float offset = 200 * Gdx.graphics.getDeltaTime();

        if (!isReversed) {
            currentPos -= (int) offset;
            if (currentPos < -1 * width) {
                currentPos = xCord;
                car = pickRandomElement(cars);
            }
        } else {
            currentPos += (int) offset;
            if (currentPos > mapWidth + width) {
                currentPos = -width;
                car = pickRandomElement(reversedCars);
            }
        }

        batch.draw(car, currentPos, yCord, width, height);
        batch.draw(whitespace, mapWidth, 0, 500, 500);
        batch.draw(whitespace, -500, 0, 500, 500);
    }

    private <T> T pickRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
