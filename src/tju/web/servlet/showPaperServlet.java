package tju.web.servlet;

import tju.web.dao.CommentDao;
import tju.web.dao.PaperDao;
import tju.web.entity.Comment;
import tju.web.entity.Paper;
import tju.web.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by llin on 16/1/12.
 */
@WebServlet("/servlet/showPaper")
public class showPaperServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {//TODO 如果用户信息丢失，那么重定向回登陆界面
            resp.sendRedirect("/jsp/login.jsp");
        }
        else{//TODO 用户信息未丢失，那么展示指定文章的信息和评论列表
            int pid = Integer.parseInt(req.getParameter("pid"));
            PaperDao paperDao = new PaperDao();
            CommentDao commentDao = new CommentDao();
            try {
                Paper paper = paperDao.findPaperById(pid);
                List<Comment> commentList = commentDao.findCommentByPid(pid);
                req.setAttribute("paper", paper);
                req.setAttribute("commentList", commentList);
                req.getRequestDispatcher("/jsp/paper.jsp").forward(req,resp);
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
