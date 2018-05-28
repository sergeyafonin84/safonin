package ru.job4j.workwithusersservlet;

import static org.junit.Assert.*;

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

public class DeleteRoleServletTest {

    @Ignore
    @Test
    public void deleteRole() throws ServletException, IOException {

        DeleteRoleServlet deleteRoleServlet = new DeleteRoleServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        session.setAttribute("login", "root");

        when(req.getSession()).thenReturn(session);
        when(req.getParameter("Back")).thenReturn(null);
        when((String) session.getAttribute("login")).thenReturn("root");

        when(req.getParameter("rolename")).thenReturn("user1"); // because off userlogin REFERENCES users (login) and we have name root and user1 so we use user1
        when(req.getParameter("userlogin")).thenReturn("user1");

        when(req.getRequestDispatcher("/WEB-INF/views/DeleteRole.jsp")).thenReturn(requestDispatcher);

        ArrayList<Role> rolesBeforeDeleting = UserStore.getInstance().getAllRolesSql();
        Integer quontityBeforeDeleting = rolesBeforeDeleting.size();

        deleteRoleServlet.doPost(req, resp);

        ArrayList<Role> rolesAfterDeleting = UserStore.getInstance().getAllRolesSql();
        Integer quontityAfterDeleting = rolesAfterDeleting.size();

        assertThat(quontityBeforeDeleting - quontityAfterDeleting, is(1));
    }
}