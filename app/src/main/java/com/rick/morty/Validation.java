package com.rick.morty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String USERNAME_REGEX_PATTERN="^[a-zA-Z]{3,20}$";
    public static final String PASSWORD_REGEX_PATTERN="^[a-zA-Z]{3,20}$";
    public static final String PASSWORD_NUMBER_PATTERN="^[0-9]{1,8}$";

    public static boolean isValidUsername(String username){
        Pattern pattern=Pattern.compile(USERNAME_REGEX_PATTERN);
        Matcher matcher=pattern.matcher(username);
        return matcher.find();
    }

    public static boolean isValidPassword(String password){
        Pattern pattern=Pattern.compile(PASSWORD_REGEX_PATTERN);
        Matcher matcher=pattern.matcher(password);
        return matcher.find();
    }

    public static boolean isValidNumber(String number){
        Pattern pattern=Pattern.compile(PASSWORD_NUMBER_PATTERN);
        Matcher matcher=pattern.matcher(number);

        return matcher.find();
    }

}
