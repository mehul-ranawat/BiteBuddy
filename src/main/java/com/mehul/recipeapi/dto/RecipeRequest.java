package com.mehul.recipeapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest {

    @NotBlank(message = "Title is required")
    @Schema(example = "Classic Creamy Carbonara")
    private String title;

    @Schema(example = "Traditional Italian pasta with eggs, pecorino cheese, and crispy guanciale.")
    private String description;

    @NotBlank(message = "Ingredients are required")
    @Schema(example = "400g Spaghetti, 200g Guanciale, 4 Egg Yolks, 100g Pecorino Romano, Fresh Black Pepper")
    private String ingredients;

    @NotBlank(message = "Instructions are required")
    @Schema(example = "1. Boil salted water and cook spaghetti al dente.\n2. Fry guanciale until golden.\n3. Whisk yolks and pecorino.\n4. Toss pasta with guanciale and yolk mixture off heat.")
    private String instructions;

    @Schema(example = "https://images.unsplash.com/photo-1612874742237-6526221588e3?auto=format&fit=crop&w=800&q=80")
    private String imageUrl;

    @Positive(message = "Prep time must be positive")
    @Schema(example = "15")
    private Integer prepTime;

    @Positive(message = "Cook time must be positive")
    @Schema(example = "20")
    private Integer cookTime;

    @Positive(message = "Servings must be positive")
    @Schema(example = "4")
    private Integer servings;

    @Schema(example = "MEDIUM", allowableValues = {"EASY", "MEDIUM", "HARD"})
    private String difficulty;

    @Schema(example = "1")
    private Long categoryId;
}