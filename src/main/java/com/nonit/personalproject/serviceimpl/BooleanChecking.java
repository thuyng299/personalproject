package com.nonit.personalproject.serviceimpl;

public class BooleanChecking {
    public static boolean isAlpha(String input) {
        String pattern = "^[a-zA-Z]+$";
        return input.matches(pattern);
    }
    public static boolean isAlphanumeric(String input) {
        String pattern = "^[a-zA-Z0-9]+$";
        return input.matches(pattern);
    }
    public static boolean isLowerCaseAlphanumeric(String input) {
        String pattern = "^[a-z0-9\\S\\.]+$";
        return input.matches(pattern);
    }
    public static boolean isAlphanumericAndNoWhiteSpace(String input) {
        String pattern = "^[a-zA-Z0-9\\S\\.]+$";
        return input.matches(pattern);
    }
    public static boolean isAlphaOrNumberic(String input){
        String pattern = "^(?:[a-zA-Z]+|[0-9]+)$";
        return input.matches(pattern);
    }
    public static boolean isAlphaOrNumberOrSpecial(String input){
        String pattern = "^[a-zA-Z0-9!@#$%^&*()_+=\\-\\[\\]{}|\\\\:;\"'<>,.?/]+$";
        return input.matches(pattern);
    }
    public static boolean isNumbericOrSpecial(String input){
        String pattern = "[\\d\\p{Punct}]+";
        return input.matches(pattern);
    }
    public static boolean isAlphanumericWithSpecial(String input) {
        String pattern = "^[a-zA-Z0-9\\p{Punct}]{6,}$";
        return input.matches(pattern);
    }
    public static boolean isNumberOnly(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public static boolean isPhoneFormat(String input){
        String pattern = "^[0-9]{3,}-[0-9]{4,}-[0-9]{2,}$";
        return input.matches(pattern);
    }
}
