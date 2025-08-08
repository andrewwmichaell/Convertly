package com.example.demo.service;

import com.example.demo.model.ConversionResponse;
import com.example.demo.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
    
    public ConversionResponse convert(String from, String to, double value) {
        double result;
        String formula;

        switch (from.toLowerCase()) {
            case "celsius":
                if (to.equalsIgnoreCase("fahrenheit")) {
                    result = (value * 9/5) + 32;
                    formula = String.format("(%.2f°C × 9/5) + 32 = %.2f°F", value, result);
                } else if (to.equalsIgnoreCase("kelvin")) {
                    result = value + 273.15;
                    formula = String.format("%.2f°C + 273.15 = %.2fK", value, result);
                } else {
                    throw new InvalidUnitException("Invalid target unit for Celsius");
                }
                break;
            case "fahrenheit":
                if (to.equalsIgnoreCase("celsius")) {
                    result = (value - 32) * 5/9;
                    formula = String.format("(%.2f°F - 32) × 5/9 = %.2f°C", value, result);
                } else if (to.equalsIgnoreCase("kelvin")) {
                    result = (value - 32) * 5/9 + 273.15;
                    formula = String.format("(%.2f°F - 32) × 5/9 + 273.15 = %.2fK", value, result);
                } else {
                    throw new InvalidUnitException("Invalid target unit for Fahrenheit");
                }
                break;
            case "kelvin":
                if (to.equalsIgnoreCase("celsius")) {
                    result = value - 273.15;
                    formula = String.format("%.2fK - 273.15 = %.2f°C", value, result);
                } else if (to.equalsIgnoreCase("fahrenheit")) {
                    result = (value - 273.15) * 9/5 + 32;
                    formula = String.format("(%.2fK - 273.15) × 9/5 + 32 = %.2f°F", value, result);
                } else {
                    throw new InvalidUnitException("Invalid target unit for Kelvin");
                }
                break;
            default:
                throw new InvalidUnitException("Invalid source unit for temperature");
        }
        
        return new ConversionResponse(
            result, formula, "success",
            value, "temperature", from, to
        );
    }
}