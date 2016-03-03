package tju.web.util;

import java.sql.*;

/**
 * Created by llin on 16/1/12.
 * 数据库管理工具
 */
public class DBUtil {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     * 获得数据库的连接的方法
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//加载驱动类
        String url = Constant.DB_URL;
        String user = Constant.DB_USERNAME;
        String password = Constant.DB_PASSWORD;
        conn = DriverManager.getConnection(url,user,password);
    }

    /**
     * 关闭数据库连接的方法
     * @throws SQLException
     */
    public void closeConn() throws SQLException {
        if( rs != null )
            rs.close();
        if( pstmt != null )
            pstmt.close();
        if( conn != null )
            conn.close();
    }

    /**
     * 专门执行数据库删改的方法
     * @param strSQL 要执行的SQL语句
     * @param params SQL语句中替代占位符的参数
     * @return 执行结果的代码，-1为发生失败
     */
    public int execOther( final String strSQL , final Object[] params ){
        try {
            this.getConn();

            pstmt = conn.prepareStatement(strSQL);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 执行数据库查询操作的方法
     * @param strSQL 要执行的SQL语句
     * @param params SQL语句中替代占位符的参数
     * @return 查询到的数据集
     */
    public ResultSet execQuery( final String strSQL , final Object[] params ){
        try {
            this.getConn();
            pstmt = conn.prepareStatement(strSQL);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

    }

}
