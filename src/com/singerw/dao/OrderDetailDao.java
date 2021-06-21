package com.singerw.dao;

import com.singerw.entity.OrderDetailEntity;
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
     * @Description: //TODO 根据条件进行模糊查询 未实现功能
     */
    public List<OrderDetailEntity> getOrderDetailAdminByLike(String keywords) {
        String sql = "SELECT * FROM tbl_orderdetail WHERE oid LIKE ?";
        Object obj = DBUtil.exQuery(sql, OrderDetailEntity.class, keywords);
        // 返回值是List类型
        if (obj instanceof List) {
            List<OrderDetailEntity> list = (List) obj;
            return list;
        }
        return null;
    }


    /**
     * @param keywords
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-17 0:35
     * @Description: //TODO 根据条件进行模糊查询
     */
    public List<OrderDetailEntity> getOrderDetailUserByLike(String keywords) {
        String sql = "SELECT * FROM tbl_orderdetail WHERE oid LIKE ?";
        Object obj = DBUtil.exQuery(sql, OrderDetailEntity.class, keywords);
        // 返回值是List类型
        if (obj instanceof List) {
            List<OrderDetailEntity> list = (List) obj;
            return list;
        }
        return null;
    }

}
