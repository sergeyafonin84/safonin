package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditUserServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String listOfUsers;
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        listOfUsers = this.users.getAll()
                + " QuontityOfUsers = " + this.users.getQuontityOfUsers();
        writer.append("<!DOCTYPE html>"
                +
                "<html lang=\"en\">"
                +
                "<head>"
                +
                "    <meta charset=\"UTF-8\">"
                +
                "    <title>Title</title>"
                +
                "</head>"
                +
                "<body>"
                +
                "<form action = '"
                + req.getContextPath()
                + "/EditUserServlet' method = 'post'>"
                +
                "<br />"
                +
                "login: <input type = 'text' name = 'login' value = ''/>"
                +
                "<br />"
                +
                "name: <input type = 'text' name = 'name'/>"
                +
                "<br />"
                +
                "email: <input type = 'text' name = 'email'/>"
                +
                "<br />"
                +
                "<input type = 'submit' value = 'edit user'>"
                +
                "</form>"
                +
                "<a href='http://localhost:8080/items/NewUsersServlet'>Back</a>"
//                "<a href='http://localhost:8080/items/'>Back</a>"
                +
                "</br>"
                +
                listOfUsers
                +
                "</body>"
                +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.users.edit(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        this.doGet(req, resp);
    }
}