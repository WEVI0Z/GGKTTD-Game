package by.garkaviy.game.test;

import lombok.Getter;

@Getter
public enum PartsEnum {
    DATA_BASES("Базы данных"),
    PROGRAMMING("Программирование"),
    CONNECTIONS("Компьютерные сети"),
    TECHNOLOGIES("Технологии");

    private String title;

    PartsEnum(String title) {
        this.title = title;
    }
}
