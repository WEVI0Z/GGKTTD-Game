package by.garkaviy.game.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public enum PlayerState {
    FRONT_IDLE(PlayerAnimationController.frontIdle),
    BACK_IDLE(PlayerAnimationController.backIdle),
    LEFT_IDLE(PlayerAnimationController.leftIdle),
    RIGHT_IDLE(PlayerAnimationController.rightIdle),
    FRONT_WALKING(PlayerAnimationController.frontWalking),
    BACK_WALKING(PlayerAnimationController.backWalking),
    LEFT_WALKING(PlayerAnimationController.leftWalking),
    RIGHT_WALKING(PlayerAnimationController.rightWalking);

    private final Animation<TextureRegion> animation;

    PlayerState(Animation<TextureRegion> animation) {
        this.animation = animation;
    }
}
