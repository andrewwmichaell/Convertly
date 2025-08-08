package com.example.demo.enums;

public enum Unit {
    // Temperature units
    CELSIUS(Category.TEMPERATURE),
    FAHRENHEIT(Category.TEMPERATURE),
    KELVIN(Category.TEMPERATURE),

    // Length units
    METER(Category.LENGTH),
    KILOMETER(Category.LENGTH),
    MILE(Category.LENGTH),
    INCH(Category.LENGTH),
    FOOT(Category.LENGTH),

    // Weight units
    GRAM(Category.WEIGHT),
    KILOGRAM(Category.WEIGHT),
    POUND(Category.WEIGHT),
    OUNCE(Category.WEIGHT),

    // Time units
    SECONDS(Category.TIME),
    MINUTES(Category.TIME),
    HOURS(Category.TIME),
    DAYS(Category.TIME);

    private final Category category;

    Unit(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public static boolean isValidForCategory(String unit, String category) {
        try {
            Unit unitEnum = Unit.valueOf(unit.toUpperCase());
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            return unitEnum.getCategory() == categoryEnum;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

