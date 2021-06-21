package com.singerw.dao;

import com.singerw.entity.OrderDetailEntity;
import com.singerw.entity.OrderDetailUserEntity;
import com.singerw.tools.DBUtil;

import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-13 0:36
 * @Description: //TODO 订单详情表(tbl_orderdetail)实现基本 增加，删除，修改和查询
 */
public class OrderDetailDao {


    /**
     * @param keywords
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-17 0:35
     * @Description: //TODO 用户根据条件进行模糊查询
     */
    public List<OrderDetailUserEntity> getOrderDetailUserByLike(String keywords, int cid) {
        StringBuilder sql = new StringBuilder();
        sql.append(" " + "SELECT tbl_orderdetail.id,tbl_orderdetail.oid,tbl_orderdetail.gid,tbl_orderdetail.gprice,tbl_orderdetail.gcount,tbl_user.cid,tbl_user.cname,tbl_orderdetail.total");
        sql.append(" " + "FROM tbl_orderdetail INNER JOIN tbl_order ON tbl_orderdetail.oid = tbl_order.oid INNER JOIN tbl_user ON tbl_order.cid = tbl_user.cid");
        sql.append(" " + "WHERE tbl_orderdetail.oid LIKE ? AND tbl_user.cid = ? ");
        Object obj = DBUtil.exQuery(sql.toString(), OrderDetailUserEntity.class, keywords, cid);
        // 返回值是List类型
        if (obj instanceof List) {
            List<OrderDetailUserEntity> list = (List) obj;
            return list;
        }
        return null;
    }


    /**
     * @param keywords
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-17 0:35
     * @Description: //TODO 用户根据条件进行模糊查询
     */
    public List<OrderDetailUserEntity> getOrderDetailAdminByLike(String keywords) {
        StringBuilder sql = new StringBuilder();
        sql.append(" " + "SELECT tbl_orderdetail.id,tbl_orderdetail.oid,tbl_orderdetail.gid,tbl_orderdetail.gprice,tbl_orderdetail.gcount,tbl_user.cid,tbl_user.cname,tbl_orderdetail.total");
        sql.append(" " + "FROM tbl_orderdetail INNER JOIN tbl_order ON tbl_orderdetail.oid = tbl_order.oid INNER JOIN tbl_user ON tbl_order.cid = tbl_user.cid");
        sql.append(" " + "WHERE tbl_orderdetail.oid LIKE ?");
        Object obj = DBUtil.exQuery(sql.toString(), OrderDetailUserEntity.class, keywords);
        // 返回值是List类型
        if (obj instanceof List) {
            List<OrderDetailUserEntity> list = (List) obj;
            return list;
        }
        return null;
    }

}
