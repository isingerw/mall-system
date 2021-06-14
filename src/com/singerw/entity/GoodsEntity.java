package com.singerw.entity;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:01
 * @Description: //TODO 商品信息表(tbl_goods)实体类
 */
public class GoodsEntity {
    private int gid;
    private String gname;
    private double gprice;
    private int gstock;
    private String Ginfo;
    private int Gstate;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public int getGstock() {
        return gstock;
    }

    public void setGstock(int gstock) {
        this.gstock = gstock;
    }

    public String getGinfo() {
        return Ginfo;
    }

    public void setGinfo(String ginfo) {
        Ginfo = ginfo;
    }

    public int getGstate() {
        return Gstate;
    }

    public void setGstate(int gstate) {
        Gstate = gstate;
    }

    public GoodsEntity() {
    }

//    public GoodsEntity(int gid, String gname, double gprice, int gstock, String ginfo, int gstate) {
//        this.gid = gid;
//        this.gname = gname;
//        this.gprice = gprice;
//        this.gstock = gstock;
//        Ginfo = ginfo;
//        Gstate = gstate;
//    }

    public GoodsEntity(String gname, double gprice, int gstock, String ginfo, int gstate) {
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gstock = gstock;
        Ginfo = ginfo;
        Gstate = gstate;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gprice=" + gprice +
                ", gstock=" + gstock +
                ", Ginfo='" + Ginfo + '\'' +
                ", Gstate=" + Gstate +
                '}';
    }
}
