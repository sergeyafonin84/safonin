package ru.job4j.workwithusersservlet;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.crudservlet.UserStore;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DataSourceTest {

   // @Ignore
  //  @Test
   // public void whenGetConnectionThenThereIsConnection() {
    //    Connection myConnection = null;
    //    UserStore userStore = UserStore.getInstance();

     //   try {
    //        myConnection = DataSource.getInstance().getConnection();
   //     } catch (IOException e) {
  //          System.out.println("getInitConnectionJNDI IOException");
  //      } catch (SQLException e2) {
   //         System.out.println("getInitConnectionJNDI SQLException");
   //     } catch (PropertyVetoException e3) {
  //          System.out.println("getInitConnectionJNDI PropertyVetoException");
  //      }

       // Connection expected = userStore.getConnection();

      //  assertThat(myConnection, is(expected));
   // }

}
