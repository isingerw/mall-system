package com.singerw.entity;


/**
 * @Author: CodeSleep
 * @Date: 2021-06-10 22:12
 * @Description: //TODO 用户信息表（tbl_user）实体类
 */
public class UserEntity {
    private int cid;
    private String cname;
    private String cpwd;
    private String cphone;
    private String caddress;
    private int level;
    private String lastlogin;

    public UserEntity(String cname, String cpwd, String cphone, String caddress) {
        this.cname = cname;
        this.cpwd = cpwd;
        this.cphone = cphone;
        this.caddress = caddress;
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

    public String getCpwd() {
        return cpwd;
    }

    public void setCpwd(String cpwd) {
        this.cpwd = cpwd;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public UserEntity() {
    }

    public UserEntity(int cid, String cname, String cpwd, String cphone, String caddress, int level, String lastlogin) {
        this.cid = cid;
        this.cname = cname;
        this.cpwd = cpwd;
        this.cphone = cphone;
        this.caddress = caddress;
        this.level = level;
        this.lastlogin = lastlogin;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cpwd='" + cpwd + '\'' +
                ", cphone='" + cphone + '\'' +
                ", caddress='" + caddress + '\'' +
                ", level=" + level +
                ", lastlogin='" + lastlogin + '\'' +
                '}';
    }
}
