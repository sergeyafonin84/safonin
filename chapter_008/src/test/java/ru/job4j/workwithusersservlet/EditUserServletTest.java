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

public class EditUserServletTest {

    @Ignore
    @Test
    public void editUser() throws ServletException, IOException {

        EditUserServlet editUserServlet = new EditUserServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        session.setAttribute("login", "root");

        when(req.getSession()).thenReturn(session);
        when(req.getParameter("Back")).thenReturn(null);
        when((String) session.getAttribute("login")).thenReturn("root");

        when(req.getParameter("name")).thenReturn("user1testname");
        when(req.getParameter("login")).thenReturn("user1");
        when(req.getParameter("email")).thenReturn("user1testemail");
        when(req.getParameter("password")).thenReturn("user1testpassword");

        when(req.getRequestDispatcher("/WEB-INF/views/NewEditUserView.jsp")).thenReturn(requestDispatcher);

        editUserServlet.doPost(req, resp);

        ArrayList<User> users = UserStore.getInstance().getAllSql();

        assertThat(users.get(1).getName(), is("user1testname"));
    }
}