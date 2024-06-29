package by.garkaviy.game.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionEntity {
    private String question;
    private List<AnswerEntity> answers;

    public QuestionEntity() {
        // default constructor
    }
}
