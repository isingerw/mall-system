package com.singerw.tools;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:20
 * @Description: //TODO db����ͨ�ò���
 */
public class DBUtil {

    /**
     * @return Connection����
     * @throws ClassNotFoundException
     * @throws SQLException
     * @Author CodeSleep
     * @Date: 2021-06-10 22:20
     * @Description: //TODO ��ȡ���Ӷ���ķ���
     */
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // ��ȡ���� Connection conn = DriverManager.getConnection(url,user,password)
        String url = "jdbc:mysql://localhost:3306/mall_db?serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "795200";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * @param sql    Ҫִ�е�sql���
     * @param params ռλ��?��Ӧ������
     * @return int ��Ӱ�����
     * @Author CodeSleep
     * @Date: 2021-06-10 22:20
     * @Description: //TODO ͨ�õ����� ɾ�����޸ķ���
     */
    public static int exUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int n = 0;
        try {
            // ��ȡ���Ӷ���
            conn = getConn();
            // ����pstmt����
            pstmt = conn.prepareStatement(sql);
            // parmas��������������
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    // ����ռλ����Ӧ����ֵ
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            System.out.println("pstmt :" + pstmt);
            // ִ����ɾ�ķ���
            n = pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // �ͷ���Դ
            closeAll(null, pstmt, conn);
        }
        // ������Ӱ�����
        return n;
    }

    /**
     * @param sql    sql���
     * @param cls    Class���Ͷ���
     * @param params ռλ������
     * @return Object ʵ������һ��List����
     * @Author CodeSleep
     * @Date: 2021-06-10 22:21
     * @Description: //TODO ͨ�ò�ѯ����
     */
    public static Object exQuery(String sql, Class cls, Object... params) {
        // list���Ǵ�Ų�ѯ���������
        List list = new ArrayList();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ���Ӷ���
            conn = getConn();
            // ����pstmt����
            pstmt = conn.prepareStatement(sql);
            // parmas��������������
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    // ����ռλ����Ӧ����ֵ
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            System.out.println("pstmt :" + pstmt);
            // ִ�в�ѯ�ķ���
            rs = pstmt.executeQuery();

            //�ж�Class -->��ѯ����ֵ
            if (cls.getName().equals("java.lang.Object")) {
                //����ֵ
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            // ��ѯ����� -> ����
            while (rs.next()) {

                //�õ�һ�����ݾ͵���convert�������е�������䵽һ�������У�Ȼ�󷵻�
                Object obj = convert(cls, rs);
                //��������ӵ�list������
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
            // �ͷ���Դ
            closeAll(rs, pstmt, conn);
        }
        // ���ؽ��
        return list;
    }

    /**
     * @param cls Classs
     * @param rs  �����
     * @return Object
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InvocationTargetException
     * @Author CodeSleep
     * @Date: 2021-06-10 22:21
     * @Description: //TODO �����ת��ΪClass����
     */
    private static Object convert(Class cls, ResultSet rs)
            throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException {
        Object obj = cls.newInstance();
        // ������ ResultSet������е����������ͺ����ԡ�
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            String cname = rsmd.getColumnLabel(i);
            // ��Դ�����beanutil
            // 1-��������
            // 2->������
            // 3->���Ե�ֵ
            BeanUtils.setProperty(obj, cname, rs.getObject(cname));
        }
        return obj;
    }

    /**
     * @param rs    �����ResultSet����
     * @param pstmt Ԥ������� PreparedStatement
     * @param conn  ���Ӷ��� Connection
     * @Author CodeSleep
     * @Date: 2021-06-10 22:21
     * @Description: //TODO �ͷ���Դ
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
