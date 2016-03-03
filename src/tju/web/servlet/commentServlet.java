package tju.web.servlet;

import tju.web.entity.User;
import tju.web.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by llin on 16/1/12.
 */
@WebServlet("/servlet/makeComment")
public class commentServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {//TODO　如果用户信息丢失，那么就重定向回用户登录界面
            resp.sendRedirect("/jsp/login.jsp");
        } else {//TODO　如果用户信息核对无误，那么就添加新的评论
            int uid = user.getUid();
            int pid = Integer.parseInt(req.getParameter("pid"));
            String content = req.getParameter("content");
            CommentService commentService = new CommentService();
            int success = commentService.commentPaper(pid, uid , content);
            if (success==1) {
                req.setAttribute("comment","评论成功!");
            } else if (success == 0) {
                req.setAttribute("comment", "只有下载过的用户才能评论!");
            } else {
                req.setAttribute("comment", "未知错误!评论失败!");
            }
            req.getRequestDispatcher("/servlet/showPaper?pid="+pid).forward(req,resp);
        }
    }
}
