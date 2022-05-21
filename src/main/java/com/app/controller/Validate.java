package com.app.controller;

import com.app.model.Account;

import java.util.List;

public class Validate {
    public boolean checkLogin(List<Account> arr, String user, String pass){
        for (Account i: arr) {
            if(i.user.equals(user) && i.password.equals(pass) ){
                return  true;
            }
        }
        return false;
    }
}
