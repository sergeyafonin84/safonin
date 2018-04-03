package ru.job4j.parsentev.servlets;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author afonin
 * @since 13.02.2018
 */
//public class EchoServlet extends HttpServlet {
public class UsersController extends HttpServlet {
    private List<String> users = new CopyOnWriteArrayList<>();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        StringBuilder sb = new StringBuilder("<table>");
//
//        for (String login : users) {
//            sb.append("<tr><td>" + login + "</td></tr>");
//        }
//
//        sb.append("</table>");
//
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
//                + "/echo' method = 'post'>"
//                +
//                "Name : <input type = 'text' name = 'login'/>"
//                +
//                "<input type = 'submit'>"
//                +
//                "</form>"
//                +
//                "</br>"
//                +
//                sb.toString()
//                +
//                "</body>"
//                +
//                "</html>");
//        writer.flush();
//    }
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("users", UserStorage.getInstance().getUsers());
    req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
}



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        UserStorage.getInstance().add(new User(req.getParameter("login"),
                req.getParameter("email")));
//        this.users.add(req.getParameter("login"));
//        this.doGet(req, resp);
//        resp.sendRedirect(req.getContextPath() + "/UsersView.jsp");
        resp.sendRedirect(req.getContextPath() + "/");

    }
}