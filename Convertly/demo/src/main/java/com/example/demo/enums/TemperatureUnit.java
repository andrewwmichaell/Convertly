package com.example.demo.enums;

public enum TemperatureUnit {
    CELSIUS("°C"),
    FAHRENHEIT("°F"),
    KELVIN("K");

    private final String symbol;

    TemperatureUnit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static boolean isValid(String unit) {
        try {
            valueOf(unit.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

