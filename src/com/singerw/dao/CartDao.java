package com.singerw.dao;

import com.singerw.entity.CartBeanEntity;
import com.singerw.entity.CartEntity;
import com.singerw.tools.DBUtil;

import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-13 0:35
 * @Description: //TODO 购物车信息表(tbl_cart)实现基本 增加，删除，修改和查询
 */
public class CartDao {


    /**
     * @param cart
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-15 22:18
     * @Description: //TODO 添加商品到购物车中(只添加一件)
     */
    public boolean addCart(CartEntity cart) {
        if (cart == null) {
            return false;
        }
        // 查询看购物车是否存在该商品
        CartEntity cartEntity = getCartByGidAndCid(cart.getGid(), cart.getCid());
        String sql = "INSERT INTO `mall_db`.`tbl_cart`(`sid`,`gcount`,`gid`,`cid`,`state`) VALUES (null, ?, ?, ?, 1)";

        if (cartEntity != null) {
            sql = "update tbl_cart set gcount = gcount+? , state = 1 where gid = ? and cid = ? ";

        }
        // 增加调用DBUtil.exUpdate方法
        int n = DBUtil.exUpdate(sql, cart.getGcount(), cart.getGid(), cart.getCid());
        return n > 0;
    }


    /**
     * 判断购物车是否已经存在 该商品
     *
     * @param gid
     * @param cid
     * @return CartBeanEntity
     */
    public CartEntity getCartByGidAndCid(int gid, int cid) {

        String sql = "select * from tbl_cart where gid = ? and cid=?";
        Object obj = DBUtil.exQuery(sql, CartEntity.class, gid, cid);

        // 返回值是List类型
        if (obj instanceof List) {
            List<CartEntity> list = (List) obj;
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }


    /**
     * 查询某个客户的购物车(商品编号，商品名称，客户端编号，商品数量等，购物车表，商品表也有，多表联接查询)
     *
     * @param cid 客户编号
     * @return List<Cart>
     */
    public List<CartBeanEntity> getCartBycid(int cid) {
        String sql = "SELECT tbl_cart.gid,tbl_cart.gcount,tbl_cart.cid,tbl_goods.gname,tbl_goods.gprice FROM tbl_cart INNER JOIN tbl_goods ON tbl_cart.gid=tbl_goods.gid WHERE tbl_cart.cid=? and tbl_cart.state=1";
        Object obj = DBUtil.exQuery(sql, CartBeanEntity.class, cid);
        // 返回值是List类型
        if (obj instanceof List) {
            List<CartBeanEntity> list = (List) obj;
            return list;
        }
        return null;
    }


}
