package ru.job4j.map;

import java.util.Calendar;

public class User {

    String name;
    int children;
    Calendar birthday;

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
