package ru.job4j.parsentev.servlets;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author afonin
 * @since 13.02.2018
 */
public class EchoServlet extends HttpServlet {
//    private static final Logger Log = LoggerFactory.getLogger(EchoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        resp.getOutputStream().write("hello word.".getBytes());
        writer.flush();
    }
}


