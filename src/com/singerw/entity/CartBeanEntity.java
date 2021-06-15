package com.singerw.entity;

public class CartBeanEntity {

	private int sid;
	private int gid;
	private int gcount;
	private int cid;
	private String gname;
	private double gprice;

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

	public CartBeanEntity() {
		// TODO Auto-generated constructor stub
	}

	public CartBeanEntity(int sid, int gid, int gcount, int cid, String gname, double gprice) {
		super();
		this.sid = sid;
		this.gid = gid;
		this.gcount = gcount;
		this.cid = cid;
		this.gname = gname;
		this.gprice = gprice;
	}

	@Override
	public String toString() {
		return "CartBean [sid=" + sid + ", gid=" + gid + ", gcount=" + gcount + ", cid=" + cid + ", gname=" + gname
				+ ", gprice=" + gprice + "]";
	}

}
