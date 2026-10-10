package com.mehul.recipeapi.controller;

import com.mehul.recipeapi.dto.RecipeRequest;
import com.mehul.recipeapi.dto.RecipeResponse;
import com.mehul.recipeapi.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@Tag(name = "Recipes", description = "Recipe publishing, discovery, and community engagement")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @Operation(summary = "Get all recipes", description = "Retrieve a list of all published recipes across the platform")
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/trending")
    @Operation(summary = "Get trending recipes", description = "Retrieve top 10 recipes sorted by community likes")
    public ResponseEntity<List<RecipeResponse>> getTrendingRecipes() {
        return ResponseEntity.ok(recipeService.getTrendingRecipes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by ID", description = "Fetch detailed information for a single recipe including ingredients and instructions")
    public ResponseEntity<RecipeResponse> getRecipeById(
            @Parameter(description = "ID of the recipe to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search recipes by keyword", description = "Search recipes by matching terms in title, description, or ingredients")
    public ResponseEntity<List<RecipeResponse>> searchRecipes(
            @Parameter(description = "Keyword to search for (e.g., pasta, chicken, chocolate)") @RequestParam String q) {
        return ResponseEntity.ok(recipeService.searchRecipes(q));
    }

    @GetMapping("/category/{slug}")
    @Operation(summary = "Filter recipes by category slug", description = "Retrieve all recipes organized under a specific category (e.g., italian, desserts)")
    public ResponseEntity<List<RecipeResponse>> getByCategory(
            @Parameter(description = "Category slug name") @PathVariable String slug) {
        return ResponseEntity.ok(recipeService.getRecipesByCategory(slug));
    }

    @GetMapping("/my")
    @Operation(summary = "Get current user recipes", description = "Fetch all recipes created by the currently authenticated user", security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<List<RecipeResponse>> getMyRecipes(
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(recipeService.getMyRecipes(userDetails.getUsername()));
    }

    @PostMapping
    @Operation(summary = "Create a new recipe", description = "Publish a new recipe with ingredients, steps, category, and cooking times", security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<RecipeResponse> createRecipe(
            @Valid @RequestBody RecipeRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(recipeService.createRecipe(request, userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a recipe", description = "Update an existing recipe (Owner authorization required)", security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<RecipeResponse> updateRecipe(
            @Parameter(description = "ID of the recipe to update") @PathVariable Long id,
            @Valid @RequestBody RecipeRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(recipeService.updateRecipe(id, request, userDetails.getUsername()));
    }

    @PostMapping("/{id}/like")
    @Operation(summary = "Like a recipe", description = "Increment the like count for a recipe (Community engagement)")
    public ResponseEntity<RecipeResponse> likeRecipe(
            @Parameter(description = "ID of the recipe to like") @PathVariable Long id) {
        return ResponseEntity.ok(recipeService.likeRecipe(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a recipe", description = "Permanently delete a recipe (Owner authorization required)", security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<String> deleteRecipe(
            @Parameter(description = "ID of the recipe to delete") @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        recipeService.deleteRecipe(id, userDetails.getUsername());
        return ResponseEntity.ok("Recipe deleted successfully");
    }
}