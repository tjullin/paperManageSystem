package tju.web.dao;

import tju.web.entity.Download;
import tju.web.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by llin on 16/1/12.
 * 下载操作的数据访问对象类型
 */
public class DownloadDao {

    /**
     *
     * @param download 要执行的下载操作
     * @return 操作成功，返回true,失败返回false
     * @throws SQLException
     */
    public boolean addDownload(Download download) throws SQLException {
        String sql = "insert into download(pid,uid,fuid,curtime) values(?,?,?,?)";
        DBUtil dbUtil = new DBUtil();
        try {
            int res = dbUtil.execOther(sql, new Object[]{download.getPid(), download.getUid(), download.getFuid(), download.getCurtime()});
            return res >= 0 ? true : false;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 查找指定用户对于指定文章是否有过下载记录
     * @param pid 文章ID
     * @param uid 用户ID
     * @return  返回true表示有满足要求的下载记录，返回false表示无满足要求的下载记录
     * @throws SQLException
     */
    public boolean findDownload(int pid,int uid) throws SQLException {
        String sql = "select count(*) from download where pid=? and uid=?";
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{pid, uid});
        try {
            resultSet.next();
            return resultSet.getInt(1) > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbUtil.closeConn();
        }
    }
}
