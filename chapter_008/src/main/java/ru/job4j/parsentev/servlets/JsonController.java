package ru.job4j.parsentev.servlets;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStorage.getInstance().add(new User("", req.getParameter("login"), "", ""));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final char dm = (char) 34;
        final String string = "STRING: " + dm + " string " + dm;



        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.append("[{'login':'test2', 'email':'email2'},{'login':'test2', 'email':'email2'}]");
        writer.append("[{" + dm + "login" + dm + ":  " + dm + "test2" + dm + ", " + dm + "email" + dm + ":" + dm + "email2" + dm + "}]");
        writer.flush();
    }
}
