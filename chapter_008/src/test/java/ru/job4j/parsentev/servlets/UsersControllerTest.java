package ru.job4j.parsentev.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.mock;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class UsersControllerTest {

    @Test
    public void addUser() throws ServletException, IOException {
        UsersController controller = new UsersController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("asdf");
        when(request.getParameter("email")).thenReturn("emailasdf");

        controller.doPost(request, response);

        List<User> users = UserStorage.getInstance().getUsers();

        assertThat(users.get(1).getLogin(), is("asdf"));
    }

}