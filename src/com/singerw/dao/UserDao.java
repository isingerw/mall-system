package com.singerw.dao;

import com.singerw.entity.GoodsEntity;
import com.singerw.entity.UserEntity;
import com.singerw.tools.DBUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 23:18
 * @Description: //TODO 用户的注册增加，注销删除，修改和查询
 */
public class UserDao {

    /**
     * @param users 用户对象
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:41
     * @Description: //TODO 添加用户
     */
    public boolean addUser(UserEntity users) {
        if (users == null) {
            return false;
        }
        String sql = "insert into tbl_user values(null,?,?,?,?,?,?)";
        // 增加调用DBUtil.exupdate方法
        int n = DBUtil.exUpdate(sql, users.getCname(), users.getCpwd(), users.getCphone(), users.getCaddress(), users.getLevel(), users.getLastlogin());
        return n > 0;
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 22:23
     * @Description: //TODO 用户注册
     * @param users
     * @return
     */
    public boolean zhuceUser(UserEntity users) {

        if (users == null) {
            return false;
        }
        String sql = "insert into tbl_user values(null,?,?,?,?,?)";
        // 增加调用DBUtil.exupdate方法
        int n = DBUtil.exUpdate(sql, users.getCname(), users.getCpwd(), users.getCphone(), users.getCaddress(), users.getLastlogin());
        return n > 0;
    }

    /**
     * @param cid
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:54
     * @Description: //TODO 删除用户(物理删除或直接删除)
     */
    public boolean deleteUsersById(int cid) {
        String sql = "DELETE FROM tbl_user WHERE `cid` = ?";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, cid);
        return n > 0;
    }


    /**
     * @param users 用户对象
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:59
     * @Description: //TODO 修改用户信息
     */
    public boolean updateUsers(UserEntity users) {
        String sql = "update tbl_goods set cname = ?,cpwd = ?,cphone = ?,caddress = ?, 0 ,lastlogin = ? where gid = ? ";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, users.getCname(), users.getCpwd(), users.getCphone(), users.getCaddress(), users.getLevel(), users.getLastlogin());
        return n > 0;
    }


    /**
     * @param cid 用户编号
     * @return Goods 商品对象
     * @Author CodeSleep
     * @Date: 2021-06-10 23:05
     * @Description: //TODO 根据id查询单个记录
     */
    public GoodsEntity getUserById(int cid) {
        String sql = "select * form tbl_user where cid = ?";
        Object obj = DBUtil.exQuery(sql, GoodsEntity.class, cid);

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
    public List<UserEntity> getUserByLike(String keywords) {
        String sql = "select * from tbl_user where cid like ? or cname like ?";
        Object obj = DBUtil.exQuery(sql, UserEntity.class, keywords, keywords);

        if (obj instanceof List) {
            List<UserEntity> list = (List) obj;
            return list;
        }
        return null;
    }


    /**
     * @param cname
     * @param cpwd
     * @return
     * @Author CodeSleep
     * @Date: 2021-06-11 13:54
     * @Description: //TODO 根据用户名和密码查询用户信息
     */
    public UserEntity getUserByNameAndPwd(String cname, String cpwd) {
        String sql = "select * from tbl_user where cname=? and cpwd=?";
        Object obj = DBUtil.exQuery(sql, UserEntity.class, cname, cpwd);

        // 返回值是List类型
        if (obj instanceof List) {
            List<UserEntity> list = (List) obj;
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }


}
