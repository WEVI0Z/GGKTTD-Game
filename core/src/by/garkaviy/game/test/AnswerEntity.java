package by.garkaviy.game.test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerEntity {
    private String text;
    private boolean isRight;

    AnswerEntity(String text) {
        this.text = text;
        isRight = false;
    }

    AnswerEntity(String text, boolean isRight) {
        this.text = text;
        this.isRight = isRight;
    }
}
