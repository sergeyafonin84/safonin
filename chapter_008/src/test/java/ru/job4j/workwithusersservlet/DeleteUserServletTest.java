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

public class DeleteUserServletTest {

    @Test
    public void deleteUser() throws ServletException, IOException {

        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        session.setAttribute("login", "root");

        when(req.getSession()).thenReturn(session);
        when(req.getParameter("Back")).thenReturn(null);
        when((String) session.getAttribute("login")).thenReturn("root");

        when(req.getParameter("login")).thenReturn("user1");

        when(req.getRequestDispatcher("/WEB-INF/views/NewDeleteUserView.jsp")).thenReturn(requestDispatcher);

        ArrayList<User> usersforeDeleting = UserStore.getInstance().getAllSql();

        Integer quountityOfUsersBeforeDeleting = usersforeDeleting.size();

        deleteUserServlet.doPost(req, resp);

        ArrayList<User> usersAfterDeleting = UserStore.getInstance().getAllSql();

        Integer quountityOfUsersAfterDeleting = usersAfterDeleting.size();

        assertThat(quountityOfUsersBeforeDeleting - quountityOfUsersAfterDeleting, is(1));
    }
}