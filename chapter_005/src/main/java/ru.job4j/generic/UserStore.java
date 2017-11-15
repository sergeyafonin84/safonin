package ru.job4j.generic;

import java.util.ArrayList;

//2. Сделать два класса хранилища UserStore и RoleStore. Внутри для хранения данных использовать SimpleArray.
public class UserStore extends AbstractStore implements Store {

    public UserStore(int numberOfElements) {
        super(numberOfElements);
    }

}