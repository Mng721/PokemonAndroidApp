package com.example.mypokemonapplication.Utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BanNT
 */

public class ValidatorUtil {
    public static boolean emptyValue(String str) {
        if (null == str || str.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean weakPassword(String str){
        if (str.length() < 8){
            return true;
        }
        return false;
    }

    public static boolean isEmail(String Value) {
        Pattern pattern = Pattern.compile(
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }

}
