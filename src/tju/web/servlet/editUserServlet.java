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
 * Created by llin on 16/1/12.
 */
@WebServlet("/servlet/editUser")
public class editUserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null || u.getType() != 1) {//TODO 如果用户信息丢失或者不是管理员,那么退回到登陆界面
            resp.sendRedirect("/jsp/login.jsp");
        } else {//TODO 从表单获取到更新的密码修改用户信息
            UserDao userDao = new UserDao();
            User user = new User();
            user.setUid(Integer.parseInt( req.getParameter("uid")));
            user.setUname(req.getParameter("uname"));
            user.setPwd(req.getParameter("pwd"));
            user.setScore(Integer.parseInt(req.getParameter("score")));
            try {
                boolean success = userDao.updateUser(user);
                req.setAttribute("success",success);
                resp.sendRedirect("/servlet/userList");
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("success",false);
            }
        }
    }
}
