package com.singerw.dao;

import com.singerw.entity.MessageEntity;
import com.singerw.tools.DBUtil;

import java.util.List;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-13 0:09
 * @Description: //TODO 留言信息表(tbl_msg) 留言的基本 增加，删除，修改和查询
 */
public class MessageDao {


    /**
     * @param messages 商品对象
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:41
     * @Description: //TODO 添加留言
     */
    public boolean addMessage(MessageEntity messages) {
        if (messages == null) {
            return false;
        }
        String sql = "insert into tbl_msg values(null,?,?,?,?)";
        // 增加调用DBUtil.exupdate方法
        int n = DBUtil.exUpdate(sql, messages.getGid(), messages.getMessage(), messages.getCid(), messages.getPdate());
        return n > 0;
    }


    /**
     * @param mid
     * @return true 操作成功 false 操作失败
     * @Author CodeSleep
     * @Date: 2021-06-10 22:54
     * @Description: //TODO 删除留言(物理删除或直接删除)
     */
    public boolean deleteMessageById(int mid) {
        String sql = "DELETE FROM tbl_msg WHERE mid = ?";
        // 调用DButil.exUpdate方法
        int n = DBUtil.exUpdate(sql, mid);
        return n > 0;
    }


    /**
     * @param mid 商品编号
     * @return Goods 商品对象
     * @Author CodeSleep
     * @Date: 2021-06-10 23:05
     * @Description: //TODO 根据id查询单个记录
     */
    public MessageEntity getMessageById(int mid) {
        String sql = "SELECT * from tbl_msg WHERE mid = ?";
        Object obj = DBUtil.exQuery(sql, MessageEntity.class, mid);
        // 返回值是List类型
        if (obj instanceof List) {
            List<MessageEntity> list = (List) obj;
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
    public List<MessageEntity> getMessageByLike(String keywords) {
        String sql = "SELECT * from tbl_msg where cid like ? or gid like ?";
        Object obj = DBUtil.exQuery(sql, MessageEntity.class, keywords, keywords);
        if (obj instanceof List) {
            List<MessageEntity> list = (List) obj;
            return list;
        }
        return null;
    }


}
