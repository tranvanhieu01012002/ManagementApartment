package com.app.model;

public class Travel {
    private String name;
    private  String time;
    private  String start_end;
    private String price;
    private String img;
    public Travel(){

    }
    // getter setter for class
    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStart_end(String start_end) {
        this.start_end = start_end;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getStart_end() {
        return start_end;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return getImg() + "\t"+ getTime() + "\t"+ getPrice();
    }
}
