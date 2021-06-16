//package com.singerw.test;
//
//import com.singerw.dao.MessageDao;
//import com.singerw.entity.MessageEntity;
//import sun.plugin2.message.Message;
//
//import java.util.List;
//
///**
// * @Author: CodeSleep
// * @Date: 2021-06-13 0:09
// * @Description: //TODO ???????? ????????????????????
// */
//public class TestMessage {
//    public static void main(String[] args) {
//        TestMassage1 t1 = new TestMassage1();
////        t1.addMassageTest();
////        t1.deleteMessageTest();
//        t1.queryMessageTest();
//    }
//}
//
//
//class TestMassage1 {
//
//    MessageDao md = new MessageDao();
//
//    public void addMassageTest() {
//        MessageEntity me = new MessageEntity(1, 20171546, "?????????????", 6, "2021-09-12 12:12");
//        boolean flag = md.addMessage(me);
//        System.out.println(flag);
//    }
//
//    public void deleteMessageTest() {
//        boolean flag = md.deleteMessageById(3);
//        System.out.println(flag);
//    }
//
//    public void queryMessageTest(){
//        MessageEntity messageById = md.getMessageById(1);
//        System.out.println(messageById);
//
//        List<MessageEntity> messageByLike = md.getMessageByLike(20171546);
//        messageByLike.forEach(System.out::println);
//    }
//}
