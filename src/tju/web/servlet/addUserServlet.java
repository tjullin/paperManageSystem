package tju.web.servlet;

import tju.web.dao.UserDao;
import tju.web.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by llin on 16/1/12.
 * 处理注册操作的servlet
 */
@WebServlet("/servlet/addUser")
public class addUserServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Integer.parseInt(req.getParameter("type")) == 1) {//如果要注册管理员用户，返回提示信息
            req.setAttribute("success","无法注册管理员账号!");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
        }
        User user = new User();
        user.setUname(req.getParameter("uname"));
        user.setPwd(req.getParameter("pwd"));
        UserDao userDao = new UserDao();
        try {//添加用户
            if( user.getPwd().equals("") || user.getUname().equals("")){
                req.setAttribute("success", "账号或密码为空!");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
            }
            boolean success = userDao.addUser(user);
            req.setAttribute("success",success);
            if (success) {//如果成功注册用户，直接登陆
                req.setAttribute("user",user);
                //清除缓存，并且设置账号密码为新注册的密码向/servlet/login提交表单
                req.getRequestDispatcher("/servlet/login?uname="+user.getUname()+"&pwd="+user.getPwd()+"&type=0").forward(req,resp);
            }
            else{//如果账号已经存在，那么提供提示信息
                req.setAttribute("success", "该账号已经存在!");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
            }
        } catch (SQLException e) {//如果不能成功添加用户，返回提示信息
            e.printStackTrace();
            req.setAttribute("success","注册失败!请重试!");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
