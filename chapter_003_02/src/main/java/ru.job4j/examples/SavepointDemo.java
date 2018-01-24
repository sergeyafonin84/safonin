package ru.job4j.examples;

import java.sql.*;

//http://proselyte.net/tutorials/jdbc/transactions/
public class SavepointDemo {
    /**
     * JDBC Driver and database url
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/PROSELYTE_TUTORIALS"; //"jdbc:mysql://localhost/PROSELYTE_TUTORIALS";

    /**
     * User and Password
     */
    static final String USER = "postgres"; //"ВАШЕ_ИМЯ_ПОЛЬЗОВАТЕЛЯ";
    static final String PASSWORD = "password"; // "ВАШ_ПАРОЛЬ";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        connection.setAutoCommit(false); ///55.55

        statement = connection.createStatement();

        String sql;
        sql = "SELECT * FROM developers";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nDevelopers:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
        }

        System.out.println("\n===========================\n");
        System.out.println("Creating savepoint...");
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");

        try {
            sql = "INSERT INTO developer VALUES (6, 'John','C#', 2200)";
            statement.executeUpdate(sql);

            sql = "INSE INTHE developers VALUES (7, 'Ron', 'Ruby', 1900)";
            statement.executeUpdate(sql);

            connection.commit(); //asdf55.55
        } catch (SQLException e) {
            System.out.println("SQLException. Executing rollback to savepoint...");
            connection.rollback(savepointOne);
        }
        sql = "SELECT * FROM developers";
        resultSet = statement.executeQuery(sql);
        System.out.println("Retrieving data from database...");
        System.out.println("\nDevelopers:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n================\n");
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();
    }
}

