package by.garkaviy.game.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestLibrary {

    public static List<TestEntity> getTests() {
        List<TestEntity> tests = new ArrayList<>();
        tests.add(new TestEntity("Основы баз данных", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
                        new AnswerEntity("Структурированный", true),
                        new AnswerEntity("Статический", false),
                        new AnswerEntity("Системный", false),
                        new AnswerEntity("Секвенциальный", false)
                )),
                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
                        new AnswerEntity("Массив", false),
                        new AnswerEntity("Таблица", true),
                        new AnswerEntity("Дерево", false),
                        new AnswerEntity("Список", false)
                )),
                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
                        new AnswerEntity("Колонка", false),
                        new AnswerEntity("Поле", false),
                        new AnswerEntity("Ключ", true),
                        new AnswerEntity("Запрос", false)
                )),
                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
                        new AnswerEntity("Update", false),
                        new AnswerEntity("Delete", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Select", true)
                ))
        )));

        tests.add(new TestEntity("Продвинутые концепции баз", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
                        new AnswerEntity("Delete", true),
                        new AnswerEntity("Select", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Update", false)
                )),
                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
                        new AnswerEntity("Merge", false),
                        new AnswerEntity("Join", true),
                        new AnswerEntity("Split", false),
                        new AnswerEntity("Divide", false)
                )),
                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
                        new AnswerEntity("МногиекОдному", false),
                        new AnswerEntity("ОдинкоОдному", false),
                        new AnswerEntity("ОдинкоМногим", true),
                        new AnswerEntity("МногиекМногим", false)
                )),
                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
                        new AnswerEntity("Архивирование", false),
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг", false),
                        new AnswerEntity("Восстановление", true)
                ))
        )));

        tests.add(new TestEntity("Архитектура баз данных", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
                        new AnswerEntity("Хранилище", true),
                        new AnswerEntity("Сервер", false),
                        new AnswerEntity("Кластер", false),
                        new AnswerEntity("Таблица", false)
                )),
                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг ", true),
                        new AnswerEntity("Кэширование", false),
                        new AnswerEntity("Индексация", false)
                )),
                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
                        new AnswerEntity("Лог", false),
                        new AnswerEntity("Индекс", false),
                        new AnswerEntity("Кэш", true),
                        new AnswerEntity("Буфер", false)
                )),
                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
                        new AnswerEntity("Сетевая", false),
                        new AnswerEntity("Реляционная", false),
                        new AnswerEntity("Графовая", false),
                        new AnswerEntity("Документная", true)
                ))
        )));

        tests.add(new TestEntity("Инструменты и технологии баз", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
                        new AnswerEntity("MySQL ", true),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("SQLServer", false),
                        new AnswerEntity("MongoDB", false)
                )),
                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
                        new AnswerEntity("XML", false),
                        new AnswerEntity("SQL", true),
                        new AnswerEntity("HTML", false),
                        new AnswerEntity("JSON", false)
                )),
                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("Tableau", true),
                        new AnswerEntity("PostgreSQL", false)
                )),
                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("PostgreSQL", false),
                        new AnswerEntity("SQLite", false),
                        new AnswerEntity("Oracle ", true)
                ))
        )));

        return tests;
    }


    public static List<TestEntity> getSecondTests() {
        List<TestEntity> tests = new ArrayList<>();
        tests.add(new TestEntity("Компьютерные сети", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
                        new AnswerEntity("Структурированный", true),
                        new AnswerEntity("Статический", false),
                        new AnswerEntity("Системный", false),
                        new AnswerEntity("Секвенциальный", false)
                )),
                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
                        new AnswerEntity("Массив", false),
                        new AnswerEntity("Таблица", true),
                        new AnswerEntity("Дерево", false),
                        new AnswerEntity("Список", false)
                )),
                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
                        new AnswerEntity("Колонка", false),
                        new AnswerEntity("Поле", false),
                        new AnswerEntity("Ключ", true),
                        new AnswerEntity("Запрос", false)
                )),
                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
                        new AnswerEntity("Update", false),
                        new AnswerEntity("Delete", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Select", true)
                ))
        )));

        tests.add(new TestEntity("Продвинутые концепции баз", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
                        new AnswerEntity("Delete", true),
                        new AnswerEntity("Select", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Update", false)
                )),
                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
                        new AnswerEntity("Merge", false),
                        new AnswerEntity("Join", true),
                        new AnswerEntity("Split", false),
                        new AnswerEntity("Divide", false)
                )),
                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
                        new AnswerEntity("МногиекОдному", false),
                        new AnswerEntity("ОдинкоОдному", false),
                        new AnswerEntity("ОдинкоМногим", true),
                        new AnswerEntity("МногиекМногим", false)
                )),
                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
                        new AnswerEntity("Архивирование", false),
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг", false),
                        new AnswerEntity("Восстановление", true)
                ))
        )));

        tests.add(new TestEntity("Архитектура баз данных", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
                        new AnswerEntity("Хранилище", true),
                        new AnswerEntity("Сервер", false),
                        new AnswerEntity("Кластер", false),
                        new AnswerEntity("Таблица", false)
                )),
                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг ", true),
                        new AnswerEntity("Кэширование", false),
                        new AnswerEntity("Индексация", false)
                )),
                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
                        new AnswerEntity("Лог", false),
                        new AnswerEntity("Индекс", false),
                        new AnswerEntity("Кэш", true),
                        new AnswerEntity("Буфер", false)
                )),
                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
                        new AnswerEntity("Сетевая", false),
                        new AnswerEntity("Реляционная", false),
                        new AnswerEntity("Графовая", false),
                        new AnswerEntity("Документная", true)
                ))
        )));

        tests.add(new TestEntity("Инструменты и технологии баз", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
                        new AnswerEntity("MySQL ", true),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("SQLServer", false),
                        new AnswerEntity("MongoDB", false)
                )),
                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
                        new AnswerEntity("XML", false),
                        new AnswerEntity("SQL", true),
                        new AnswerEntity("HTML", false),
                        new AnswerEntity("JSON", false)
                )),
                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("Tableau", true),
                        new AnswerEntity("PostgreSQL", false)
                )),
                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("PostgreSQL", false),
                        new AnswerEntity("SQLite", false),
                        new AnswerEntity("Oracle ", true)
                ))
        )));

        return tests;
    }

    public static List<TestEntity> getThirdTests() {
        List<TestEntity> tests = new ArrayList<>();
        tests.add(new TestEntity("Программирование", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
                        new AnswerEntity("Структурированный", true),
                        new AnswerEntity("Статический", false),
                        new AnswerEntity("Системный", false),
                        new AnswerEntity("Секвенциальный", false)
                )),
                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
                        new AnswerEntity("Массив", false),
                        new AnswerEntity("Таблица", true),
                        new AnswerEntity("Дерево", false),
                        new AnswerEntity("Список", false)
                )),
                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
                        new AnswerEntity("Колонка", false),
                        new AnswerEntity("Поле", false),
                        new AnswerEntity("Ключ", true),
                        new AnswerEntity("Запрос", false)
                )),
                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
                        new AnswerEntity("Update", false),
                        new AnswerEntity("Delete", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Select", true)
                ))
        )));

        tests.add(new TestEntity("Продвинутые концепции баз", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
                        new AnswerEntity("Delete", true),
                        new AnswerEntity("Select", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Update", false)
                )),
                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
                        new AnswerEntity("Merge", false),
                        new AnswerEntity("Join", true),
                        new AnswerEntity("Split", false),
                        new AnswerEntity("Divide", false)
                )),
                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
                        new AnswerEntity("МногиекОдному", false),
                        new AnswerEntity("ОдинкоОдному", false),
                        new AnswerEntity("ОдинкоМногим", true),
                        new AnswerEntity("МногиекМногим", false)
                )),
                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
                        new AnswerEntity("Архивирование", false),
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг", false),
                        new AnswerEntity("Восстановление", true)
                ))
        )));

        tests.add(new TestEntity("Архитектура баз данных", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
                        new AnswerEntity("Хранилище", true),
                        new AnswerEntity("Сервер", false),
                        new AnswerEntity("Кластер", false),
                        new AnswerEntity("Таблица", false)
                )),
                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг ", true),
                        new AnswerEntity("Кэширование", false),
                        new AnswerEntity("Индексация", false)
                )),
                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
                        new AnswerEntity("Лог", false),
                        new AnswerEntity("Индекс", false),
                        new AnswerEntity("Кэш", true),
                        new AnswerEntity("Буфер", false)
                )),
                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
                        new AnswerEntity("Сетевая", false),
                        new AnswerEntity("Реляционная", false),
                        new AnswerEntity("Графовая", false),
                        new AnswerEntity("Документная", true)
                ))
        )));

        tests.add(new TestEntity("Инструменты и технологии баз", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
                        new AnswerEntity("MySQL ", true),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("SQLServer", false),
                        new AnswerEntity("MongoDB", false)
                )),
                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
                        new AnswerEntity("XML", false),
                        new AnswerEntity("SQL", true),
                        new AnswerEntity("HTML", false),
                        new AnswerEntity("JSON", false)
                )),
                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("Tableau", true),
                        new AnswerEntity("PostgreSQL", false)
                )),
                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("PostgreSQL", false),
                        new AnswerEntity("SQLite", false),
                        new AnswerEntity("Oracle ", true)
                ))
        )));

        return tests;
    }

    public static List<TestEntity> getFourthTests() {
        List<TestEntity> tests = new ArrayList<>();
        tests.add(new TestEntity("Технологии", List.of(new QuestionEntity("Что означает аббревиатура SQL?", List.of(
                        new AnswerEntity("Структурированный", true),
                        new AnswerEntity("Статический", false),
                        new AnswerEntity("Системный", false),
                        new AnswerEntity("Секвенциальный", false)
                )),
                new QuestionEntity("Как называется основная структура данных в реляционной базе данных?", List.of(
                        new AnswerEntity("Массив", false),
                        new AnswerEntity("Таблица", true),
                        new AnswerEntity("Дерево", false),
                        new AnswerEntity("Список", false)
                )),
                new QuestionEntity("Как называется уникальный идентификатор строки в таблице?", List.of(
                        new AnswerEntity("Колонка", false),
                        new AnswerEntity("Поле", false),
                        new AnswerEntity("Ключ", true),
                        new AnswerEntity("Запрос", false)
                )),
                new QuestionEntity("Как называется команда для извлечения данных из базы данных?", List.of(
                        new AnswerEntity("Update", false),
                        new AnswerEntity("Delete", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Select", true)
                ))
        )));

        tests.add(new TestEntity("Продвинутые концепции баз", List.of(new QuestionEntity("Как называется операция удаления данных из таблицы?", List.of(
                        new AnswerEntity("Delete", true),
                        new AnswerEntity("Select", false),
                        new AnswerEntity("Insert", false),
                        new AnswerEntity("Update", false)
                )),
                new QuestionEntity("Как называется комбинация двух таблиц на основе общего столбца?", List.of(
                        new AnswerEntity("Merge", false),
                        new AnswerEntity("Join", true),
                        new AnswerEntity("Split", false),
                        new AnswerEntity("Divide", false)
                )),
                new QuestionEntity("Какой тип связи существует между двумя таблицами, когда одна строка одной таблицы соответствует многим строкам другой таблицы?", List.of(
                        new AnswerEntity("МногиекОдному", false),
                        new AnswerEntity("ОдинкоОдному", false),
                        new AnswerEntity("ОдинкоМногим", true),
                        new AnswerEntity("МногиекМногим", false)
                )),
                new QuestionEntity("Как называется процесс восстановления данных после сбоя?", List.of(
                        new AnswerEntity("Архивирование", false),
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг", false),
                        new AnswerEntity("Восстановление", true)
                ))
        )));

        tests.add(new TestEntity("Архитектура баз данных", List.of(new QuestionEntity("Как называется центральная часть базы данных, где хранятся данные?", List.of(
                        new AnswerEntity("Хранилище", true),
                        new AnswerEntity("Сервер", false),
                        new AnswerEntity("Кластер", false),
                        new AnswerEntity("Таблица", false)
                )),
                new QuestionEntity("Как называется процесс разделения базы данных на части для повышения производительности?", List.of(
                        new AnswerEntity("Репликация", false),
                        new AnswerEntity("Шардинг ", true),
                        new AnswerEntity("Кэширование", false),
                        new AnswerEntity("Индексация", false)
                )),
                new QuestionEntity("Как называется временное хранилище данных для ускорения доступа?", List.of(
                        new AnswerEntity("Лог", false),
                        new AnswerEntity("Индекс", false),
                        new AnswerEntity("Кэш", true),
                        new AnswerEntity("Буфер", false)
                )),
                new QuestionEntity("Как называется модель данных, где данные хранятся в виде документов?", List.of(
                        new AnswerEntity("Сетевая", false),
                        new AnswerEntity("Реляционная", false),
                        new AnswerEntity("Графовая", false),
                        new AnswerEntity("Документная", true)
                ))
        )));

        tests.add(new TestEntity("Инструменты и технологии баз", List.of(new QuestionEntity("Как называется популярная система управления базами данных с открытым исходным кодом?", List.of(
                        new AnswerEntity("MySQL ", true),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("SQLServer", false),
                        new AnswerEntity("MongoDB", false)
                )),
                new QuestionEntity("Как называется язык запросов для управления и манипуляции данными?", List.of(
                        new AnswerEntity("XML", false),
                        new AnswerEntity("SQL", true),
                        new AnswerEntity("HTML", false),
                        new AnswerEntity("JSON", false)
                )),
                new QuestionEntity("Как называется инструмент для визуализации и анализа данных?", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("Oracle", false),
                        new AnswerEntity("Tableau", true),
                        new AnswerEntity("PostgreSQL", false)
                )),
                new QuestionEntity("Как называется система управления базами данных, разработанная компанией Oracle??", List.of(
                        new AnswerEntity("MySQL", false),
                        new AnswerEntity("PostgreSQL", false),
                        new AnswerEntity("SQLite", false),
                        new AnswerEntity("Oracle ", true)
                ))
        )));

        return tests;
    }
}
