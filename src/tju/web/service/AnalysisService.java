package tju.web.service;

import tju.web.entity.Paper;
import tju.web.entity.PaperVo;
import tju.web.entity.User;
import tju.web.entity.UserVo;
import tju.web.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by llin on 16/1/14.
 */
public class AnalysisService {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public List<PaperVo> analysisPaper(Date startDate, Date endDate) throws SQLException {
        DBUtil dbUtil = new DBUtil();
        String sql = "select count(*),d.pid,title from download d,paper s where d.pid=s.pid";
        if (startDate != null) {
            sql += " and d.curtime >= '" + simpleDateFormat.format(startDate) + "'";
        }
        if (endDate != null) {
            sql += " and d.curtime <= '" + simpleDateFormat.format(endDate) + "'";
        }
        sql += " group by d.pid order by count(*) desc";
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{});
        List<PaperVo> paperList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                PaperVo paperVo = new PaperVo();
                Paper paper = new Paper();
                paperVo.setDownloadCnt(resultSet.getInt(1));
                paperVo.setStartDate(startDate);
                paperVo.setEndDate(endDate);
                paper.setPid(resultSet.getInt(2));
                paper.setTitle(resultSet.getString(3));
                paperVo.setPaper(paper);
                paperList.add(paperVo);
            }
            return paperList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbUtil.closeConn();
        }
    }

    public List<UserVo> analysisUser(Date startDate, Date endDate) throws SQLException {
        DBUtil dbUtil = new DBUtil();
        String sql = "select sum(p.score),u.uname from paper p,user u,download d where p.pid=d.pid and u.uid=d.fuid";
        if (startDate != null) {
            sql += " and d.curtime >= '" + simpleDateFormat.format(startDate) + "'";
        }
        if (endDate != null) {
            sql += " and d.curtime <= '" + simpleDateFormat.format(endDate) + "'";
        }
        sql += " group by d.fuid order by sum(p.score) desc";
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{});
        List<UserVo> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                UserVo userVo = new UserVo();
                User user = new User();
                userVo.setScore(resultSet.getInt(1));
                userVo.setStartDate(startDate);
                userVo.setEndDate(endDate);
                user.setUname(resultSet.getString(2));
                userVo.setUser(user);
                userList.add(userVo);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbUtil.closeConn();
        }

    }
}
