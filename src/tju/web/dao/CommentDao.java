package tju.web.dao;

import tju.web.entity.Comment;
import tju.web.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llin on 16/1/12.
 * 对Comment的数据访问对象的类型
 */
public class CommentDao {

    /**
     * 向数据库中添加一条评论信息
     * @param comment 准备添加的评论信息
     * @return 操作成功返回true,失败返回false
     * @throws SQLException
     */
    public boolean addComment(Comment comment) throws SQLException {
        String sql = "insert into comment(pid,uid,content,curtime) values(?,?,?,?)";
        DBUtil dbUtil = new DBUtil();
        try {
            int res = dbUtil.execOther(sql, new Object[]{comment.getPid(), comment.getUid(), comment.getContent(),comment.getCurtime()});
            return res >= 0 ? true : false;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 查询指定pid的文章的评论集合
     * @param pid 文章的pid
     * @return 查询到的指定文章的评论的集合
     * @throws SQLException
     */
    public List<Comment> findCommentByPid(int pid) throws SQLException {
        String sql = "select * from comment c,user u where pid=? and c.uid=u.uid";
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{pid});
        List<Comment> commentList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setCid(resultSet.getInt(1));
                comment.setPid(resultSet.getInt(2));
                comment.setUid(resultSet.getInt(3));
                comment.setContent(resultSet.getString(4));
                comment.setCurtime(resultSet.getDate(5));
                comment.setUname(resultSet.getString(7));
                commentList.add(comment);
            }
            return commentList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbUtil.closeConn();
        }
    }
}
