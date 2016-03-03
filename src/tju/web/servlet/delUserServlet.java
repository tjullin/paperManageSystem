package tju.web.servlet;

import tju.web.dao.UserDao;
import tju.web.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by gmy on 16/1/12.
 */
@WebServlet("/servlet/delUser")
public class delUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/jsp/login.jsp");
        } else {
            UserDao userDao = new UserDao();
            try {
                boolean res = userDao.deleteUserById(Integer.parseInt(req.getParameter("uid")));
                req.setAttribute("success", res);
                resp.sendRedirect("/servlet/userList");
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("success",false);
            }
        }
    }
}
