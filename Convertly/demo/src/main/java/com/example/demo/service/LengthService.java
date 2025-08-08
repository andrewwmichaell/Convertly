package com.example.demo.service;

import com.example.demo.model.ConversionResponse;
import com.example.demo.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

@Service
public class LengthService {
    
    public ConversionResponse convert(String from, String to, double value) {
        double result;
        String formula;

        // Convert to meters first
        double meters = switch (from.toLowerCase()) {
            case "meter" -> value;
            case "kilometer" -> value * 1000;
            case "mile" -> value * 1609.34;
            case "inch" -> value * 0.0254;
            case "foot" -> value * 0.3048;
            default -> throw new InvalidUnitException("Invalid source unit for length");
        };

        // Convert from meters to target unit
        result = switch (to.toLowerCase()) {
            case "meter" -> meters;
            case "kilometer" -> meters / 1000;
            case "mile" -> meters / 1609.34;
            case "inch" -> meters / 0.0254;
            case "foot" -> meters / 0.3048;
            default -> throw new InvalidUnitException("Invalid target unit for length");
        };

        formula = String.format("%.2f %s = %.2f %s", 
            value, from.toLowerCase(), result, to.toLowerCase());

        return new ConversionResponse(
            result, formula, "success",
            value, "length", from, to
        );
    }
}