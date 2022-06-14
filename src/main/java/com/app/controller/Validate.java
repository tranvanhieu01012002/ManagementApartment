package com.app.controller;

import com.app.model.Account;

import java.util.List;

public class Validate {
    public boolean checkLogin(List<Account> arr, String user, String pass){
        for (Account i: arr) {
            if(i.getUsername().equals(user) && i.getPassword().equals(pass) ){
                return  true;
            }
        }
        return false;
    }
}
