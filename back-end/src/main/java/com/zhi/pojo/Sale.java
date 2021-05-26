package com.zhi.pojo;

public class Sale {

    private String year;
    private String month;
    private int total;


    public Sale() {
    }

    public Sale(String year, String month, int total) {
        this.year = year;
        this.month = month;
        this.total = total;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
