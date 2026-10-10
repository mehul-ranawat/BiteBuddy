package com.mehul.recipeapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

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

    public RecipeRequest() {
    }

    public RecipeRequest(String title, String description, String ingredients, String instructions,
                         String imageUrl, Integer prepTime, Integer cookTime, Integer servings,
                         String difficulty, Long categoryId) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.difficulty = difficulty;
        this.categoryId = categoryId;
    }

    public static RecipeRequestBuilder builder() {
        return new RecipeRequestBuilder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public static class RecipeRequestBuilder {
        private String title;
        private String description;
        private String ingredients;
        private String instructions;
        private String imageUrl;
        private Integer prepTime;
        private Integer cookTime;
        private Integer servings;
        private String difficulty;
        private Long categoryId;

        RecipeRequestBuilder() {
        }

        public RecipeRequestBuilder title(String title) {
            this.title = title;
            return this;
        }

        public RecipeRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RecipeRequestBuilder ingredients(String ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public RecipeRequestBuilder instructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public RecipeRequestBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public RecipeRequestBuilder prepTime(Integer prepTime) {
            this.prepTime = prepTime;
            return this;
        }

        public RecipeRequestBuilder cookTime(Integer cookTime) {
            this.cookTime = cookTime;
            return this;
        }

        public RecipeRequestBuilder servings(Integer servings) {
            this.servings = servings;
            return this;
        }

        public RecipeRequestBuilder difficulty(String difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public RecipeRequestBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public RecipeRequest build() {
            return new RecipeRequest(this.title, this.description, this.ingredients, this.instructions,
                    this.imageUrl, this.prepTime, this.cookTime, this.servings, this.difficulty, this.categoryId);
        }
    }
}