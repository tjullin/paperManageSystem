package tju.web.servlet;

import tju.web.entity.PaperVo;
import tju.web.entity.User;
import tju.web.entity.UserVo;
import tju.web.service.AnalysisService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by llin on 16/1/14.
 */
@WebServlet("/servlet/analysisData")
public class analysisServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {//如果用户的信息丢失，那么重定向回登陆界面
            resp.sendRedirect("/jsp/login.jsp");
        } else {
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            Date start = null, end = null;
            if (startDate != null) {
                try {
                    start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (endDate != null) {
                try {
                    end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            AnalysisService analysisService = new AnalysisService();
            try {
                List<PaperVo> paperList = analysisService.analysisPaper(start, end);
                List<UserVo> userList = analysisService.analysisUser(start, end);
                req.setAttribute("paperList",paperList.subList(0,Math.min(paperList.size(),10)));
                req.setAttribute("userList", userList.subList(0,Math.min(userList.size(),10)));
                req.getRequestDispatcher("/jsp/analysisList.jsp").forward(req, resp);
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
