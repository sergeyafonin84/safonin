package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

//Создать два объекта User, которые имеют одинаковые поля.
//        Создать карту Map<User, Object>
//        Добавить два объекта. Вывести карту на печать. Описать полученный результат словами.
public class UserTest {

    @Test
    public void map() {

        User first = new User("Serg", 1, new Calendar("12/09/84"));


        User second = new User("Serg", 1, new Calendar("12/09/84"));

        Map<User, Object> map = new HashMap<>();

        map.put(first, "serg");
        map.put(second, "serg");

        System.out.println(map);

    }
}