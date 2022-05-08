package com.app.model;

public class Person {
    private String name;
    private int age;
    private int phone;
    private int id;

    public Person(String name, int age, int phone, int id) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.id = id;
    }
    public  Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
