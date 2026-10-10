package com.mehul.recipeapi.dto;

import java.time.LocalDateTime;

public class RecipeResponse {
    private Long id;
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private String imageUrl;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String difficulty;
    private Integer likeCount;
    private Long categoryId;
    private String categoryName;
    private String categorySlug;
    private String authorUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RecipeResponse() {
    }

    public RecipeResponse(Long id, String title, String description, String ingredients, String instructions,
                          String imageUrl, Integer prepTime, Integer cookTime, Integer servings,
                          String difficulty, Integer likeCount, Long categoryId, String categoryName,
                          String categorySlug, String authorUsername, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.difficulty = difficulty;
        this.likeCount = likeCount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorySlug = categorySlug;
        this.authorUsername = authorUsername;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static RecipeResponseBuilder builder() {
        return new RecipeResponseBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class RecipeResponseBuilder {
        private Long id;
        private String title;
        private String description;
        private String ingredients;
        private String instructions;
        private String imageUrl;
        private Integer prepTime;
        private Integer cookTime;
        private Integer servings;
        private String difficulty;
        private Integer likeCount;
        private Long categoryId;
        private String categoryName;
        private String categorySlug;
        private String authorUsername;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        RecipeResponseBuilder() {
        }

        public RecipeResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RecipeResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public RecipeResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RecipeResponseBuilder ingredients(String ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public RecipeResponseBuilder instructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public RecipeResponseBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public RecipeResponseBuilder prepTime(Integer prepTime) {
            this.prepTime = prepTime;
            return this;
        }

        public RecipeResponseBuilder cookTime(Integer cookTime) {
            this.cookTime = cookTime;
            return this;
        }

        public RecipeResponseBuilder servings(Integer servings) {
            this.servings = servings;
            return this;
        }

        public RecipeResponseBuilder difficulty(String difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public RecipeResponseBuilder likeCount(Integer likeCount) {
            this.likeCount = likeCount;
            return this;
        }

        public RecipeResponseBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public RecipeResponseBuilder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public RecipeResponseBuilder categorySlug(String categorySlug) {
            this.categorySlug = categorySlug;
            return this;
        }

        public RecipeResponseBuilder authorUsername(String authorUsername) {
            this.authorUsername = authorUsername;
            return this;
        }

        public RecipeResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RecipeResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RecipeResponse build() {
            return new RecipeResponse(this.id, this.title, this.description, this.ingredients, this.instructions,
                    this.imageUrl, this.prepTime, this.cookTime, this.servings, this.difficulty,
                    this.likeCount, this.categoryId, this.categoryName, this.categorySlug,
                    this.authorUsername, this.createdAt, this.updatedAt);
        }
    }
}