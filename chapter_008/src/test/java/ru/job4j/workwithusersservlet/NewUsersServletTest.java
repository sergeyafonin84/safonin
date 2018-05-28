package ru.job4j.workwithusersservlet;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.core.Is.is;

public class NewUsersServletTest {

    @Ignore
    @Test
    public void addUser() throws ServletException, IOException {

        NewUsersServlet newUsersServlet = new NewUsersServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("asdf");

        newUsersServlet.doPost(request, response);

        ArrayList<User> users = UserStore.getInstance().getAllSql();

        assertThat(users.get(2).getLogin(), is("asdf"));

    }

}