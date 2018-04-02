package ru.job4j.workwithusersservlet;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

//https://stackoverflow.com/questions/40976740/dbcp-psql-exception-connection-pool
public class DataSource {

    private static DataSource     datasource;
    private BasicDataSource ds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        //ds.setDriverClassName("org.postgresql.Driver");
        ds.setDriverClassName(ds.getDriverClassName());
        ds.setUsername("postgres");
        ds.setPassword("password");
        ds.setUrl("jdbc:postgresql://localhost:5432/Crudervlet2512"); // ds.setUrl("jdbc:postgresql://localhost:5432/ehealth");

        // the settings below are optional -- dbcp can work with defaults
        //   ds.setMinIdle(5);
        //   ds.setMaxIdle(20);
        //   ds.setMaxOpenPreparedStatements(180);
    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

}
