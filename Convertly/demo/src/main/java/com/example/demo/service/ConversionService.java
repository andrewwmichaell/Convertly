package com.example.demo.service;

import com.example.demo.model.ConversionResponse;
import com.example.demo.exception.InvalidUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {
    
    @Autowired
    private TemperatureService temperatureService;
    
    @Autowired
    private LengthService lengthService;
    
    @Autowired
    private WeightService weightService;
    
    @Autowired
    private TimeService timeService;

    public ConversionResponse convert(String category, String fromUnit, String toUnit, double value) {
        try {
            return switch (category.toLowerCase()) {
                case "temperature" -> temperatureService.convert(fromUnit, toUnit, value);
                case "length" -> lengthService.convert(fromUnit, toUnit, value);
                case "weight" -> weightService.convert(fromUnit, toUnit, value);
                case "time" -> timeService.convert(fromUnit, toUnit, value);
                default -> throw new InvalidUnitException("Unsupported category: " + category);
            };
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException(e.getMessage());
        }
    }
}