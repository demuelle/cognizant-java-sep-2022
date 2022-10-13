package com.example.peoplecounters.model;

import java.util.Objects;

public class DayCount {
    private int inCount;
    private int outCount;
    private int siteId;
    private String monthName;
    private int date;

    public int getInCount() {
        return inCount;
    }

    public void setInCount(int inCount) {
        this.inCount = inCount;
    }

    public int getOutCount() {
        return outCount;
    }

    public void setOutCount(int outCount) {
        this.outCount = outCount;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayCount dayCount = (DayCount) o;
        return inCount == dayCount.inCount && outCount == dayCount.outCount && siteId == dayCount.siteId && date == dayCount.date && Objects.equals(monthName, dayCount.monthName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inCount, outCount, siteId, monthName, date);
    }

    @Override
    public String toString() {
        return "DayCount{" +
                "inCount=" + inCount +
                ", outCount=" + outCount +
                ", siteId=" + siteId +
                ", monthName='" + monthName + '\'' +
                ", date=" + date +
                '}';
    }
}
