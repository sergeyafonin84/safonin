package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class GetUserServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GetUserServlet doGet");
        req.setAttribute("users", UserStore.getInstance().getAllSql());
        req.getRequestDispatcher("/WEB-INF/views/NewGetUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("GetUserServlet doPost01");

        String back = req.getParameter("Back");

        System.out.println("Back = " + back + LocalDateTime.now());

        if (back == null) {
                System.out.println("GetUserServlet doPost02");
                resp.setContentType("text/html");
                this.doGet(req, resp);
            } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
