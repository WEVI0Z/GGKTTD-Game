package by.garkaviy.game.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestLibrary {
    private final List<TestEntity> tests;

    public TestLibrary() {
        tests = new ArrayList<>();
        tests.add(new TestEntity(List.of(new QuestionEntity("Question 1", List.of(
                        new AnswerEntity("Answer 1", true),
                        new AnswerEntity("Answer 2", false),
                        new AnswerEntity("Answer 3", false),
                        new AnswerEntity("Answer 4", false)
                )),
                new QuestionEntity("Question 2", List.of(
                        new AnswerEntity("Answer 1", false),
                        new AnswerEntity("Answer 2", true),
                        new AnswerEntity("Answer 3", false),
                        new AnswerEntity("Answer 4", false)
                )),
                new QuestionEntity("Question 3", List.of(
                        new AnswerEntity("Answer 1", false),
                        new AnswerEntity("Answer 2", false),
                        new AnswerEntity("Answer 3", true),
                        new AnswerEntity("Answer 4", false)
                )),
                new QuestionEntity("Question 4", List.of(
                        new AnswerEntity("Answer 1", false),
                        new AnswerEntity("Answer 2", false),
                        new AnswerEntity("Answer 3", false),
                        new AnswerEntity("Answer 4", true)
                ))
        )));

        tests.add(new TestEntity(List.of(new QuestionEntity("1 Question 1", List.of(
                        new AnswerEntity("Answer 1", true),
                        new AnswerEntity("Answer 2", false),
                        new AnswerEntity("Answer 3", false),
                        new AnswerEntity("Answer 4", false)
                )),
                new QuestionEntity("1 Question 2", List.of(
                        new AnswerEntity("Answer 1", false),
                        new AnswerEntity("Answer 2", true),
                        new AnswerEntity("Answer 3", false),
                        new AnswerEntity("Answer 4", false)
                )),
                new QuestionEntity("1 Question 3", List.of(
                        new AnswerEntity("Answer 1", false),
                        new AnswerEntity("Answer 2", false),
                        new AnswerEntity("Answer 3", true),
                        new AnswerEntity("Answer 4", false)
                )),
                new QuestionEntity("1 Question 4", List.of(
                        new AnswerEntity("Answer 1", false),
                        new AnswerEntity("Answer 2", false),
                        new AnswerEntity("Answer 3", false),
                        new AnswerEntity("Answer 4", true)
                ))
        )));
    }
}
