package tju.web.servlet;

import tju.web.dao.UserDao;
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
@WebServlet("/servlet/userList")
public class userListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getType() != 1) {
            resp.sendRedirect("/jsp/login.jsp");
        } else {
            UserDao userDao = new UserDao();
            try {
                List<User> userList = userDao.findAllUser();
                Page page = new Page();
                page.setPageSize(Constant.PAGE_SIZE);
                page.setTotalRows(userList.size());
//            page.setCurrentPage(Integer.parseInt(req.getParameter("pageId")));
//            page.setData(userList.subList(page.getStartIndex(),Math.min(page.getEndIndex(),userList.size())));
                page.setData(userList);
                req.setAttribute("userList",page);
                req.getRequestDispatcher("/jsp/userList.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
