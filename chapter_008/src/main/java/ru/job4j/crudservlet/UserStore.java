package ru.job4j.crudservlet;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import ru.job4j.workwithusersservlet.DataSource;
import ru.job4j.workwithusersservlet.Role;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;

public class UserStore {

    private static final UserStore INSTANCE = new UserStore();
    Connection connection;
    WorkWithDataBase workWithDataBase;

    private UserStore() {
        this.getInitConnectionTomcatPool();
//        this.getInitConnectionJNDI();
        //this.getInitConnection();
        workWithDataBase = new WorkWithDataBase(connection);
        workWithDataBase.deleleALLRoles();
        workWithDataBase.deleleALLUsers();

        this.addSql(new User("root", "root", "root@root", "root", LocalDateTime.now()));
        this.addSql(new User("user1", "user1", "user1@user1", "user1", LocalDateTime.now()));
        this.addRoleSql(new Role("root", "root"));
        this.addRoleSql(new Role("user1", "user1"));
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    private Connection getInitConnection() {
        MyConnection myConnection = new MyConnection();
        myConnection.initConnection();
        connection = myConnection.getConnection();
        return connection;
    }

    private Connection getInitConnectionJNDI() {

        Connection myConnection = null;

        try {
            myConnection = DataSource.getInstance().getConnection();
        } catch (IOException e) {
            System.out.println("getInitConnectionJNDI IOException");
        } catch (SQLException e2) {
            System.out.println("getInitConnectionJNDI SQLException");
        } catch (PropertyVetoException e3) {
            System.out.println("getInitConnectionJNDI PropertyVetoException");
        }

        connection = myConnection;

        return connection;
    }

    private Connection getInitConnectionTomcatPool() {
        Connection myConnection = null;

        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/Crudervlet2512");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("password");

        org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
        datasource.setPoolProperties(p);

        try {
            myConnection = datasource.getConnection();
        } catch (SQLException e) {
            System.out.println("getInitConnectionTomcatPool SQLException");
        }

        connection = myConnection;

        return connection;
    }

    public void addSql(User user) {
        workWithDataBase.addUser(user);
    }

    public void addRoleSql(Role role) {
        workWithDataBase.addRole(role);
    }




    private ArrayList<User> getSql(String login) {
        return  workWithDataBase.getUsers(login);
    }

    public ArrayList<User> getAllSql() {
        return  workWithDataBase.getAllUsers();
    }

    public ArrayList<Role> getAllRolesSql() {
        return  workWithDataBase.getAllRoles();
    }



    public void add(String name, String login, String email) {
        if (this.get(login).equals("")) {

            User user = new User();
            user.name = name;
            user.login = login;
            user.email = email;
            user.createDate = LocalDateTime.now();

            addSql(user);
        }
    }

    public String get(String login) {

        ArrayList<User> users = getSql(login);

        String listOfUsers = "";
        Iterator iterator = users.listIterator();
        while (iterator.hasNext()) {
            User currUser = (User) iterator.next();
            if (currUser.login.equals(login)) {
            listOfUsers = listOfUsers + "\n" + currUser.toString();
            }
        }
        return listOfUsers;
    }

    public String getAll() {

        ArrayList<User> users = getAllSql();

        String listOfUsers = "";
        Iterator iterator = users.listIterator();

        StringBuilder sb = new StringBuilder("<table>");

        while (iterator.hasNext()) {
            User currUser = (User) iterator.next();
//            if (currUser.login.equals(login)) {
            listOfUsers = listOfUsers + "\n" + currUser.toString();



//            for (String login : users) {
                sb.append("<tr><td>" + currUser.toString() + "</td></tr>");
                listOfUsers = sb.toString();
//            }
//            }
        }
        return listOfUsers;
    }

    public void delete(String login) {
        workWithDataBase.deleleUsersByLogin(login);
    }

    public void deleteRole(String rolename, String userlogin) {
        workWithDataBase.deleleRoleByRoleNameAndUserLogin(rolename, userlogin);
    }

    public void edit(String name, String login, String email) {
        this.editSqlOld(name, login, email);
    }

    public void editSqlOld(String name, String login, String email) {
       workWithDataBase.editUserOld(name, login, email);
    }

    public void editSql(String name, String login, String email, String password) {
        workWithDataBase.editUser(name, login, email, password);
    }

    public Integer getQuontityOfUsers() {
        return this.getQuontityOfUsersSql();
    }

    public Integer getQuontityOfUsersSql() {
        return workWithDataBase.getQuontityOfUsers();
    }

    public boolean isCredentional(String login, String password) {
        boolean exists = false;
        for (User user : this.getAllSql()) {
            if (user.getLogin().equals(login) && (user.getPassword() == null || user.getPassword().equals(password))) {
                exists = true;
                break;
            }
        }
        return exists;
    }

}