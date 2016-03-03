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
 * 处理登陆操作的servlet
 */
@WebServlet("/servlet/login")
public class loginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        //从表单中获得要登陆的用户的账号、密码及用户类型
        User user = new User();
        user.setUname(req.getParameter("uname"));
        user.setPwd(req.getParameter("pwd"));
        user.setType(Integer.parseInt(req.getParameter("type")));
        if (req.getParameter("success") != null) {//如果success非空，证明是注册后登陆的
            req.setAttribute("add",true);
        }
        try {//核对用户信息
            User loginUser = userDao.findUserByLogin(user);
            if (loginUser == null) {//用户信息有误，提示用户重新输入
                req.setAttribute("success", "用户名或密码错误!请重试!");
                //转发，之前的request中存放的变量不会失效
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
            } else {//用户信息正确，根据用户类型响应
                HttpSession session = req.getSession();//从请求中提取session
                session.setAttribute("user", loginUser);//将session的user设置为loginUser
                if (user.getType() == 1) {//向管理员用户返回用户列表
                    resp.sendRedirect("/servlet/userList");//重定向到userList
                } else {//向普通用户返回文章列表
                    resp.sendRedirect("/servlet/paperList?pageId=1");//重定向到paperList
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
