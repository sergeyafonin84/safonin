package ru.job4j.generic;

//1. Сделать два класса User, и Role которые наследуют Base класс
public class Role extends Base {

    private String id;

    public Role(String id) {
        this.id = id;
    }

    @Override
    String getId() {
        return null;
    }

    @Override
    void setId(String id) {

    }
}
