package ru.job4j.workwithusersservlet;

import org.junit.Ignore;
import org.junit.Test;
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

public class AddRoleServletTest {

    @Ignore
    @Test
    public void addRole() throws ServletException, IOException {

        AddRoleServlet addRoleServlet = new AddRoleServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        session.setAttribute("login", "root");

        when(req.getSession()).thenReturn(session);
        when(req.getParameter("Back")).thenReturn(null);
        when((String) session.getAttribute("login")).thenReturn("root");

        when(req.getParameter("rolename")).thenReturn("rolename");
        when(req.getParameter("userlogin")).thenReturn("user1"); // because off userlogin REFERENCES users (login) and we have name root and user1 so we use user1

        when(req.getRequestDispatcher("/WEB-INF/views/AddRole.jsp")).thenReturn(requestDispatcher);

        addRoleServlet.doPost(req, resp);

        ArrayList<Role> roles = UserStore.getInstance().getAllRolesSql();

        assertThat(roles.get(2).getRolename(), is("rolename"));
    }

}