package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NewUsersServlet  extends HttpServlet {

private final UserStore users = UserStore.getInstance();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String listOfUsers;
//        resp.setContentType("text/html");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        StringBuilder sb = new StringBuilder("<table>");
//        listOfUsers = this.users.getAll()
//                + " QuontityOfUsers = " + this.users.getQuontityOfUsers();
//        writer.append("<!DOCTYPE html>"
//                +
//                "<html lang=\"en\">"
//                +
//                "<head>"
//                +
//                "    <meta charset=\"UTF-8\">"
//                +
//                "    <title>Title</title>"
//                +
//                "</head>"
//                +
//                "<body>"
//                +
//                "<form action = '"
//                + req.getContextPath()
//                + "/NewUsersServlet' method = 'post'>"
//                +
//                "<a href='http://localhost:8080/items/AddUserServlet'>Add</a>"
//                +
//                "<br />"
//                +
//                "<a href='http://localhost:8080/items/DeleteUserServlet'>Delete</a>"
//                +
//                "<br />"
//                +
//                "<a href='http://localhost:8080/items/EditUserServlet'>Edit</a>"
//                +
//                "<br />"
//                +
//                "<a href='http://localhost:8080/items/GetUserServlet'>Get</a>"
//                +
//                "<br />"
//                +
//                "</br>"
//                +
//                listOfUsers
//                +
//                "</body>"
//                +
//                "</html>");
//
//        writer.flush();
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.getInstance().getAllSql());
        req.getRequestDispatcher("/WEB-INF/views/NewUsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.doGet(req, resp);
    }
}