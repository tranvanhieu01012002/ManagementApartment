package com.app.core;

import java.util.regex.Pattern;

public class RegularEx {
    public boolean checkNumber(String nString){
        boolean isMatch = Pattern.compile("^\\d+$")
                .matcher(nString)
                .find();
        return  isMatch;
    }
}
