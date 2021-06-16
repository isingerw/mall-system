package com.singerw.dao;

import com.singerw.entity.GoodsEntity;
import com.singerw.entity.OrderAndUserEntity;
import com.singerw.entity.OrderEntity;
import com.singerw.tools.DBUtil;

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
    public boolean addOrder(OrderEntity orders) {
        if (orders == null) {
            return false;
        }
        String sql = "insert into tbl_order values(?,?,?,?,?)";
        // ���ӵ���DBUtil.exupdate����
        int n = DBUtil.exUpdate(sql, orders.getOid(), orders.getCid(), orders.getOdate(), orders.getAddress(), orders.getTotal());
        return n > 0;
    }


    /**
     * @param orders ��������
     * @return true �����ɹ� false ����ʧ��
     * @Author CodeSleep
     * @Date: 2021-06-10 22:59
     * @Description: //TODO �޸Ķ�����Ϣ
     */
    public boolean updateOrder(OrderEntity orders) {
        String sql = "update tbl_order set address = ? where oid = ?";
        // ����DButil.exUpdate����
        int n = DBUtil.exUpdate(sql, orders.getAddress(), orders.getOid());
        return n > 0;
    }

    /**
     * @param oid ��Ʒ���
     * @return Goods ��Ʒ����
     * @Author CodeSleep
     * @Date: 2021-06-10 23:05
     * @Description: //TODO ����id��ѯ������¼
     */
    public OrderEntity getOrderById(String oid) {
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
    }

/**
 * @param oid
 * @param cid
 * @param cname
 * @return
 * @Author CodeSleep
 * @Date: 2021-06-17 0:15
 * @Description: //TODO ������������ģ����ѯ
 */
//    public List<OrderEntity> getOrderByLike(String keywords) {
//        String sql = "SELECT * from tbl_order where cid like ? or address like ? or cname like ?";
//        Object obj = DBUtil.exQuery(sql, OrderEntity.class, keywords, keywords,keywords);
//        if (obj instanceof List) {
//            List<OrderEntity> list = (List) obj;
//            return list;
//        }
//        return null;
//    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-17 0:35
     * @Description: //TODO ������������ģ����ѯ
     * @param keywords
     * @return
     */
    public List<OrderAndUserEntity> getOrderByLike(String keywords) {
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
}