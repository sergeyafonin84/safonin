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
public class EchoServlet extends HttpServlet {
//    private static final Logger Log = LoggerFactory.getLogger(EchoServlet.class);

    private List<String> users = new CopyOnWriteArrayList<>();

//    private int count = 0;
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.service(req, resp);
//        count++;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.append("hello word." + this.users);

        StringBuilder sb = new StringBuilder("<table>");

        for (String login : users) {
            sb.append("<tr><td>" + login + "</td></tr>");
        }

        sb.append("</table>");

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
                + "/echo' method = 'post'>"
                +
                "Name : <input type = 'text' name = 'login'/>"
                +
                "<input type = 'submit'>"
                +
                "</form>"
                +
                "</br>"
                +
                sb.toString()
                +
                "</body>"
                +
                "</html>");

//        writer.append("asdf");


        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.users.add(req.getParameter("login"));
        this.doGet(req, resp);

    }
}