package tju.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tju.web.dao.PaperDao;
import tju.web.entity.Paper;
import tju.web.entity.User;
import tju.web.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by llin on 16/1/12.
 */
@WebServlet("/servlet/addPaper")
public class addPaperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {// TODO 如果用户信息丢失，那么就重定向到登陆界面
            resp.sendRedirect("/jsp/login.jsp");
        }
        else {// TODO 如果存在用户信息，那么添加一篇论文
            int uid = user.getUid();
//        System.out.println(req.getParameter("title"));
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();//文件上传的对象
            factory.setSizeThreshold(1024);
            File tmpfile = new File(Constant.TEMP_FILE_PATH);
            File file = new File(Constant.FILE_PATH);
            if (!tmpfile.exists()) {
                tmpfile.mkdirs();
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            //TODO 上传论文
            factory.setRepository(tmpfile);//设置上传文件的路径
            ServletFileUpload fileUpload = new ServletFileUpload(factory);//设置文件上传的servlet
            List items = null;
            try {
                items = fileUpload.parseRequest(req);//获得上传的文件的信息
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator iterator = items.iterator();
            Map<String,String> keymap = new HashMap<>();//keyMap存储的是表单类型和表单的内容
            String fileName = null;
            String contentType = null;
            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (fileItem.isFormField()) {//如果file是普通的上传表单类型，那么添加到keyMap中
                    keymap.put(fileItem.getFieldName(), fileItem.getString());
                } else {//如果file是文件上传的表单类型
                    fileName = fileItem.getName();//文件上传字段中的文件名
                    contentType = fileName.substring(fileName.lastIndexOf(".")+1);
                    fileName = String.valueOf(System.currentTimeMillis());//服务器上的临时文件用当时的系统时间命名
                    File uploadFile = new File(file + File.separator + fileName + "." + contentType);
                    if (!uploadFile.exists()) {
                        uploadFile.createNewFile();
                    }
                    try {
                        fileItem.write(uploadFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //TODO 给论文系统添加新的文章记录
            Paper paper = null;
            PaperDao paperDao=null;
            boolean success = false;
            try {
                paper = new Paper();
                paper.setTitle(new String(keymap.get("title").getBytes("iso-8859-1"), "UTF-8"));
                paper.setScore(Integer.parseInt(keymap.get("score")));
                paper.setKeyword(new String(keymap.get("keyword").getBytes("iso-8859-1"), "UTF-8"));
                paper.setUid(uid);
                paper.setDescription(new String(keymap.get("description").getBytes("iso-8859-1"), "UTF-8"));
                paper.setType(contentType);
                paper.setFilename(fileName);
                paperDao = new PaperDao();
                success = false;
            }catch ( Exception e ){//TODO 对于空的上传表单的处理
                req.setAttribute ( "blank" , "上传失败，可能因为信息不全" );
                req.getRequestDispatcher("/jsp/uploadPaper.jsp").forward(req,resp);
            }
            try {
                success = paperDao.addPaper(paper);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("success", success);
            req.getRequestDispatcher("/jsp/uploadPaper.jsp").forward(req,resp);
        }
    }
}
