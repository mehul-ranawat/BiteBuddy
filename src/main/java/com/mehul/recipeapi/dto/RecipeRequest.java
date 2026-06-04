package com.mehul.recipeapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RecipeRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Ingredients are required")
    private String ingredients;

    @NotBlank(message = "Instructions are required")
    private String instructions;

    private String imageUrl;

    @Positive(message = "Prep time must be positive")
    private Integer prepTime;

    @Positive(message = "Servings must be positive")
    private Integer servings;

    private Long categoryId;
}