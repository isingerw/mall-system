package com.singerw.entity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:10
 * @Description: //TODO 订单详情表(tbl_orderdetail)实体类
 */
public class OrderDetailEntity {
    private int id;
    private String oid;
    private int gid ;
    private int gcount;
    private double gprice;
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

    public int getGcount() {
        return gcount;
    }

    public void setGcount(int gcount) {
        this.gcount = gcount;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(int id, String oid, int gid, int gcount, double gprice, double total) {
        this.id = id;
        this.oid = oid;
        this.gid = gid;
        this.gcount = gcount;
        this.gprice = gprice;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailEntity{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", gid=" + gid +
                ", gcount=" + gcount +
                ", gprice=" + gprice +
                ", total=" + total +
                '}';
    }
}
