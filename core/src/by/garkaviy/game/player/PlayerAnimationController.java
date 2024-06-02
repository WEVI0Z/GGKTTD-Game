package by.garkaviy.game.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimationController {
    private static final Float ANIMATION_DURATION = 0.1f;
    private static final String PLAYER_PACK_PATH = "player/player.atlas";
    private static final TextureAtlas PLAYER_ATLAS = new TextureAtlas(PLAYER_PACK_PATH);

    public static final Animation<TextureRegion> frontIdle = getAnimation("spr_player_front_idle");
    public static final Animation<TextureRegion> backIdle = getAnimation("spr_player_back_idle");
    public static final Animation<TextureRegion> leftIdle = getAnimation("spr_player_left_idle");
    public static final Animation<TextureRegion> rightIdle = getAnimation("spr_player_right_idle");
    public static final Animation<TextureRegion> frontWalking = getAnimation("spr_player_front_walk");
    public static final Animation<TextureRegion> backWalking = getAnimation("spr_player_back_walk");
    public static final Animation<TextureRegion> leftWalking = getAnimation("spr_player_left_walk");
    public static final Animation<TextureRegion> rightWalking = getAnimation("spr_player_right_walk");

    private static Animation<TextureRegion> getAnimation(String regionName) {
        return new Animation<>(ANIMATION_DURATION, concatRegions(PLAYER_ATLAS.findRegion(regionName)));
    };

    private static TextureRegion[] concatRegions(TextureRegion textureRegion) {
        return concatRegions(textureRegion, 64, 64);
    }

    private static TextureRegion[] concatRegions(TextureRegion textureRegion, int rows, int cols) {
        TextureRegion[][] tmp = textureRegion.split(rows, cols);

        TextureRegion[] frames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;

        for (TextureRegion[] textureRegions : tmp) {
            for (TextureRegion region : textureRegions) {
                frames[index++] = region;
            }
        }

        return frames;
    }
}
