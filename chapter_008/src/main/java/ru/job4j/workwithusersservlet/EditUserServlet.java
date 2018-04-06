package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditUserServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditUserServlet doGet");
        req.setAttribute("users", UserStore.getInstance().getAllSql());
        req.getRequestDispatcher("/WEB-INF/views/NewEditUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("EditUserServlet doPost01");

        String back = req.getParameter("Back"); // gi

        String currUserLogin = (String) req.getSession().getAttribute("login");

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println("Back = " + back + LocalDateTime.now());

        if (back == null) {
            if (login.equals("") || name.equals("") || email.equals("") || password.equals("")) {
                req.setAttribute("error", "You must fill all fields!");
                this.doGet(req, resp);
            } else {
                if (currUserLogin.equals("root") || currUserLogin.equals(login)) {
                    System.out.println("EditUserServlet doPost02");
                    resp.setContentType("text/html");
                    UserStore.getInstance().editSql(name, login, email, password); //addSql(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), LocalDateTime.now()));
                    this.doGet(req, resp);
                } else {
                    req.setAttribute("error", "For edit user you must have the root role!");
                    this.doGet(req, resp);
                }
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}