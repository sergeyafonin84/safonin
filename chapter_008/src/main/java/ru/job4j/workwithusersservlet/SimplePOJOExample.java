package ru.job4j.workwithusersservlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

//https://tomcat.apache.org/tomcat-8.0-doc/jdbc-pool.html
public class SimplePOJOExample {

    public static void main(String[] args) throws Exception {
        PoolProperties p = new PoolProperties();
//        p.setUrl("jdbc:mysql://localhost:3306/mysql");
//        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUrl("jdbc:postgresql://localhost:5432/Crudervlet2512");
        p.setDriverClassName("org.postgresql.Driver");
//        p.setUsername("root");
//        p.setPassword("password");
        p.setUsername("postgres");
        p.setPassword("password");



//        p.setJmxEnabled(true);
//        p.setTestWhileIdle(false);
//        p.setTestOnBorrow(true);
//        p.setValidationQuery("SELECT 1");
//        p.setTestOnReturn(false);
//        p.setValidationInterval(30000);
//        p.setTimeBetweenEvictionRunsMillis(30000);
//        p.setMaxActive(100);
//        p.setInitialSize(10);
//        p.setMaxWait(10000);
//        p.setRemoveAbandonedTimeout(60);
//        p.setMinEvictableIdleTimeMillis(30000);
//        p.setMinIdle(10);
//        p.setLogAbandoned(true);
//        p.setRemoveAbandoned(true);
//        p.setJdbcInterceptors(
//                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
//                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");



        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);

        Connection con = null;
        try {
            con = datasource.getConnection();
            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("select * from user");
            ResultSet rs = st.executeQuery("select * from users");
            int cnt = 1;
            while (rs.next()) {
//                System.out.println((cnt++)+". Host:" +rs.getString("Host")+
//                        " User:"+rs.getString("User")+" Password:"+rs.getString("Password"));
                System.out.println(rs.getString("login"));
            }
            rs.close();
            st.close();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ignore) { }
            }
        }
    }

}
