package com.singerw.test;

import com.singerw.dao.UserDao;
import com.singerw.entity.UserEntity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:40
 * @Description: //TODO ������
 */
public class TestUser {
    public static void main(String[] args) {


    }
}

class User {
    UserDao userdao = new UserDao();

    public void addUser() {
        UserEntity user = new UserEntity(0, "singerw", "assam1314520", "13100000001", "����˼�������԰����", 0, "2021-06-10 16:12:49");
        boolean flag = userdao.addUser(user);
        System.out.println(flag);
    }


    public void deleteUsersById() {
        boolean flag = userdao.deleteUsersById(7);
        System.out.println(flag);
    }


    public void updateUsersById() {

    }


    public void queryUsersById() {

    }

}
