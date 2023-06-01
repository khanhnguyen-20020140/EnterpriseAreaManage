package com.sgis.khukt.model;

public class Doanhthu {
    String startDate;

    String endDate;

    Double avgDoanhthu;

    public Doanhthu(String startDate, String endDate, Double avgDoanhthu) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.avgDoanhthu = avgDoanhthu;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getAvgDoanhthu() {
        return avgDoanhthu;
    }

    public void setAvgDoanhthu(Double avgDoanhthu) {
        this.avgDoanhthu = avgDoanhthu;
    }
}
