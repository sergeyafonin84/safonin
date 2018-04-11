package ru.job4j.crudservlet;

import java.sql.*;
import java.util.ArrayList;
import ru.job4j.workwithusersservlet.Role;

class WorkWithDataBase {

    private Connection conn;

    WorkWithDataBase(Connection conn) {
        this.conn = conn;
    }

    void addUser(User user) {
        this.createTablesIfNotExist();
            String query = "insert into users (name, login, email, password, createDate) values ('" + user.name
                    + "', '" + user.login + "', '" + user.email + "', '" + user.getPassword()
                    + "', '" + user.createDate + "');";
        try (Statement st = conn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addRole(Role role) {
        this.createTablesIfNotExist();
        String query = "insert into roles (rolename, userlogin) values ('" + role.getRolename() + "', '" + role.getUserlogin() + "')";
        try (Statement st = conn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleleUsersByLogin(String login) {
        this.createTablesIfNotExist();
        String query = "delete from users as u where u.login = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, login);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleleRoleByRoleNameAndUserLogin(String rolename, String userlogin) {
        this.createTablesIfNotExist();
        String query = "delete from roles as u where u.rolename = ? and u.userlogin = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, rolename);
            st.setString(2, userlogin);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void deleleALLUsers() {
        this.createTablesIfNotExist();
        String query = "delete from users";
        try (Statement st = conn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleleALLRoles() {
        this.createTablesIfNotExist();
        String query = "delete from roles";
        try (Statement st = conn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    ArrayList<User> getUsers(String login) {

        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users AS u WHERE u.login = '" + login + "'";

        this.createTablesIfNotExist();

        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.name = rs.getString("name");
                user.login = rs.getString("login");
                user.email = rs.getString("email");
                user.createDate = rs.getTimestamp("createDate").toLocalDateTime();

                users.add(user);
            }
        } catch (SQLException e) {
        }
        return users;
    }

    ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        this.createTablesIfNotExist();

        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user = new User(rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getTimestamp("createDate").toLocalDateTime());

                users.add(user);
            }
        } catch (SQLException e) {
        }
        return users;
    }

    ArrayList<Role> getAllRoles() {

        ArrayList<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM roles";

        this.createTablesIfNotExist();

        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Role role = new Role(rs.getString("rolename"), rs.getString("userlogin"));

                roles.add(role);
            }
        } catch (SQLException e) {
        }
        return roles;
    }

    Integer getQuontityOfUsers() {

        Integer quontityOfUsers = 0;
        String query = "select count(*) as quontitity from users";

        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
               quontityOfUsers = rs.getInt("quontitity");
            }
        } catch (SQLException e) {
        }
        return quontityOfUsers;
    }

    void editUser(String name, String login, String email, String password) {
        this.createTablesIfNotExist();
        String query = "update users set name = '" + name + "', email = '" + email +  "', password = '" + password + "' where login = '" + login + "'";
        try (Statement st = conn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void editUserOld(String name, String login, String email) {
        this.createTablesIfNotExist();
        String query = "update users set name = '" + name + "', email = '" + email + "' where login = '" + login + "'";
        try (Statement st = conn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createTableUsersIfNotExist() {
        ResultSet rs = readFromBase("select * from users");
        if (rs == null) {
            try (Statement st = this.conn.createStatement()) {
                st.execute("create table users(\n"
                        + "name varchar,\n"
                        + "login varchar PRIMARY KEY,\n"
                        + "email varchar,\n"
                        + "password varchar,\n"
                        + "createDate timestamp\n"
                        + ");");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    void createTableRolesIfNotExist() {
        ResultSet rs = readFromBase("select * from roles");
        if (rs == null) {
            try (Statement st = this.conn.createStatement()) {
                st.execute("CREATE TABLE roles (\n"
                        +
                        "rolename VARCHAR,\n"
                        +
                        "userlogin VARCHAR REFERENCES users (login)\n"
                        +
                        ");");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }



    private void createTablesIfNotExist() {
        this.createTableUsersIfNotExist();
        this.createTableRolesIfNotExist();
    }

    ResultSet readFromBase(String query) {
        ResultSet rs = null;
        try (Statement st = this.conn.createStatement()) {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
        }
        return rs;
    }
}