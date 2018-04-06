package ru.job4j.parsentev.servlets;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SigninController doGet");
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("SigninController doPost");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (UserStorage.getInstance().isCredentional(login, password)) {

            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
//                session.invalidate();
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));

        } else {
            req.setAttribute("error", "Credentional invalid");
            doGet(req, resp);
        }

    }
}
