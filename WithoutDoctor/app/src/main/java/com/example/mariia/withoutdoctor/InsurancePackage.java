package com.example.mariia.withoutdoctor;

/**
 * Created by mariia on 10.12.2017.
 */

public class InsurancePackage {
    private String name;
    private String insuranceCompanyID;
    private String companyName;
    private String description;
    private int price;

    public InsurancePackage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsuranceCompanyID() {
        return insuranceCompanyID;
    }

    public void setInsuranceCompanyID(String insuranceCompanyID) {
        this.insuranceCompanyID = insuranceCompanyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public InsurancePackage(String name, String insuranceCompanyID, String companyName, String description, int price) {
        this.name = name;
        this.insuranceCompanyID = insuranceCompanyID;
        this.companyName = companyName;
        this.description = description;
        this.price = price;
    }
}