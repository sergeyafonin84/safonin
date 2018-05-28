package ru.job4j.parsentev.servlets;

public class User {
    private  String id;
    String login;
    String email;
    private String password;


    public User(String id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(String id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
