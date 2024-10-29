package com.uca.m2.pdd.util;

public class FieldUtils {
    public static final int DEFAULT_STRING_MIN_SIZE = 1;
    public static final int DEFAULT_STRING_MAX_SIZE = 50;
    public static final int DEFAULT_LONGITUDE_MIN_SIZE = -180;
    public static final int DEFAULT_LONGITUDE_MAX_SIZE = 180;
    public static final int DEFAULT_LATITUDE_MIN_SIZE = -90;
    public static final int DEFAULT_LATITUDE_MAX_SIZE = 90;
    public static final String REGEX_PHONE_NUMBER_VALIDATION = "^[0-9-+]*$";
    public static final String ERROR_MESSAGE_PHONE_NUMBER_VALIDATION = "Invalid phone number";
    public static final String REGEx_PASSWORD_VALIDATION = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String ERROR_MESSAGE_PASSWORD_VALIDATION = "Password must contain at least 8 characters, one uppercase, one lowercase, one number and one special character";
}
