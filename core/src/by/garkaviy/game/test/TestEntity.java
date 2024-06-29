package by.garkaviy.game.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {
    private String part = "undefined";
    private String title;
    private List<QuestionEntity> questions;
}
