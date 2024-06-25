package by.garkaviy.game.test;

import lombok.Data;

import java.util.List;

@Data
public class TestEntity {
    private final String title;
    private final List<QuestionEntity> questions;
}
