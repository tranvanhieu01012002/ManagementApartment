package com.app.model;

public class Apartment {
    public Apartment(int number, String type, double price, int owner) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.owner = owner;
    }
    private int number;
    private String type;
    private double price ;
    private int owner;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
