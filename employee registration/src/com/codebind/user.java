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


    public user(String name, String id, String address, long telephone, double salary, Date date,boolean epf_etf) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.telephone = telephone;
        this.salary = salary;
        this.date = date;
        this.epf_etf=epf_etf;
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

    public Boolean getEpf_etf() {
        return epf_etf;
    }

    public void setEpf_etf(Boolean epf_etf) {
        this.epf_etf = epf_etf;
    }
}
