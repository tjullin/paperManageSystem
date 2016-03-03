package tju.web.servlet;

import tju.web.dao.PaperDao;
import tju.web.entity.Paper;
import tju.web.entity.User;
import tju.web.util.Constant;
import tju.web.util.Page;

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
@WebServlet("/servlet/paperList")
public class paperListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {//如果session中没有获取到正确的用户信息，重定向到登陆界面
            resp.sendRedirect("/jsp/login.jsp");
        } else {//初始title和keyword为空，会显示全部论文
            Paper paper = new Paper();
            paper.setTitle(req.getParameter("title"));
            paper.setKeyword(req.getParameter("keyword"));
            PaperDao paperDao = new PaperDao();
            try {
                List<Paper> paperList = paperDao.findPaperList(paper);
                Page page = new Page();
                page.setPageSize(Constant.PAGE_SIZE);
                page.setTotalRows(paperList.size());
                page.setData(paperList);
                req.setAttribute("paperList", page);
                if (req.getParameter("add") != null) {//如果是新注册的用户，提示用户有10个初始积分
                    req.setAttribute("add", "恭喜您注册成功! 获得初始积分 10 分!");
                }
                req.getRequestDispatcher("/jsp/paperList.jsp").forward(req,resp);
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
