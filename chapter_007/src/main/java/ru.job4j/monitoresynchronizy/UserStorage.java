package ru.job4j.monitoresynchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

//1. Создать класс - структуру данных для хранение пользователей UserStorage.
//2. В заголовке класса обозначить аннотацию @ThreadSafe из библиотеки
//        3. Хранилище должно иметь методы boolean add (User user), boolean update(User user), boolean delete(User user).
//        4. И особый метод transfer(int fromId, int toId, int amount);
//        5. Структура данных должна быть потокобезопасная;
//        6. В структуре User Должны быть поля int id, int amount.
//        amount - это сумма денег на счете пользователя.
//        Пример. использования.
//        UserStore stoge = new UserStore();
//        stoge.add(new User(1, 100));
//        stoge.add(new User(2, 200));
//        stoge.transfer(1, 2, 50);

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    HashSet<User> userList = new HashSet<>();

    boolean add(User user) {
        return userList.add(user);
    }
    boolean update(User user) {
        return userList.add(user);
    }
    boolean delete(User user) {
        return userList.remove(user);
    }
    void transfer(int fromId, int toId, int amount) {

        User userPayer = findUserById(fromId);
        User userGetter = findUserById(toId);

        if (userPayer != null && userGetter != null) {
            userPayer.amount = userPayer.amount - amount;
            userGetter.amount = userGetter.amount + amount;
        }
    }

    public User findUserById(int id) {

        User user = null;

        Iterator it = userList.iterator();

        while (it.hasNext()) {
            User currUser = (User) it.next();
            if (currUser.id == id) {
                return currUser;
            }
        }
        return user;
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        storage.transfer(1, 2, 50);
    }
}

@ThreadSafe
class User {
    @GuardedBy("this") int id;
    @GuardedBy("this") int amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        return amount == user.amount;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        return result;
    }

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}
