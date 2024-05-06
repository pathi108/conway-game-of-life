package com.bgl.engine.validate;



public class InputValidator {

    public static boolean isPositiveInteger(String value){
        return value.matches("\\d+") && !value.startsWith("-");
    }


    public static boolean isWithinTheGameLimit(int value){
        return value < 200;
    }

}
