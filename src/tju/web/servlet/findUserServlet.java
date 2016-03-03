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
@WebServlet("/servlet/admin/findUser")
public class findUserServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null) {
            resp.sendRedirect("/jsp/login.jsp");
        } else {
            UserDao userDao = new UserDao();
            try {
                User user = userDao.findUserById(Integer.parseInt(req.getParameter("uid")));
                req.setAttribute("user", user);
                resp.sendRedirect("/jsp/editUser.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
