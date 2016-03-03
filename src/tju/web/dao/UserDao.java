package tju.web.dao;

import tju.web.entity.User;
import tju.web.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llin on 16/1/12.
 * User类型的数据访问对象类型
 */
public class UserDao {
    /**
     * 获取全部的用户
     * @return List<User>
     * @throws SQLException
     */
    public List<User> findAllUser() throws SQLException {
        String sql = "select * from user where type=0";
        DBUtil dbUtil = new DBUtil();//创建数据库管理工具
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{});
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setUid(resultSet.getInt(1));
                user.setUname(resultSet.getString(2));
                user.setPwd(resultSet.getString(3));
                user.setScore(resultSet.getInt(5));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
          dbUtil.closeConn();
        }
    }

    /**
     * 通过用户ID删除用户
     * @param uid 要删除用户的用户ID
     * @return 是否成功删除用户,true为成功，false为失败
     * @throws SQLException
     */
    public boolean deleteUserById(int uid) throws SQLException {
        String sql = "delete from user where uid = ?";
        DBUtil dbUtil = new DBUtil();
        int res;
        try {
            res = dbUtil.execOther(sql, new Object[]{uid});
            return res >= 0 ? true : false;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 更新指定用户的用户信息
     * @param user 要更新的用户的用户信息
     * @return 是否成功更新用户信息，true成功更新用户信息，false为更新用户信息失败
     * @throws SQLException
     */
    public boolean updateUser(User user) throws SQLException {
        String sql = "update user set uname=?,pwd=?,score=? where uid=?";
        DBUtil dbUtil = new DBUtil();
        try {
            int res = dbUtil.execOther(sql, new Object[]{user.getUname(),user.getPwd(),user.getScore(),user.getUid()});
            return res >= 0 ? true : false;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 添加一个用户
     * @param user 准备添加的用户的用户信息
     * @return
     * @throws SQLException
     */
    public boolean addUser(User user) throws SQLException {
        String sql = "insert into user(uname,pwd,type) values(?,?,0)";
        DBUtil dbUtil = new DBUtil();
        try {
            int res = dbUtil.execOther(sql, new Object[]{user.getUname(), user.getPwd()});
            return res >= 0 ? true : false;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 通过用户的登陆信息查找用户
     * @param u 用户的登陆信息
     * @return 根据用户查找到的用户
     * @throws SQLException
     */
    public User findUserByLogin(User u) throws SQLException {
        String sql = "select * from user where uname=? and pwd=? and type = ?";
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{u.getUname(), u.getPwd(), u.getType()});
        try {
            return getUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 通过用户ID查找用户
     * @param uid 被查找的用户的用户ID
     * @return 查找到的用户的用户信息
     * @throws SQLException
     */
    public User findUserById(int uid) throws SQLException {
        String sql = "select * from user where uid=?";
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.execQuery(sql, new Object[]{uid});
        try {
            return getUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbUtil.closeConn();
        }
    }

    /**
     * 将数据库查找到的数据集转换为用户信息集
     * @param resultSet 数据库查找到的数据集
     * @return 数据集转换的用户信息集
     * @throws SQLException
     */
    public User getUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User();
            user.setUid(resultSet.getInt(1));
            user.setUname(resultSet.getString(2));
            user.setPwd(resultSet.getString(3));
            user.setType(resultSet.getInt(4));
            user.setScore(resultSet.getInt(5));
            return user;
        } else {
            return null;
        }
    }
}
