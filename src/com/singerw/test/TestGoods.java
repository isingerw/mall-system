package com.singerw.test;

import com.singerw.dao.GoodsDao;
import com.singerw.entity.GoodsEntity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:40
 * @Description: //TODO GoodsDao类方法测试！
 */
public class TestGoods {
    public static void main(String[] args) {
        Test test = new Test();
//        test.addGoodsTest();
//        test.deleteGoodsTest();
        test.updateGoodsTest();
//        test.queryGoodsTest();
    }

}


class Test {
    GoodsDao goodsDao = new GoodsDao();

    public void addGoodsTest() {
        GoodsEntity goods = new GoodsEntity("iPhone", 12800, 10, "卖大米卖大米", 1);
        boolean flag = goodsDao.addGoods(goods);
        System.out.println(flag);
    }


    public void deleteGoodsTest() {
//        goodsDao.deleteGoodsById(9);
//        goodsDao.updataGoodsByState(1);
//        goodsDao.updataGoodsByStateShangjia(1);
    }

    public void updateGoodsTest() {
        // 先查
        GoodsEntity goodsById = goodsDao.getGoodsById(1);

        // 修改
        goodsById.setGinfo("我再做修改测试");

        // 调用
        boolean flag = goodsDao.updateGoods(goodsById);
        System.out.println(flag);

    }


    public void queryGoodsTest(){
//        goodsDao.getGoodsById(3);
//        goodsDao.getGoodsByLike("12");
    }
}