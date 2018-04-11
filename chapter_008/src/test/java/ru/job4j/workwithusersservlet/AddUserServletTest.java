package ru.job4j.workwithusersservlet;

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

public class AddUserServletTest {

    @Test
    public void addUser() throws ServletException, IOException {

        AddUserServlet addUserServlet = new AddUserServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        session.setAttribute("login", "root");

        when(req.getSession()).thenReturn(session);
        when(req.getParameter("Back")).thenReturn(null);
        when((String) session.getAttribute("login")).thenReturn("root");

        when(req.getParameter("name")).thenReturn("testname");
        when(req.getParameter("login")).thenReturn("testlogin");
        when(req.getParameter("email")).thenReturn("testemail");
        when(req.getParameter("password")).thenReturn("testpassword");

        when(req.getRequestDispatcher("/WEB-INF/views/NewAddUser.jsp")).thenReturn(requestDispatcher);

        addUserServlet.doPost(req, resp);

        ArrayList<User> users = UserStore.getInstance().getAllSql();

        assertThat(users.get(2).getLogin(), is("testlogin"));
    }
}