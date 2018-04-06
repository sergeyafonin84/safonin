package ru.job4j.crudservlet;
import java.time.LocalDateTime;

public class User {

    String name;
    String login;
    String email;
    private String password;

    LocalDateTime createDate;

    public User(String name, String login, String email, String password, LocalDateTime createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.createDate = createDate;

    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + '}';
    }


}