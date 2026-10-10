package com.mehul.recipeapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UserProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime memberSince;
    private int recipeCount;
    private List<RecipeResponse> recipes;

    public UserProfileResponse() {
    }

    public UserProfileResponse(Long id, String username, String email, String role,
                               LocalDateTime memberSince, int recipeCount, List<RecipeResponse> recipes) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.memberSince = memberSince;
        this.recipeCount = recipeCount;
        this.recipes = recipes;
    }

    public static UserProfileResponseBuilder builder() {
        return new UserProfileResponseBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDateTime memberSince) {
        this.memberSince = memberSince;
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public void setRecipeCount(int recipeCount) {
        this.recipeCount = recipeCount;
    }

    public List<RecipeResponse> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeResponse> recipes) {
        this.recipes = recipes;
    }

    public static class UserProfileResponseBuilder {
        private Long id;
        private String username;
        private String email;
        private String role;
        private LocalDateTime memberSince;
        private int recipeCount;
        private List<RecipeResponse> recipes;

        UserProfileResponseBuilder() {
        }

        public UserProfileResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserProfileResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserProfileResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserProfileResponseBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserProfileResponseBuilder memberSince(LocalDateTime memberSince) {
            this.memberSince = memberSince;
            return this;
        }

        public UserProfileResponseBuilder recipeCount(int recipeCount) {
            this.recipeCount = recipeCount;
            return this;
        }

        public UserProfileResponseBuilder recipes(List<RecipeResponse> recipes) {
            this.recipes = recipes;
            return this;
        }

        public UserProfileResponse build() {
            return new UserProfileResponse(this.id, this.username, this.email, this.role,
                    this.memberSince, this.recipeCount, this.recipes);
        }
    }
}
