package com.example.demo.controller;

import com.example.demo.model.ConversionRequest;
import com.example.demo.model.ConversionResponse;
import com.example.demo.service.ConversionService;
import com.example.demo.enums.Category;
import com.example.demo.enums.Unit;
import com.example.demo.exception.InvalidUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

@RestController
@Tag(name = "Unit Converter", description = "API for converting units across different measurement categories")
public class ConverterController {

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/convert")
    @Operation(summary = "Convert units", description = "Converts a value from one unit to another within the same category")
    @ApiResponse(responseCode = "200", description = "Successful conversion",
            content = @Content(schema = @Schema(implementation = ConversionResponse.class)))
    public ConversionResponse convert(@RequestBody ConversionRequest request) {
        if (!Category.isValid(request.getCategory())) {
            throw new InvalidUnitException("Invalid category: " + request.getCategory());
        }
        
        if (!Unit.isValidForCategory(request.getFromUnit(), request.getCategory()) ||
            !Unit.isValidForCategory(request.getToUnit(), request.getCategory())) {
            throw new InvalidUnitException("Invalid units for category: " + request.getCategory());
        }

        return conversionService.convert(
            request.getCategory(),
            request.getFromUnit(),
            request.getToUnit(),
            request.getValue()
        );
    }

    @GetMapping("/categories")
    @Operation(summary = "Get categories", description = "Returns all available unit categories")
    public List<String> getCategories() {
        return Arrays.stream(Category.values())
                    .map(c -> c.name().toLowerCase())
                    .collect(Collectors.toList());
    }

    @GetMapping("/units")
    @Operation(
        summary = "Get units for category",
        description = "Returns a list of supported units for a given category (e.g., temperature, length, weight, time)"
    )
    @ApiResponse(
        responseCode = "200",
        description = "List of units retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(example = "[\"celsius\", \"fahrenheit\", \"kelvin\"]")
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid category provided",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(example = "{\"error\": \"Invalid category: xyz\", \"status\": \"error\"}")
        )
    )
    public List<String> getUnits(
        @RequestParam 
        @Schema(description = "Category name", example = "temperature", required = true) 
        String category) {
        try {
            Category cat = Category.valueOf(category.toUpperCase());
            return Arrays.stream(Unit.values())
                        .filter(u -> u.getCategory() == cat)
                        .map(u -> u.name().toLowerCase())
                        .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid category: " + category);
        }
    }

    @GetMapping("/sample-payload")
    @Operation(summary = "Get sample payload", description = "Returns a sample request payload for the /convert endpoint")
    public Map<String, Object> getSamplePayload() {
        Map<String, Object> sample = new HashMap<>();
        sample.put("category", "temperature");
        sample.put("fromUnit", "celsius");
        sample.put("toUnit", "fahrenheit");
        sample.put("value", 25.0);
        return sample;
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Checks if the service is running")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Unit Converter API is up and running");
        return response;
    }
}