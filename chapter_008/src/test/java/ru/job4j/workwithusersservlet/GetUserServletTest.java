package ru.job4j.workwithusersservlet;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserServletTest {

    @Ignore
    @Test
    public void addUser() throws ServletException, IOException {

        GetUserServlet getUserServlet = new GetUserServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(req.getParameter("Back")).thenReturn(null);

        when(req.getRequestDispatcher("/WEB-INF/views/NewGetUserView.jsp")).thenReturn(requestDispatcher);

        getUserServlet.doPost(req, resp);

        ArrayList<User> users = UserStore.getInstance().getAllSql();

        assertThat(users.get(0).getLogin(), is("root"));
    }
}