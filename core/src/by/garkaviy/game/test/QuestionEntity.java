package by.garkaviy.game.test;

import lombok.Data;

import java.util.List;

@Data
public class QuestionEntity {
    private final String question;
    private final List<AnswerEntity> answers;
}
