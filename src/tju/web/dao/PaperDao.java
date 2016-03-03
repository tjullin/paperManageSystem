package tju.web.dao;

import tju.web.entity.Paper;
import tju.web.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llin on 16/1/12.
 * Paper类型的数据访问对象的类型
 */
public class PaperDao {
    /**
     * 向数据库添加一篇论文
     * @param paper 准备被添加的论文
     * @return 操作结果，成功添加返回true,失败返回false
     * @throws SQLException
     */
    public boolean addPaper(Paper paper) throws SQLException {
        String sql = "insert into paper(title,keyword,description,score,uid,type,filename) values(?,?,?,?,?,?,?)";
        DBUtil dbUtil = new DBUtil();
        try {
            int res = dbUtil.execOther(sql, new Object[]{paper.getTitle(), paper.getKeyword(), paper.getDescription(), paper.getScore(),paper.getUid(),paper.getType(),paper.getFilename()});
            return res >= 0 ? true : false;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     *根据题目和关键查找论文
     * @param paper 论文的查找的关键信息
     * @return 符合要求的论文的集合
     * @throws SQLException
     */
    public List<Paper> findPaperList(Paper paper) throws SQLException {
        String sql = "select * from paper";
        if (paper.getTitle() != "" && paper.getTitle() != null) {
            sql += " where title like '%" + paper.getTitle() + "%'";
            if (paper.getKeyword() != "" && paper.getKeyword() != null) {
                sql += " and keyword like '%" + paper.getKeyword() + "%'";
            }
        } else {
            if (paper.getKeyword() != "" && paper.getKeyword() != null) {
                sql += " where keyword like '%" + paper.getKeyword() + "%'";
            }
        }
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{});
        List<Paper> paperList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Paper p = new Paper();
                p.setPid(resultSet.getInt(1));
                p.setTitle(resultSet.getString(2));
                p.setKeyword(resultSet.getString(3));
                p.setDescription(resultSet.getString(4));
                p.setScore(resultSet.getInt(5));
                p.setUid(resultSet.getInt(6));
                p.setType(resultSet.getString(7));
                p.setFilename(resultSet.getString(8));
                paperList.add(p);
            }
            return paperList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 通过论文的PID查找一篇论文
     * @param pid 准备查找的论文的PID
     * @return 指定的PID的论文
     * @throws SQLException
     */
    public Paper findPaperById(int pid) throws SQLException {
        String sql = "select * from paper where pid=?";
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{pid});
        try {
            if (resultSet.next()) {
                Paper paper = new Paper();
                paper.setPid(resultSet.getInt(1));
                paper.setTitle(resultSet.getString(2));
                paper.setKeyword(resultSet.getString(3));
                paper.setDescription(resultSet.getString(4));
                paper.setScore(resultSet.getInt(5));
                paper.setUid(resultSet.getInt(6));
                paper.setType(resultSet.getString(7));
                paper.setFilename(resultSet.getString(8));
                return paper;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbUtil.closeConn();
        }
    }
}
