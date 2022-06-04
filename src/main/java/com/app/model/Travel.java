package com.app.model;

public class Travel {
    private String name;
    private  String time;
    private  String start_end;
    private int price;
    private int id;
    private String img;
    public Travel(){

    }
    public Travel(String name, String time, String start_end, int price, String img, int id){
        this.id = id;
        this.setName(name);
        this.setTime(time);
        this.setStart_end(start_end);
        this.setPrice(price);
        this.setImg(img);
    }
    // getter setter for class
    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
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

    public int getPrice() {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName()+"\n"+getTime()+"\n"+getPrice()+"\n"+getStart_end();
    }
}
