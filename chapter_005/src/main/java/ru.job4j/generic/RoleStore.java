package ru.job4j.generic;

//2. Сделать два класса хранилища UserStore и RoleStore. Внутри для хранения данных использовать SimpleArray.
public class RoleStore extends AbstractStore implements Store {

    public RoleStore(int numberOfElements) {
        super(numberOfElements);
    }
}
