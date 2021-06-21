package com.singerw.entity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-21 21:24
 * @Description: //TODO 用户订单详情实体类
 */
public class OrderDetailUserEntity {
    private int id;
    private String oid;
    private int gid;
    private double gprice;
    private int gcount;
    private int cid;
    private String cname;
    private double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderDetailUserEntity() {
    }

    public OrderDetailUserEntity(int id, String oid, int gid, double gprice, int gcount, int cid, String cname, double total) {
        this.id = id;
        this.oid = oid;
        this.gid = gid;
        this.gprice = gprice;
        this.gcount = gcount;
        this.cid = cid;
        this.cname = cname;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailUserEntity{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", gid=" + gid +
                ", gprice=" + gprice +
                ", gcount=" + gcount +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                ", total=" + total +
                '}';
    }
}
