package com.example.demo.enums;

public enum Category {
    TEMPERATURE,
    LENGTH,
    WEIGHT,
    TIME;

    public static boolean isValid(String category) {
        try {
            Category.valueOf(category.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

