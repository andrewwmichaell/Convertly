package com.example.demo.service;

import com.example.demo.model.ConversionResponse;
import com.example.demo.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

@Service
public class TimeService {
    
    public ConversionResponse convert(String from, String to, double value) {
        if (value < 0) {
            throw new InvalidUnitException("Time cannot be negative");
        }

        double result;
        String formula;
        
        // Convert everything to seconds first
        double seconds = switch (from.toLowerCase()) {
            case "seconds" -> value;
            case "minutes" -> value * 60;
            case "hours" -> value * 3600;
            case "days" -> value * 86400;
            default -> throw new InvalidUnitException("Invalid source unit for time");
        };

        // Convert from seconds to target unit
        result = switch (to.toLowerCase()) {
            case "seconds" -> seconds;
            case "minutes" -> seconds / 60;
            case "hours" -> seconds / 3600;
            case "days" -> seconds / 86400;
            default -> throw new InvalidUnitException("Invalid target unit for time");
        };

        formula = String.format("%.2f %s = %.2f %s", 
            value, from.toLowerCase(), result, to.toLowerCase());

        return new ConversionResponse(
            result, formula, "success",
            value, "time", from, to
        );
    }
}

