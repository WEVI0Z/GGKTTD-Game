package by.garkaviy.game.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestLibrary {
    private final List<TestEntity> tests;

    public TestLibrary() {
        tests = new ArrayList<>();
        tests.add(new TestEntity(List.of(new QuestionEntity("Что означает аббревиатура CPU?", List.of(
                        new AnswerEntity("Процессор", true),
                        new AnswerEntity("Контроллер", false),
                        new AnswerEntity("Память", false),
                        new AnswerEntity("Устройство", false)
                )),
                new QuestionEntity("Какой язык является языком программирования?", List.of(
                        new AnswerEntity("HTML", false),
                        new AnswerEntity("Python", true),
                        new AnswerEntity("CSS", false),
                        new AnswerEntity("XML", false)
                )),
                new QuestionEntity("Как называется основное устройство для хранения данных?", List.of(
                        new AnswerEntity("Дисковод", false),
                        new AnswerEntity("Процессор", false),
                        new AnswerEntity("Диск", true),
                        new AnswerEntity("Модем", false)
                )),
                new QuestionEntity("Что такое RAM?", List.of(
                        new AnswerEntity("Процессор", false),
                        new AnswerEntity("Устройство", false),
                        new AnswerEntity("Сеть", false),
                        new AnswerEntity("Память", true)
                ))
        )));

        tests.add(new TestEntity(List.of(new QuestionEntity("Какой язык используется для создания веб-страниц?", List.of(
                        new AnswerEntity("HTML", true),
                        new AnswerEntity("Python", false),
                        new AnswerEntity("Java", false),
                        new AnswerEntity("C++", false)
                )),
                new QuestionEntity("Какая структура данных является линейной?", List.of(
                        new AnswerEntity("Дерево", false),
                        new AnswerEntity("Список", true),
                        new AnswerEntity("Граф", false),
                        new AnswerEntity("Сеть", false)
                )),
                new QuestionEntity("Как называется цикл с предусловием?", List.of(
                        new AnswerEntity("For", false),
                        new AnswerEntity("Loop", false),
                        new AnswerEntity("While", true),
                        new AnswerEntity("Until", false)
                )),
                new QuestionEntity("Какой тип данных используется для хранения истинных или ложных значений?", List.of(
                        new AnswerEntity("String", false),
                        new AnswerEntity("Integer", false),
                        new AnswerEntity("Float", false),
                        new AnswerEntity("Boolean", true)
                ))
        )));

        tests.add(new TestEntity(List.of(new QuestionEntity("Как называется устройство для вывода информации на экран?", List.of(
                        new AnswerEntity("Монитор", true),
                        new AnswerEntity("Клавиатура", false),
                        new AnswerEntity("Мышь", false),
                        new AnswerEntity("Принтер", false)
                )),
                new QuestionEntity("Какой компонент отвечает за графическую обработку?", List.of(
                        new AnswerEntity("Процессор", false),
                        new AnswerEntity("Видеокарта ", true),
                        new AnswerEntity("Память", false),
                        new AnswerEntity("Звуковая", false)
                )),
                new QuestionEntity("Как называется высокоскоростная внутренняя память?", List.of(
                        new AnswerEntity("ROM", false),
                        new AnswerEntity("SSD", false),
                        new AnswerEntity("Hash", true),
                        new AnswerEntity("HDD", false)
                )),
                new QuestionEntity("Какое устройство используется для ввода данных?", List.of(
                        new AnswerEntity("Принтер", false),
                        new AnswerEntity("Монитор", false),
                        new AnswerEntity("Колонки", false),
                        new AnswerEntity("Клавиатура", true)
                ))
        )));

        tests.add(new TestEntity(List.of(new QuestionEntity("Как называется уникальный идентификатор компьютера в сети?", List.of(
                        new AnswerEntity("IP", true),
                        new AnswerEntity("URL", false),
                        new AnswerEntity("HTML", false),
                        new AnswerEntity("DNS", false)
                )),
                new QuestionEntity("Какое устройство соединяет несколько сетей?", List.of(
                        new AnswerEntity("Коммутатор", false),
                        new AnswerEntity("Маршрутизатор", true),
                        new AnswerEntity("Хаб", false),
                        new AnswerEntity("Мост", false)
                )),
                new QuestionEntity("Какой протокол используется для передачи веб-страниц?", List.of(
                        new AnswerEntity("FTP", false),
                        new AnswerEntity("SMTP", false),
                        new AnswerEntity("HTTP", true),
                        new AnswerEntity("SNMP", false)
                )),
                new QuestionEntity("Какое устройство обеспечивает беспроводное подключение?", List.of(
                        new AnswerEntity("Провод", false),
                        new AnswerEntity("Свитч", false),
                        new AnswerEntity("Модем", false),
                        new AnswerEntity("Роутер", true)
                ))
        )));
    }
}
