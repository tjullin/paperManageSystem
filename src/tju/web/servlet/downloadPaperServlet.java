package tju.web.servlet;

import tju.web.dao.PaperDao;
import tju.web.dao.UserDao;
import tju.web.entity.Paper;
import tju.web.entity.User;
import tju.web.service.DownloadService;
import tju.web.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by llin on 16/1/13.
 */
@WebServlet("/servlet/downloadPaper")
public class downloadPaperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        int pid = Integer.parseInt(req.getParameter("pid"));
        PaperDao paperDao = new PaperDao();
        //TODO 测试完后将userId改成正确的
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {//TODO 如果用户信息丢失了，则重定向回登陆界面
            resp.sendRedirect("/jsp/login.jsp");
        } else {//TODO 执行下载操作并添加下载记录
            int uid = user.getUid();
            DownloadService downloadService = new DownloadService();
            int success = downloadService.downPaper(pid, uid);
            if ( success > 0 ) {//TODO 如果成功添加下载记录，那么执行数据传输
                try {
                    UserDao userDao = new UserDao();
                    User u = userDao.findUserById(user.getUid());
                    session.setAttribute("user", u);
                    Paper paper = paperDao.findPaperById(pid);
                    String filepath = Constant.FILE_PATH + File.separator;
                    filepath += paper.getFilename() + "." + paper.getType();
                    String filename = paper.getTitle() + "." + paper.getType();
                    resp.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
                    OutputStream outputStream = resp.getOutputStream();
                    FileInputStream fileInputStream = new FileInputStream(filepath);
                    byte[] bytes = new byte[1024];
                    int i;
                    while ((i = fileInputStream.read(bytes)) > 0) {
                        outputStream.write(bytes, 0, i);
                    }
                    fileInputStream.close();
                    outputStream.flush();
                    outputStream.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (success == 2) {//更新当前的success状态
                req.setAttribute("success","您已下载过,本次下载将不消耗积分!");
            } else if (success == 0) {
                req.setAttribute("success","您的积分不足!");
            } else if (success == -1) {
                req.setAttribute("success","下载失败!");
            }
            req.getRequestDispatcher("/servlet/showPaper?pid="+pid).forward(req,resp);
        }
    }
}
