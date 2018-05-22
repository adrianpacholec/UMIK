package com.example.adrian.umik;

public class Person {
    private String param, name, descr;
    private Integer picture;

    public Person(String param, String name, String descr, Integer picture) {
        this.param = param;
        this.name = name;
        this.descr = descr;
        this.picture = picture;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }
}
