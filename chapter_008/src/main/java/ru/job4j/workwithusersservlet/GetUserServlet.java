package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetUserServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userLogin = req.getParameter("userLogin");
        String listOfUsers;

        userLogin  = userLogin == null ? ("") : (userLogin);

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
//                "<a href='http://localhost:8080/items/NewUsersServlet'>Back</a>"
                "<a href='http://localhost:8080/items/'>Back</a>"
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
        this.doGet(req, resp);
    }
}
