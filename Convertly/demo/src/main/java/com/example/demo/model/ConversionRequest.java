package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Unit conversion request")
public class ConversionRequest {
    @Schema(description = "Conversion category (e.g., temperature, length, weight, time)", example = "temperature")
    private String category;
    
    @Schema(description = "Value to convert", example = "32.0")
    private double value;
    
    @Schema(description = "Source unit", example = "fahrenheit")
    private String fromUnit;
    
    @Schema(description = "Target unit", example = "celsius")
    private String toUnit;

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    
    public String getFromUnit() { return fromUnit; }
    public void setFromUnit(String fromUnit) { this.fromUnit = fromUnit; }
    
    public String getToUnit() { return toUnit; }
    public void setToUnit(String toUnit) { this.toUnit = toUnit; }
}