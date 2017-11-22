package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Calendar;

//Создать два объекта User, которые имеют одинаковые поля.
//        Создать карту Map<User, Object>
//        Добавить два объекта. Вывести карту на печать. Описать полученный результат словами.
public class UserTest {

    @Test
    public void map() {

        Calendar calendar = Calendar.getInstance();

        User first = new User("Serg", 1, calendar);


        User second = new User("Serg", 1, calendar);

        Map<User, Object> map = new HashMap<>();

        map.put(first, "serg");
        map.put(second, "serg");

        System.out.println(map);

    }
}