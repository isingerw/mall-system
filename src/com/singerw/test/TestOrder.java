//package com.singerw.test;
//
//import com.singerw.dao.OrderDao;
//import com.singerw.entity.GoodsEntity;
//import com.singerw.entity.OrderEntity;
//
//import java.util.List;
//
///**
// * @author Zhang_Singer
// */
//public class TestOrder {
//    public static void main(String[] args) {
//        Test1 test1 = new Test1();
//        test1.addOrderTest();
//        test1.deleteOrderTest();
//        test1.updateOrderTest();
//        test1.queryOrderTest();
//
//    }
//}
//
//
//class Test1 {
//    OrderDao orderDao = new OrderDao();
//
//    public void addOrderTest() {
//        OrderEntity orderEntity = new OrderEntity("202120154652", 7, "2020-12-23 12:57:53", "���԰����", 56.56);
//        boolean flag = orderDao.addOrder(orderEntity);
//        System.out.println(flag);
//
//    }
//
//
//    public void deleteOrderTest() {
//        // ��
//    }
//
//    public void updateOrderTest() {
//        // �Ȳ�
//        OrderEntity orderById = orderDao.getOrderById("201720154645");
//        // �޸�
//        orderById.setAddress("88888888888��ַ����");
//        // ����
//        boolean flag = orderDao.updateOrder(orderById);
//        System.out.println(flag);
//
//    }
//
//
//    public void queryOrderTest() {
//        OrderEntity orderById = orderDao.getOrderById("20216101615501234");
//        System.out.println(orderById);
//
//        List<OrderEntity> address = orderDao.getOrderByLike("%���԰%");
//        address.forEach(System.out::println);
//    }
//
//}