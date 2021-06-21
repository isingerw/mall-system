package com.singerw.dao;

import com.singerw.entity.OrderAndUserEntity;
import com.singerw.entity.OrderDetailEntity;
import com.singerw.entity.OrderEntity;
import com.singerw.tools.CommonInfo;
import com.singerw.tools.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-12 21:43
 * @Description: //TODO ������(�ܱ�) (tbl_order)�Ļ������ ɾ�� �޸� �Ͳ�ѯ
 */
public class OrderDao {


    /**
     * @param orders
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-12 22:47
     * @Description: //TODO ��Ӷ�����Ϣ
     */
    public boolean addOrder(OrderEntity orders, List<OrderDetailEntity> orderDetailEntityList) {
        if (orders == null) {
            return false;
        }
        String order_sql = "INSERT INTO `mall_db`.`tbl_order` (`oid`, `cid`, `odate`, `address`, `total`) VALUES (?, ?,now(), ?, ?)";
        String orderdetail_sql = "INSERT INTO `mall_db`.`tbl_orderdetail` (`id`, `oid`, `gid`, `gcount`, `gprice`, `total`) VALUES (null,?, ?, ?, ?, ?)";
        String updategstact_sql = "UPDATE `mall_db`.`tbl_goods` SET `gstock` = `gstock`- ? WHERE `gid` = ?";
        String updatestate_sql = "UPDATE `mall_db`.`tbl_cart` SET `state` = 0 ,gcount = 0 WHERE `cid` = ? and state = 1";
        // ִ�����Ӳ���
        // ����pstmt����
        Connection conn = null;
        try {
            conn = DBUtil.getConn();
            PreparedStatement pstmt = null;
            // ���������Զ��ύ
            conn.setAutoCommit(false);

            // ************** 1����������¼insert ��ʼ **************
            pstmt = conn.prepareStatement(order_sql);
            // ����ռλ����Ӧ����ֵ
            pstmt.setString(1, orders.getOid());
            pstmt.setInt(2, orders.getCid());
            pstmt.setString(3, orders.getAddress());
            pstmt.setDouble(4, orders.getTotal());
            System.out.println("���������pstmt:" + pstmt);
            // ִ����ɾ�ķ���
            int n = pstmt.executeUpdate();
            // ************** 1����������¼insert ���� **************


            // ************** 2�������������¼insert ��ʼ **************
            pstmt = conn.prepareStatement(orderdetail_sql);
            for (OrderDetailEntity orderDetailEntity : orderDetailEntityList) {
                // ����ռλ����Ӧ����ֵ
                pstmt.setString(1, orderDetailEntity.getOid());
                pstmt.setInt(2, orderDetailEntity.getGid());
                pstmt.setInt(3, orderDetailEntity.getGcount());
                pstmt.setDouble(4, orderDetailEntity.getGprice());
                pstmt.setDouble(5, orderDetailEntity.getTotal());
                System.out.println("������������pstmt:" + pstmt);
                // ִ����ɾ�ķ���
                pstmt.addBatch();
            }
            // ͳһ��������ִ��һ�����Ӳ���
            int[] m = pstmt.executeBatch();
            // ************** 2�������������¼insert ���� **************


            // ************** 3������ tbl_goods gstock ��ʼ **************
            pstmt = conn.prepareStatement(updategstact_sql);
            for (OrderDetailEntity orderDetailEntity : orderDetailEntityList) {
                // ����ռλ����Ӧ����ֵ
                pstmt.setInt(1, orderDetailEntity.getGcount());
                pstmt.setInt(2, orderDetailEntity.getGid());
                System.out.println("������pstmt:" + pstmt);
                // ִ����ɾ�ķ���
                pstmt.addBatch();
            }
            // ͳһ��������ִ��һ�����Ӳ���
            int[] o = pstmt.executeBatch();
            // ************** 3������ tbl_goods gstock ���� **************


            // ************** 4ɾ�����ﳵ��¼ �߼�ɾ�� tbl_cart ��ʼ **************
            pstmt = conn.prepareStatement(updatestate_sql);
            pstmt.setInt(1, CommonInfo.cid);

            int p = pstmt.executeUpdate();
            System.out.println("p" + p);
            // ************** 4ɾ�����ﳵ��¼ �߼�ɾ�� tbl_cart ���� **************

            // �����ύ
            conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            try {
                conn.rollback();
                return false;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    /**
     * @param keywords
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-21 16:11
     * @Description: //TODO ����Ա�����������ж���ģ����ѯ
     */
    public List<OrderAndUserEntity> getOrderAdminByLike(String keywords) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT tbl_order.oid,tbl_user.cid,tbl_user.cname,tbl_order.odate,tbl_order.address,tbl_order.total FROM tbl_order INNER JOIN tbl_user ON tbl_order.cid = tbl_user.cid");
        sql.append(" " + "WHERE tbl_order.oid like ?");
        Object obj = DBUtil.exQuery(sql.toString(), OrderAndUserEntity.class, keywords);
        // ����ֵ��List����
        if (obj instanceof List) {
            List<OrderAndUserEntity> list = (List) obj;
            return list;
        }
        return null;
    }


    /**
     * @param keywords
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-17 0:35
     * @Description: //TODO �û������������ж���ģ����ѯ
     */
    public List<OrderAndUserEntity> getOrderUserByLike(String keywords, int cid) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT tbl_order.oid,tbl_user.cid,tbl_user.cname,tbl_order.odate,tbl_order.address,tbl_order.total FROM tbl_order INNER JOIN tbl_user ON tbl_order.cid = tbl_user.cid");
        sql.append(" " + "WHERE tbl_order.oid like ? AND tbl_user.cid = ?");
        Object obj = DBUtil.exQuery(sql.toString(), OrderAndUserEntity.class, keywords, cid);
        // ����ֵ��List����
        if (obj instanceof List) {
            List<OrderAndUserEntity> list = (List) obj;
            return list;
        }
        return null;
    }


    /**
     * @param orders ��������
     * @return true �����ɹ� false ����ʧ��
     * @Author CodeSleep
     * @Date: 2021-06-10 22:59
     * @Description: //TODO �޸Ķ�����Ϣ������δʵ��
     */
    /*public boolean updateOrder(OrderEntity orders) {
        String sql = "update tbl_order set address = ? where oid = ?";
        // ����DButil.exUpdate����
        int n = DBUtil.exUpdate(sql, orders.getAddress(), orders.getOid());
        return n > 0;
    }*/


    /**
     * @param oid ��Ʒ���
     * @return Goods ��Ʒ����
     * @Author CodeSleep
     * @Date: 2021-06-10 23:05
     * @Description: //TODO ����id��ѯ������¼������δʵ��
     */
    /*public OrderEntity getOrderById(String oid) {
        String sql = "SELECT * from tbl_order WHERE oid = ?";
        Object obj = DBUtil.exQuery(sql, OrderEntity.class, oid);
        // ����ֵ��List����
        if (obj instanceof List) {
            List<OrderEntity> list = (List) obj;
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        }
        return null;
    }*/
}