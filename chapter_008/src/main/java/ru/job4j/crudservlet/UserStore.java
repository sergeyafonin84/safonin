    package ru.job4j.crudservlet;

    import org.apache.tomcat.jdbc.pool.PoolProperties;
    import ru.job4j.workwithusersservlet.Role;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.time.LocalDateTime;

    public class UserStore {

        private static final UserStore INSTANCE = new UserStore();
        Connection connection;

        private UserStore() {
            this.getInitConnectionTomcatPool();
            deleleALLRoles();
            deleleALLUsers();

            this.addSql(new User("root", "root", "root@root", "root", LocalDateTime.now()));
            this.addSql(new User("user1", "user1", "user1@user1", "user1", LocalDateTime.now()));
            this.addRoleSql(new Role("root", "root"));
            this.addRoleSql(new Role("user1", "user1"));
        }

        public static UserStore getInstance() {
            return INSTANCE;
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
            this.createTablesIfNotExist();
            String query = "insert into users (name, login, email, password, createDate) values ('" + user.name
                    + "', '" + user.login + "', '" + user.email + "', '" + user.getPassword()
                    + "', '" + user.createDate + "');";
            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void addRoleSql(Role role) {
            this.createTablesIfNotExist();
            String query = "insert into roles (rolename, userlogin) values ('" + role.getRolename() + "', '" + role.getUserlogin() + "')";
            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private ArrayList<User> getSql(String login) {
            ArrayList<User> users = new ArrayList<>();
            String query = "SELECT * FROM users AS u WHERE u.login = '" + login + "'";

            this.createTablesIfNotExist();

            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
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

        public ArrayList<User> getAllSql() {
            ArrayList<User> users = new ArrayList<>();
            String query = "SELECT * FROM users";

            this.createTablesIfNotExist();

            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
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

        public ArrayList<Role> getAllRolesSql() {
            ArrayList<Role> roles = new ArrayList<>();
            String query = "SELECT * FROM roles";

            this.createTablesIfNotExist();

            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Role role = new Role(rs.getString("rolename"), rs.getString("userlogin"));

                    roles.add(role);
                }
            } catch (SQLException e) {
            }
            return roles;
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

                sb.append("<tr><td>" + currUser.toString() + "</td></tr>");
                listOfUsers = sb.toString();
            }
            return listOfUsers;
        }

        public void delete(String login) {
            this.createTablesIfNotExist();
            String query = "delete from users as u where u.login = ?";
            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                pst.setString(1, login);
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteRole(String rolename, String userlogin) {
            this.createTablesIfNotExist();
            String query = "delete from roles as u where u.rolename = ? and u.userlogin = ?";
            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                pst.setString(1, rolename);
                pst.setString(2, userlogin);
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void edit(String name, String login, String email) {
            this.createTablesIfNotExist();
            String query = "update users set name = '" + name + "', email = '" + email + "' where login = '" + login + "'";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void editSql(String name, String login, String email, String password) {
            this.createTablesIfNotExist();
            String query = "update users set name = '" + name + "', email = '" + email +  "', password = '" + password + "' where login = '" + login + "'";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Integer getQuontityOfUsers() {
            Integer quontityOfUsers = 0;
            String query = "select count(*) as quontitity from users";

            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    quontityOfUsers = rs.getInt("quontitity");
                }
            } catch (SQLException e) {
            }
            return quontityOfUsers;
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

        void deleleALLUsers() {
            this.createTablesIfNotExist();
            String query = "delete from users";
            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        void deleleALLRoles() {
            this.createTablesIfNotExist();
            String query = "delete from roles";
            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        void createTableUsersIfNotExist() {
            ResultSet rs = readFromBase("select * from users");
            if (rs == null) {
                try (Statement st = this.connection.createStatement()) {
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
                try (Statement st = this.connection.createStatement()) {
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
            try (PreparedStatement pst = this.connection.prepareStatement(query)) {
                rs = pst.executeQuery();
            } catch (SQLException e) {
            }
            return rs;
        }
    }