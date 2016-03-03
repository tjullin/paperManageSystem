package tju.web.service;

import tju.web.dao.CommentDao;
import tju.web.dao.DownloadDao;
import tju.web.entity.Comment;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by llin on 16/1/12.
 * 提供评论服务的类型
 */
public class CommentService {
    private DownloadDao downloadDao = new DownloadDao();
    private CommentDao commentDao = new CommentDao();

    /**
     * 评论论文
     * @param pid 所评论的论文的pid
     * @param uid 做出评论的人的uid
     * @param content 评论内容
     * @return 返回值0代表没下载过没有评论权限,1代表成功,-1代表失败
     */
    public int commentPaper(int pid, int uid,String content) {
        try {
            boolean everDownloaded = downloadDao.findDownload(pid, uid);//当前用户是否下载过指定pid的文章
            if (!everDownloaded) {//TODO 如果未被下载过，那么无法评论
                return 0;
            }
            //TODO 对文章进行评论，向数据库中添加评论
            Comment comment = new Comment();
            comment.setUid(uid);
            comment.setPid(pid);
            comment.setContent(content);
            comment.setCurtime(new Date());
            if (commentDao.addComment(comment)) return 1;
            else return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
