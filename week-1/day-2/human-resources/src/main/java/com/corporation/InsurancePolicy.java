package com.corporation;

public class InsurancePolicy {
    private String groupId;
    private String carrier;
    private int year;

    public InsurancePolicy() {
    }

    public InsurancePolicy(String carrier) {
        this.carrier = carrier;
    }
//
//    public InsurancePolicy(String groupId) {
//        this.groupId = groupId;
//    }


    public InsurancePolicy(int year) {
        this.year = year;
    }

    public InsurancePolicy(String groupId, String carrier, int year) {
        this.groupId = groupId;
        this.carrier = carrier;
        this.year = year;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "groupId='" + groupId + '\'' +
                ", carrier='" + carrier + '\'' +
                ", year=" + year +
                '}';
    }


}
