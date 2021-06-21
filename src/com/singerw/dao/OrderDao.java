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
 * @Description: //TODO 订单表(总表) (tbl_order)的基本添加 删除 修改 和查询
 */
public class OrderDao {


    /**
     * @param orders
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-12 22:47
     * @Description: //TODO 添加订单信息
     */
    public boolean addOrder(OrderEntity orders, List<OrderDetailEntity> orderDetailEntityList) {
        if (orders == null) {
            return false;
        }
        String order_sql = "INSERT INTO `mall_db`.`tbl_order` (`oid`, `cid`, `odate`, `address`, `total`) VALUES (?, ?,now(), ?, ?)";
        String orderdetail_sql = "INSERT INTO `mall_db`.`tbl_orderdetail` (`id`, `oid`, `gid`, `gcount`, `gprice`, `total`) VALUES (null,?, ?, ?, ?, ?)";
        String updategstact_sql = "UPDATE `mall_db`.`tbl_goods` SET `gstock` = `gstock`- ? WHERE `gid` = ?";
        String updatestate_sql = "UPDATE `mall_db`.`tbl_cart` SET `state` = 0 ,gcount = 0 WHERE `cid` = ? and state = 1";
        // 执行增加操作
        // 创建pstmt对象
        Connection conn = null;
        try {
            conn = DBUtil.getConn();
            PreparedStatement pstmt = null;
            // 设置事务不自动提交
            conn.setAutoCommit(false);

            // ************** 1订单表插入记录insert 开始 **************
            pstmt = conn.prepareStatement(order_sql);
            // 设置占位符对应参数值
            pstmt.setString(1, orders.getOid());
            pstmt.setInt(2, orders.getCid());
            pstmt.setString(3, orders.getAddress());
            pstmt.setDouble(4, orders.getTotal());
            System.out.println("订单表插入pstmt:" + pstmt);
            // 执行增删改方法
            int n = pstmt.executeUpdate();
            // ************** 1订单表插入记录insert 结束 **************


            // ************** 2订单详情表插入记录insert 开始 **************
            pstmt = conn.prepareStatement(orderdetail_sql);
            for (OrderDetailEntity orderDetailEntity : orderDetailEntityList) {
                // 设置占位符对应参数值
                pstmt.setString(1, orderDetailEntity.getOid());
                pstmt.setInt(2, orderDetailEntity.getGid());
                pstmt.setInt(3, orderDetailEntity.getGcount());
                pstmt.setDouble(4, orderDetailEntity.getGprice());
                pstmt.setDouble(5, orderDetailEntity.getTotal());
                System.out.println("订单详情表插入pstmt:" + pstmt);
                // 执行增删改方法
                pstmt.addBatch();
            }
            // 统一发送请求，执行一遍增加操作
            int[] m = pstmt.executeBatch();
            // ************** 2订单详情表插入记录insert 结束 **************


            // ************** 3库存减少 tbl_goods gstock 开始 **************
            pstmt = conn.prepareStatement(updategstact_sql);
            for (OrderDetailEntity orderDetailEntity : orderDetailEntityList) {
                // 设置占位符对应参数值
                pstmt.setInt(1, orderDetailEntity.getGcount());
                pstmt.setInt(2, orderDetailEntity.getGid());
                System.out.println("库存减少pstmt:" + pstmt);
                // 执行增删改方法
                pstmt.addBatch();
            }
            // 统一发送请求，执行一遍增加操作
            int[] o = pstmt.executeBatch();
            // ************** 3库存减少 tbl_goods gstock 结束 **************


            // ************** 4删除购物车记录 逻辑删除 tbl_cart 开始 **************
            pstmt = conn.prepareStatement(updatestate_sql);
            pstmt.setInt(1, CommonInfo.cid);

            int p = pstmt.executeUpdate();
            System.out.println("p" + p);
            // ************** 4删除购物车记录 逻辑删除 tbl_cart 结束 **************

            // 事务提交
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
     * @Description: //TODO 管理员根据条件进行订单模糊查询
     */
    public List<OrderAndUserEntity> getOrderAdminByLike(String keywords) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT tbl_order.oid,tbl_user.cid,tbl_user.cname,tbl_order.odate,tbl_order.address,tbl_order.total FROM tbl_order INNER JOIN tbl_user ON tbl_order.cid = tbl_user.cid");
        sql.append(" " + "WHERE tbl_order.oid like ?");
        Object obj = DBUtil.exQuery(sql.toString(), OrderAndUserEntity.class, keywords);
        // 返回值是List类型
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
     * @Description: //TODO 用户根据条件进行订单模糊查询
     */
    public List<OrderAndUserEntity> getOrderUserByLike(String keywords, int cid) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT tbl_order.oid,tbl_user.cid,tbl_user.cname,tbl_order.odate,tbl_order.address,tbl_order.total FROM tbl_order INNER JOIN tbl_user ON tbl_order.cid = tbl_user.cid");
        sql.append(" " + "WHERE tbl_order.oid like ? AND tbl_user.cid = ?");
        Object obj = DBUtil.exQuery(sql.toString(), OrderAndUserEntity.class, keywords, cid);
        // 返回值是List类型
        if (obj instanceof List) {
            List<OrderAndUserEntity> list = (List) obj;
            return list;
        }
        return null;
    }


    /**
     * @param orders 订单对象
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:59
     * @Description: //TODO 修改订单信息，功能未实现
     */
    /*public boolean updateOrder(OrderEntity orders) {
        String sql = "update tbl_order set address = ? where oid = ?";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, orders.getAddress(), orders.getOid());
        return n > 0;
    }*/


    /**
     * @param oid 商品编号
     * @return Goods 商品对象
     * @Author CodeSleep
     * @Date: 2021-06-10 23:05
     * @Description: //TODO 根据id查询单个记录，功能未实现
     */
    /*public OrderEntity getOrderById(String oid) {
        String sql = "SELECT * from tbl_order WHERE oid = ?";
        Object obj = DBUtil.exQuery(sql, OrderEntity.class, oid);
        // 返回值是List类型
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