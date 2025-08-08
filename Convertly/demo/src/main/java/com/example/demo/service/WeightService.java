package com.example.demo.service;

import com.example.demo.model.ConversionResponse;
import com.example.demo.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

@Service
public class WeightService {
    
    public ConversionResponse convert(String from, String to, double value) {
        if (value < 0) {
            throw new InvalidUnitException("Weight cannot be negative");
        }

        double result;
        String formula;
        
        // Convert everything to grams first
        double grams = switch (from.toLowerCase()) {
            case "gram" -> value;
            case "kilogram" -> value * 1000;
            case "pound" -> value * 453.592;
            case "ounce" -> value * 28.3495;
            default -> throw new InvalidUnitException("Invalid source unit for weight");
        };

        // Convert from grams to target unit
        result = switch (to.toLowerCase()) {
            case "gram" -> grams;
            case "kilogram" -> grams / 1000;
            case "pound" -> grams / 453.592;
            case "ounce" -> grams / 28.3495;
            default -> throw new InvalidUnitException("Invalid target unit for weight");
        };

        formula = String.format("%.2f %s = %.2f %s", 
            value, from.toLowerCase(), result, to.toLowerCase());

        return new ConversionResponse(
            result, formula, "success",
            value, "weight", from, to
        );
    }
}

