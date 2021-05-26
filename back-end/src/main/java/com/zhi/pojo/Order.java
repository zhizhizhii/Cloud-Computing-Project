package com.zhi.pojo;

public class Order {
    private int o_id;
    private String c_id;
    private String c_name;
    private int c_sex;
    private String o_time;
    private int o_num;

    public Order() {
    }

    public Order(int o_id, String c_id, String c_name, int c_sex, String o_time, int o_num) {
        this.o_id = o_id;
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_sex = c_sex;
        this.o_time = o_time;
        this.o_num = o_num;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
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

    public String getO_time() {
        return o_time;
    }

    public void setO_time(String o_time) {
        this.o_time = o_time;
    }

    public int getO_num() {
        return o_num;
    }

    public void setO_num(int o_num) {
        this.o_num = o_num;
    }
}
