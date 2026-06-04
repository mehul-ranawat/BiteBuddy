package com.mehul.recipeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponse {
    private Long id;
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private String imageUrl;
    private Integer prepTime;
    private Integer servings;
    private String categoryName;
    private String authorUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}