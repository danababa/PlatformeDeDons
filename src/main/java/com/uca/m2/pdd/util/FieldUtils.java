package com.uca.m2.pdd.util;

public class FieldUtils {
    public static final int DEFAULT_STRING_MIN_SIZE = 1;
    public static final int DEFAULT_STRING_MAX_SIZE = 255;
    public static final double DEFAULT_LONGITUDE_MIN_SIZE = -180;
    public static final double DEFAULT_LONGITUDE_MAX_SIZE = 180;
    public static final double DEFAULT_LATITUDE_MIN_SIZE = -90;
    public static final double DEFAULT_LATITUDE_MAX_SIZE = 90;
    public static final String REGEX_PHONE_NUMBER_VALIDATION = "^[0-9-+]*$";
    public static final String ERROR_MESSAGE_PHONE_NUMBER_VALIDATION = "Invalid phone number";
    public static final String REGEX_PASSWORD_VALIDATION = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String ERROR_MESSAGE_PASSWORD_VALIDATION = "Password must contain at least 8 characters, one uppercase, one lowercase, one number and one special character";
    public static final int DEFAULT_LIST_MIN_SIZE = 1;
    public static final int DEFAULT_LIST_MAX_SIZE = 10;
    public static final String REGEX_DATE_VALIDATION = "^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[0-1])/\\d{4}$";
    public static final String ERROR_MESSAGE_DATE_VALIDATION = "Dqte must be in the format MM/dd/yyyy";
}
