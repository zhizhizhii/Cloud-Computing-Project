package com.zhi.pojo;

public class Product {

    private String p_id;
    private String p_name;
    private String p_category;
    private int price;
    private int cost;
    private int s_vol;

    public int getS_vol() {
        return s_vol;
    }

    public void setS_vol(int s_vol) {
        this.s_vol = s_vol;
    }

    public Product() {
    }

    public Product(String p_id, String p_name, String p_category, int price, int cost) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_category = p_category;
        this.price = price;
        this.cost = cost;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getP_category() {
        return p_category;
    }

    public void setP_category(String p_category) {
        this.p_category = p_category;
    }

}
