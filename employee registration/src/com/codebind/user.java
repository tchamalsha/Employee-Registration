package com.codebind;

import java.util.Date;

public class user {
    String name;
    String id;
    String address;
    long telephone;
    double salary;
    Date date;
    Boolean epf_etf;


    public user(String name, String id, String address, long telephone, double salary, Date date) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.telephone = telephone;
        this.salary = salary;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public long getTelephone() {
        return telephone;
    }

    public double getSalary() {
        return salary;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
