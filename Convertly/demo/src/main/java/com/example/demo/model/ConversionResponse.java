package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Unit conversion response")
public class ConversionResponse {
    @Schema(description = "Converted value", example = "77.0")
    private double result;

    @Schema(description = "Original input value", example = "25.0")
    private double originalValue;

    @Schema(description = "Original category", example = "temperature")
    private String category;

    @Schema(description = "Original from unit", example = "celsius")
    private String fromUnit;

    @Schema(description = "Original to unit", example = "fahrenheit")
    private String toUnit;

    @Schema(description = "Conversion formula used", example = "(25°C × 9/5) + 32 = 77°F")
    private String formula;

    @Schema(description = "Status message", example = "success")
    private String message;

    public ConversionResponse(double result, String formula, String message, 
                            double originalValue, String category, 
                            String fromUnit, String toUnit) {
        this.result = result;
        this.formula = formula;
        this.message = message;
        this.originalValue = originalValue;
        this.category = category;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    public double getResult() { return result; }
    public String getFormula() { return formula; }
    public String getMessage() { return message; }
    public double getOriginalValue() { return originalValue; }
    public String getCategory() { return category; }
    public String getFromUnit() { return fromUnit; }
    public String getToUnit() { return toUnit; }
}