package ru.job4j.generic;

//1. Сделать два класса User, и Role которые наследуют Base класс
public class User extends Base {

    private String id;

    public User(String id) {
        this.id = id;
    }

    @Override
    String getId() {
        return this.id;
    }

    @Override
    void setId(String id) {
        this.id = id;
    }
}
