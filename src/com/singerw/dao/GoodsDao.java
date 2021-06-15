package com.singerw.dao;

import com.singerw.entity.GoodsEntity;
import com.singerw.tools.DBUtil;

import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:23
 * @Description: //TODO Goods商品的基本 增加，删除，修改和查询
 */
public class GoodsDao {

    /**
     * @param goods 商品对象
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:41
     * @Description: //TODO 添加商品
     */
    public boolean addGoods(GoodsEntity goods) {
        if (goods == null) {
            return false;
        }
        String sql = "insert into tbl_goods values(null,?,?,?,?,?)";
        // 增加调用DBUtil.exupdate方法
        int n = DBUtil.exUpdate(sql, goods.getGname(), goods.getGprice(), goods.getGstock(), goods.getGinfo(), goods.getGstate());
        return n > 0;
    }

    /**
     * @param gid
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:54
     * @Description: //TODO 删除商品(物理删除或直接删除)
     */
    public boolean deleteGoodsById(int gid) {
        String sql = "DELETE FROM tbl_goods WHERE gid = ?";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, gid);
        return n > 0;
    }

    /**
     * @param gid
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:56
     * @Description: //TODO 删除商品(逻辑删除->类似下架)
     */
    public boolean updataGoodsByState(int gid) { //下架
        String sql = "update tbl_goods set gstate = 0 where gid = ?";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, gid);
        return n > 0;
    }

    public boolean updataGoodsByStateShangjia(int gid) { //上架
        String sql = "update tbl_goods set gstate = 1 where gid = ?";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, gid);
        return n > 0;
    }

    /**
     * @param goods 商品对象
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:59
     * @Description: //TODO 修改商品信息
     */
    public boolean updateGoods(GoodsEntity goods) {
        String sql = "update tbl_goods set gname = ?,gprice = ?,gstock = ?,ginfo = ?,gstate = ? where gid = ? ";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, goods.getGname(), goods.getGprice(), goods.getGstock(), goods.getGinfo(),
                goods.getGstate(), goods.getGid());
        return n > 0;
    }

    /**
     * @param gid 商品编号
     * @return Goods 商品对象
     * @Author CodeSleep
     * @Date: 2021-06-10 23:05
     * @Description: //TODO 根据id查询单个记录
     */
    public GoodsEntity getGoodsById(int gid) {
        String sql = "SELECT * from tbl_goods WHERE gid = ?";
        Object obj = DBUtil.exQuery(sql, GoodsEntity.class, gid);

        // 返回值是List类型
        if (obj instanceof List) {
            List<GoodsEntity> list = (List) obj;
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        }
        return null;
    }


    /**
     * @param keywords 查询关键字
     * @return List集合
     * @Author CodeSleep
     * @Date: 2021-06-10 23:06
     * @Description: //TODO 根据条件进行模糊查询
     */
    public List<GoodsEntity> getGoodsByLike(String keywords) {
        String sql = "SELECT * from tbl_goods where gname like ? or ginfo like ?";
        Object obj = DBUtil.exQuery(sql, GoodsEntity.class, keywords, keywords);

        if (obj instanceof List) {
            List<GoodsEntity> list = (List) obj;
            return list;
        }
        return null;
    }


    /**
     * @param keywords
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-15 22:16
     * @Description: //TODO 要查询的是已经上架的商品，且库存大于0
     */
    public List<GoodsEntity> getGoodsByLikeAndState(String keywords) {
        String sql = "select * from tbl_goods where gstate=1 and gstock>0 and  (gname like ? or ginfo like ?)";
        Object obj = DBUtil.exQuery(sql, GoodsEntity.class, keywords, keywords);
        if (obj instanceof List) {
            List<GoodsEntity> list = (List) obj;
            return list;
        }
        return null;
    }

}
