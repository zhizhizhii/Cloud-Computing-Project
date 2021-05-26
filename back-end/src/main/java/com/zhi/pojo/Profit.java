package com.zhi.pojo;

public class Profit {

    private String year;
    private String month;
    private int profit;

    public Profit() {
    }

    public Profit(String year, String month, int profit) {
        this.year = year;
        this.month = month;
        this.profit = profit;
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

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
