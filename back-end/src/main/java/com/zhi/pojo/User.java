package com.zhi.pojo;

import java.security.PrivilegedExceptionAction;

public class User {
    private String c_id;
    private String c_name;
    private int c_sex;
    private String data_dt;

    public User() {
    }

    public User(String c_id, String c_name, int c_sex, String data_dt) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_sex = c_sex;
        this.data_dt = data_dt;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getC_sex() {
        return c_sex;
    }

    public void setC_sex(int c_sex) {
        this.c_sex = c_sex;
    }

    public String getData_dt() {
        return data_dt;
    }

    public void setData_dt(String data_dt) {
        this.data_dt = data_dt;
    }
}
