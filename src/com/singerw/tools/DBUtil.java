package com.singerw.tools;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:20
 * @Description: //TODO db访问通用操作
 */
public class DBUtil {

    /**
     * @return Connection对象
     * @throws ClassNotFoundException
     * @throws SQLException
     * @Author CodeSleep
     * @Date: 2021-06-10 22:20
     * @Description: //TODO 获取连接对象的方法
     */
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接 Connection conn = DriverManager.getConnection(url,user,password)
        String url = "jdbc:mysql://localhost:3306/mall_db?serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "795200";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * @param sql    要执行的sql语句
     * @param params 占位符?对应的数据
     * @return int 受影响的行
     * @Author CodeSleep
     * @Date: 2021-06-10 22:20
     * @Description: //TODO 通用的增加 删除和修改方法
     */
    public static int exUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int n = 0;
        try {
            // 获取连接对象
            conn = getConn();
            // 创建pstmt对象
            pstmt = conn.prepareStatement(sql);
            // parmas当成数组来处理
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符对应参数值
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            System.out.println("pstmt :" + pstmt);
            // 执行增删改方法
            n = pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            closeAll(null, pstmt, conn);
        }
        // 返回受影响的行
        return n;
    }

    /**
     * @param sql    sql语句
     * @param cls    Class类型对象
     * @param params 占位符参数
     * @return Object 实际上是一个List对象
     * @Author CodeSleep
     * @Date: 2021-06-10 22:21
     * @Description: //TODO 通用查询方法
     */
    public static Object exQuery(String sql, Class cls, Object... params) {
        // list就是存放查询到到结果集
        List list = new ArrayList();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取连接对象
            conn = getConn();
            // 创建pstmt对象
            pstmt = conn.prepareStatement(sql);
            // parmas当成数组来处理
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符对应参数值
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            System.out.println("pstmt :" + pstmt);
            // 执行查询的方法
            rs = pstmt.executeQuery();

            //判断Class -->查询单个值
            if (cls.getName().equals("java.lang.Object")) {
                //单个值
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            // 查询多个列 -> 遍历
            while (rs.next()) {

                //得到一行数据就调用convert方法将列的数据填充到一个对象中，然后返回
                Object obj = convert(cls, rs);
                //将对象添加到list集合中
                list.add(obj);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            closeAll(rs, pstmt, conn);
        }
        // 返回结果
        return list;
    }

    /**
     * @param cls Classs
     * @param rs  结果集
     * @return Object
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InvocationTargetException
     * @Author CodeSleep
     * @Date: 2021-06-10 22:21
     * @Description: //TODO 结果集转换为Class对象
     */
    private static Object convert(Class cls, ResultSet rs)
            throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException {
        Object obj = cls.newInstance();
        // 检索此 ResultSet对象的列的数量，类型和属性。
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            String cname = rsmd.getColumnLabel(i);
            // 开源组件：beanutil
            // 1-》对象名
            // 2->属性名
            // 3->属性的值
            BeanUtils.setProperty(obj, cname, rs.getObject(cname));
        }
        return obj;
    }

    /**
     * @param rs    结果集ResultSet对象
     * @param pstmt 预处理对象 PreparedStatement
     * @param conn  连接对象 Connection
     * @Author CodeSleep
     * @Date: 2021-06-10 22:21
     * @Description: //TODO 释放资源
     */
    public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
