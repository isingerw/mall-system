package com.singerw.entity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:06
 * @Description: //TODO 留言信息表(tbl_msg)实体类
 */
public class MessageEntity {
    private int mid;
    private int gid;
    private String message;
    private int cid;
    private String pdate;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public MessageEntity() {
    }

    public MessageEntity(int mid, int gid, String message, int cid, String pdate) {
        this.mid = mid;
        this.gid = gid;
        this.message = message;
        this.cid = cid;
        this.pdate = pdate;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "mid=" + mid +
                ", gid=" + gid +
                ", message='" + message + '\'' +
                ", cid=" + cid +
                ", pdate='" + pdate + '\'' +
                '}';
    }
}
