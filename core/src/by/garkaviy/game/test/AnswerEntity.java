package by.garkaviy.game.test;

import lombok.Data;

@Data
public class AnswerEntity {
    private final String text;
    private final boolean isRight;

    AnswerEntity(String text) {
        this.text = text;
        isRight = false;
    }

    AnswerEntity(String text, boolean isRight) {
        this.text = text;
        this.isRight = isRight;
    }
}
