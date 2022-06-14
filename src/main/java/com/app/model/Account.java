package com.app.model;

public class Account {
    private String username ;
    private String password ;
    private  String fullname;

    public Account (String user,String pass){
        this.username = user;
        this.password = pass;
    }
    public Account (String username, String pass, String fullname){
        this.username = username;
        this.password = pass;
        this.fullname = fullname;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
