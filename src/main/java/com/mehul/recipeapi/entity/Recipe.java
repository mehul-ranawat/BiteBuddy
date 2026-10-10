package com.mehul.recipeapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String instructions;

    private String imageUrl;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    @Column(nullable = false)
    private String difficulty = "MEDIUM";

    @Column(nullable = false)
    private Integer likeCount = 0;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Recipe() {
    }

    public Recipe(Long id, String title, String description, String ingredients, String instructions,
                  String imageUrl, Integer prepTime, Integer cookTime, Integer servings,
                  String difficulty, Integer likeCount, LocalDateTime createdAt, LocalDateTime updatedAt,
                  User user, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.difficulty = (difficulty != null) ? difficulty : "MEDIUM";
        this.likeCount = (likeCount != null) ? likeCount : 0;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.category = category;
    }

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static class RecipeBuilder {
        private Long id;
        private String title;
        private String description;
        private String ingredients;
        private String instructions;
        private String imageUrl;
        private Integer prepTime;
        private Integer cookTime;
        private Integer servings;
        private String difficulty = "MEDIUM";
        private Integer likeCount = 0;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User user;
        private Category category;

        RecipeBuilder() {
        }

        public RecipeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RecipeBuilder title(String title) {
            this.title = title;
            return this;
        }

        public RecipeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RecipeBuilder ingredients(String ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public RecipeBuilder instructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public RecipeBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public RecipeBuilder prepTime(Integer prepTime) {
            this.prepTime = prepTime;
            return this;
        }

        public RecipeBuilder cookTime(Integer cookTime) {
            this.cookTime = cookTime;
            return this;
        }

        public RecipeBuilder servings(Integer servings) {
            this.servings = servings;
            return this;
        }

        public RecipeBuilder difficulty(String difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public RecipeBuilder likeCount(Integer likeCount) {
            this.likeCount = likeCount;
            return this;
        }

        public RecipeBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RecipeBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RecipeBuilder user(User user) {
            this.user = user;
            return this;
        }

        public RecipeBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public Recipe build() {
            return new Recipe(this.id, this.title, this.description, this.ingredients, this.instructions,
                    this.imageUrl, this.prepTime, this.cookTime, this.servings,
                    this.difficulty, this.likeCount, this.createdAt, this.updatedAt,
                    this.user, this.category);
        }
    }
}