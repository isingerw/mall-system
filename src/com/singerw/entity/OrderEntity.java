package com.singerw.entity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:09
 * @Description: //TODO 订单表(总表) (tbl_order)实体类
 */
public class OrderEntity {
    private String oid;
    private int cid;
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

    public OrderEntity() {
    }

    public OrderEntity(String oid, int cid, String odate, String address, double total) {
        this.oid = oid;
        this.cid = cid;
        this.odate = odate;
        this.address = address;
        this.total = total;
    }

    public OrderEntity(String oid, int cid, String odate, double total) {
        this.oid = oid;
        this.cid = cid;
        this.odate = odate;
        this.address = address;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "oid='" + oid + '\'' +
                ", cid=" + cid +
                ", odate='" + odate + '\'' +
                ", address='" + address + '\'' +
                ", total=" + total +
                '}';
    }
}
