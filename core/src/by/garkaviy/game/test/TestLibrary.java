package by.garkaviy.game.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TestLibrary {

    private static final Json json = new Json();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveTests() {
        saveTests(getTests());
        saveTests(getSecondTests());
        saveTests(getThirdTests());
        saveTests(getFourthTests());
    }

    private static void saveTests(List<TestEntity> tests) {
        tests.forEach(TestLibrary::saveTest);
    }

    public static void saveTest(TestEntity test) {
        try {
            String json = mapper.writeValueAsString(test);
            FileHandle file = Gdx.files.local("tests/" + test.getTitle() + ".json");

            try (OutputStream outputStream = new FileOutputStream(file.file());
                 Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                writer.write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TestEntity> load(String part) {
        FileHandle[] files = Gdx.files.local("tests").list();
        ObjectMapper mapper = new ObjectMapper();

        return Arrays.stream(files).map(file -> {
            try {
                return mapper.readValue(file.reader(StandardCharsets.UTF_8.toString()), TestEntity.class);
            } catch (Exception e) {
                Gdx.app.log("Parsing Exception", e.getMessage());
                TestEntity test = new TestEntity();
                test.setPart("undefined");
                return test;
            }
        }).filter(test -> test.getPart().equals(part)).collect(Collectors.toList());
    }

    public static void delete(String title) {
        FileHandle file = Gdx.files.local("tests/" + title + ".json"); // указываем путь к файлу
        file.delete();
    }


    public static List<String> getAllTitles() {
        List<TestEntity> tests = getTests();
        tests.addAll(getSecondTests());
        tests.addAll(getThirdTests());
        tests.addAll(getFourthTests());
        List<String> titles = new ArrayList<>();
        for (TestEntity test : tests) {
            titles.add(test.getTitle());
        }
        return titles;
    }

    public static List<TestEntity> getTests() {
        return load(PartsEnum.DATA_BASES.getTitle());
    }

    public static List<TestEntity> getSecondTests() {
        return load(PartsEnum.CONNECTIONS.getTitle());
    }

    public static List<TestEntity> getThirdTests() {
        return load(PartsEnum.PROGRAMMING.getTitle());
    }

    public static List<TestEntity> getFourthTests() {
        return load(PartsEnum.TECHNOLOGIES.getTitle());
    }

//    public static List<TestEntity> getTests() {
//        List<TestEntity> tests = new ArrayList<>();
//        tests.add(new TestEntity("Базы данных", "Основы баз данных", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
//                        new AnswerEntity("Структурированный", true),
//                        new AnswerEntity("Статический", false),
//                        new AnswerEntity("Системный", false),
//                        new AnswerEntity("Секвенциальный", false)
//                )),
//                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
//                        new AnswerEntity("Массив", false),
//                        new AnswerEntity("Таблица", true),
//                        new AnswerEntity("Дерево", false),
//                        new AnswerEntity("Список", false)
//                )),
//                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
//                        new AnswerEntity("Колонка", false),
//                        new AnswerEntity("Поле", false),
//                        new AnswerEntity("Ключ", true),
//                        new AnswerEntity("Запрос", false)
//                )),
//                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
//                        new AnswerEntity("Update", false),
//                        new AnswerEntity("Delete", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Select", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Базы данных", "Продвинутые концепции баз", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
//                        new AnswerEntity("Delete", true),
//                        new AnswerEntity("Select", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Update", false)
//                )),
//                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
//                        new AnswerEntity("Merge", false),
//                        new AnswerEntity("Join", true),
//                        new AnswerEntity("Split", false),
//                        new AnswerEntity("Divide", false)
//                )),
//                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
//                        new AnswerEntity("МногиекОдному", false),
//                        new AnswerEntity("ОдинкоОдному", false),
//                        new AnswerEntity("ОдинкоМногим", true),
//                        new AnswerEntity("МногиекМногим", false)
//                )),
//                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
//                        new AnswerEntity("Архивирование", false),
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг", false),
//                        new AnswerEntity("Восстановление", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Базы данных", "Архитектура баз данных", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
//                        new AnswerEntity("Хранилище", true),
//                        new AnswerEntity("Сервер", false),
//                        new AnswerEntity("Кластер", false),
//                        new AnswerEntity("Таблица", false)
//                )),
//                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг ", true),
//                        new AnswerEntity("Кэширование", false),
//                        new AnswerEntity("Индексация", false)
//                )),
//                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
//                        new AnswerEntity("Лог", false),
//                        new AnswerEntity("Индекс", false),
//                        new AnswerEntity("Кэш", true),
//                        new AnswerEntity("Буфер", false)
//                )),
//                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
//                        new AnswerEntity("Сетевая", false),
//                        new AnswerEntity("Реляционная", false),
//                        new AnswerEntity("Графовая", false),
//                        new AnswerEntity("Документная", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Базы данных", "Инструменты и технологии баз", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
//                        new AnswerEntity("MySQL ", true),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("SQLServer", false),
//                        new AnswerEntity("MongoDB", false)
//                )),
//                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
//                        new AnswerEntity("XML", false),
//                        new AnswerEntity("SQL", true),
//                        new AnswerEntity("HTML", false),
//                        new AnswerEntity("JSON", false)
//                )),
//                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("Tableau", true),
//                        new AnswerEntity("PostgreSQL", false)
//                )),
//                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("PostgreSQL", false),
//                        new AnswerEntity("SQLite", false),
//                        new AnswerEntity("Oracle ", true)
//                ))
//        )));
//
//        return tests;
//    }
//
//
//    public static List<TestEntity> getSecondTests() {
//        List<TestEntity> tests = new ArrayList<>();
//        tests.add(new TestEntity("Компьютерные сети", "Основы компьютерных сетей", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
//                        new AnswerEntity("Структурированный", true),
//                        new AnswerEntity("Статический", false),
//                        new AnswerEntity("Системный", false),
//                        new AnswerEntity("Секвенциальный", false)
//                )),
//                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
//                        new AnswerEntity("Массив", false),
//                        new AnswerEntity("Таблица", true),
//                        new AnswerEntity("Дерево", false),
//                        new AnswerEntity("Список", false)
//                )),
//                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
//                        new AnswerEntity("Колонка", false),
//                        new AnswerEntity("Поле", false),
//                        new AnswerEntity("Ключ", true),
//                        new AnswerEntity("Запрос", false)
//                )),
//                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
//                        new AnswerEntity("Update", false),
//                        new AnswerEntity("Delete", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Select", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Компьютерные сети", "Протоколы и модели сетей", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
//                        new AnswerEntity("Delete", true),
//                        new AnswerEntity("Select", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Update", false)
//                )),
//                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
//                        new AnswerEntity("Merge", false),
//                        new AnswerEntity("Join", true),
//                        new AnswerEntity("Split", false),
//                        new AnswerEntity("Divide", false)
//                )),
//                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
//                        new AnswerEntity("МногиекОдному", false),
//                        new AnswerEntity("ОдинкоОдному", false),
//                        new AnswerEntity("ОдинкоМногим", true),
//                        new AnswerEntity("МногиекМногим", false)
//                )),
//                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
//                        new AnswerEntity("Архивирование", false),
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг", false),
//                        new AnswerEntity("Восстановление", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Компьютерные сети", "Технологии беспроводных сетей", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
//                        new AnswerEntity("Хранилище", true),
//                        new AnswerEntity("Сервер", false),
//                        new AnswerEntity("Кластер", false),
//                        new AnswerEntity("Таблица", false)
//                )),
//                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг ", true),
//                        new AnswerEntity("Кэширование", false),
//                        new AnswerEntity("Индексация", false)
//                )),
//                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
//                        new AnswerEntity("Лог", false),
//                        new AnswerEntity("Индекс", false),
//                        new AnswerEntity("Кэш", true),
//                        new AnswerEntity("Буфер", false)
//                )),
//                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
//                        new AnswerEntity("Сетевая", false),
//                        new AnswerEntity("Реляционная", false),
//                        new AnswerEntity("Графовая", false),
//                        new AnswerEntity("Документная", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Компьютерные сети", "Сетевые устройства и топологии", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
//                        new AnswerEntity("MySQL ", true),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("SQLServer", false),
//                        new AnswerEntity("MongoDB", false)
//                )),
//                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
//                        new AnswerEntity("XML", false),
//                        new AnswerEntity("SQL", true),
//                        new AnswerEntity("HTML", false),
//                        new AnswerEntity("JSON", false)
//                )),
//                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("Tableau", true),
//                        new AnswerEntity("PostgreSQL", false)
//                )),
//                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("PostgreSQL", false),
//                        new AnswerEntity("SQLite", false),
//                        new AnswerEntity("Oracle ", true)
//                ))
//        )));
//
//        return tests;
//    }
//
//    public static List<TestEntity> getThirdTests() {
//        List<TestEntity> tests = new ArrayList<>();
//        tests.add(new TestEntity("Программирование", "Основы программирования", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
//                        new AnswerEntity("Структурированный", true),
//                        new AnswerEntity("Статический", false),
//                        new AnswerEntity("Системный", false),
//                        new AnswerEntity("Секвенциальный", false)
//                )),
//                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
//                        new AnswerEntity("Массив", false),
//                        new AnswerEntity("Таблица", true),
//                        new AnswerEntity("Дерево", false),
//                        new AnswerEntity("Список", false)
//                )),
//                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
//                        new AnswerEntity("Колонка", false),
//                        new AnswerEntity("Поле", false),
//                        new AnswerEntity("Ключ", true),
//                        new AnswerEntity("Запрос", false)
//                )),
//                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
//                        new AnswerEntity("Update", false),
//                        new AnswerEntity("Delete", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Select", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Программирование", "Языки программирования", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
//                        new AnswerEntity("Delete", true),
//                        new AnswerEntity("Select", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Update", false)
//                )),
//                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
//                        new AnswerEntity("Merge", false),
//                        new AnswerEntity("Join", true),
//                        new AnswerEntity("Split", false),
//                        new AnswerEntity("Divide", false)
//                )),
//                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
//                        new AnswerEntity("МногиекОдному", false),
//                        new AnswerEntity("ОдинкоОдному", false),
//                        new AnswerEntity("ОдинкоМногим", true),
//                        new AnswerEntity("МногиекМногим", false)
//                )),
//                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
//                        new AnswerEntity("Архивирование", false),
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг", false),
//                        new AnswerEntity("Восстановление", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Программирование", "Структуры данных", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
//                        new AnswerEntity("Хранилище", true),
//                        new AnswerEntity("Сервер", false),
//                        new AnswerEntity("Кластер", false),
//                        new AnswerEntity("Таблица", false)
//                )),
//                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг ", true),
//                        new AnswerEntity("Кэширование", false),
//                        new AnswerEntity("Индексация", false)
//                )),
//                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
//                        new AnswerEntity("Лог", false),
//                        new AnswerEntity("Индекс", false),
//                        new AnswerEntity("Кэш", true),
//                        new AnswerEntity("Буфер", false)
//                )),
//                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
//                        new AnswerEntity("Сетевая", false),
//                        new AnswerEntity("Реляционная", false),
//                        new AnswerEntity("Графовая", false),
//                        new AnswerEntity("Документная", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Программирование", "Алгоритмы", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
//                        new AnswerEntity("MySQL ", true),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("SQLServer", false),
//                        new AnswerEntity("MongoDB", false)
//                )),
//                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
//                        new AnswerEntity("XML", false),
//                        new AnswerEntity("SQL", true),
//                        new AnswerEntity("HTML", false),
//                        new AnswerEntity("JSON", false)
//                )),
//                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("Tableau", true),
//                        new AnswerEntity("PostgreSQL", false)
//                )),
//                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("PostgreSQL", false),
//                        new AnswerEntity("SQLite", false),
//                        new AnswerEntity("Oracle ", true)
//                ))
//        )));
//
//        return tests;
//    }
//
//    public static List<TestEntity> getFourthTests() {
//        List<TestEntity> tests = new ArrayList<>();
//        tests.add(new TestEntity("Технологии", "Основы информационных технологий", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
//                        new AnswerEntity("Структурированный", true),
//                        new AnswerEntity("Статический", false),
//                        new AnswerEntity("Системный", false),
//                        new AnswerEntity("Секвенциальный", false)
//                )),
//                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
//                        new AnswerEntity("Массив", false),
//                        new AnswerEntity("Таблица", true),
//                        new AnswerEntity("Дерево", false),
//                        new AnswerEntity("Список", false)
//                )),
//                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
//                        new AnswerEntity("Колонка", false),
//                        new AnswerEntity("Поле", false),
//                        new AnswerEntity("Ключ", true),
//                        new AnswerEntity("Запрос", false)
//                )),
//                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
//                        new AnswerEntity("Update", false),
//                        new AnswerEntity("Delete", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Select", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Технологии", "Современные технологии", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
//                        new AnswerEntity("Delete", true),
//                        new AnswerEntity("Select", false),
//                        new AnswerEntity("Insert", false),
//                        new AnswerEntity("Update", false)
//                )),
//                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
//                        new AnswerEntity("Merge", false),
//                        new AnswerEntity("Join", true),
//                        new AnswerEntity("Split", false),
//                        new AnswerEntity("Divide", false)
//                )),
//                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
//                        new AnswerEntity("МногиекОдному", false),
//                        new AnswerEntity("ОдинкоОдному", false),
//                        new AnswerEntity("ОдинкоМногим", true),
//                        new AnswerEntity("МногиекМногим", false)
//                )),
//                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
//                        new AnswerEntity("Архивирование", false),
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг", false),
//                        new AnswerEntity("Восстановление", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Технологии", "Программные технологии", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
//                        new AnswerEntity("Хранилище", true),
//                        new AnswerEntity("Сервер", false),
//                        new AnswerEntity("Кластер", false),
//                        new AnswerEntity("Таблица", false)
//                )),
//                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
//                        new AnswerEntity("Репликация", false),
//                        new AnswerEntity("Шардинг ", true),
//                        new AnswerEntity("Кэширование", false),
//                        new AnswerEntity("Индексация", false)
//                )),
//                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
//                        new AnswerEntity("Лог", false),
//                        new AnswerEntity("Индекс", false),
//                        new AnswerEntity("Кэш", true),
//                        new AnswerEntity("Буфер", false)
//                )),
//                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
//                        new AnswerEntity("Сетевая", false),
//                        new AnswerEntity("Реляционная", false),
//                        new AnswerEntity("Графовая", false),
//                        new AnswerEntity("Документная", true)
//                ))
//        )));
//
//        tests.add(new TestEntity("Технологии", "Сетевые технологии", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
//                        new AnswerEntity("MySQL ", true),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("SQLServer", false),
//                        new AnswerEntity("MongoDB", false)
//                )),
//                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
//                        new AnswerEntity("XML", false),
//                        new AnswerEntity("SQL", true),
//                        new AnswerEntity("HTML", false),
//                        new AnswerEntity("JSON", false)
//                )),
//                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("Oracle", false),
//                        new AnswerEntity("Tableau", true),
//                        new AnswerEntity("PostgreSQL", false)
//                )),
//                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
//                        new AnswerEntity("MySQL", false),
//                        new AnswerEntity("PostgreSQL", false),
//                        new AnswerEntity("SQLite", false),
//                        new AnswerEntity("Oracle ", true)
//                ))
//        )));
//
//        return tests;
//    }
}
