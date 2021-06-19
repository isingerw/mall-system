package com.singerw.entity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:05
 * @Description: //TODO 购物车信息表(tbl_cart)实体类
 */
public class CartEntity {
    private int sid;
    private int gid;
    private int gcount;
    private int cid;
    private int state;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGcount() {
        return gcount;
    }

    public void setGcount(int gcount) {
        this.gcount = gcount;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public CartEntity() {
    }

    public CartEntity(int sid, int gid, int gcount, int cid, int state) {
        this.sid = sid;
        this.gid = gid;
        this.gcount = gcount;
        this.cid = cid;
        this.state = state;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "sid=" + sid +
                ", gid=" + gid +
                ", gcount=" + gcount +
                ", cid=" + cid +
                ", state=" + state +
                '}';
    }
}
