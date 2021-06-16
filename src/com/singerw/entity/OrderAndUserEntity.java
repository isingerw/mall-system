package com.singerw.entity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-16 22:35
 * @Description: //TODO 用于订单查询的订单与用户实体类
 */
public class OrderAndUserEntity {
    private String oid;
    private int cid;
    private String cname;
    private String odate;
    private String address;
    private double total;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderAndUserEntity() {
    }

    public OrderAndUserEntity(String oid, int cid, String cname, String odate, String address, double total) {
        this.oid = oid;
        this.cid = cid;
        this.cname = cname;
        this.odate = odate;
        this.address = address;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderAndUserEntity{" +
                "oid='" + oid + '\'' +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                ", odate='" + odate + '\'' +
                ", address='" + address + '\'' +
                ", total=" + total +
                '}';
    }
}
