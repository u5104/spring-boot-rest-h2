package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class AccountModel {

    private Integer id;
    private String currentQuota;
    private Double price;
    @JsonBackReference
    private CustomerModel customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrentQuota() {
        return currentQuota;
    }

    public void setCurrentQuota(String currentQuota) {
        this.currentQuota = currentQuota;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
