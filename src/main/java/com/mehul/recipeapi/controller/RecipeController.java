package com.mehul.recipeapi.controller;

import com.mehul.recipeapi.dto.RecipeRequest;
import com.mehul.recipeapi.dto.RecipeResponse;
import com.mehul.recipeapi.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    // GET /api/recipes — get all recipes
    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    // GET /api/recipes/{id} — get single recipe
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    // GET /api/recipes/search?q=pasta — search by keyword
    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponse>> searchRecipes(@RequestParam String q) {
        return ResponseEntity.ok(recipeService.searchRecipes(q));
    }

    // GET /api/recipes/category/vegan — filter by category slug
    @GetMapping("/category/{slug}")
    public ResponseEntity<List<RecipeResponse>> getByCategory(@PathVariable String slug) {
        return ResponseEntity.ok(recipeService.getRecipesByCategory(slug));
    }

    // GET /api/recipes/my — get logged-in user's recipes
    @GetMapping("/my")
    public ResponseEntity<List<RecipeResponse>> getMyRecipes(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(recipeService.getMyRecipes(userDetails.getUsername()));
    }

    // POST /api/recipes — create a new recipe (auth required)
    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(
            @Valid @RequestBody RecipeRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(recipeService.createRecipe(request, userDetails.getUsername()));
    }

    // PUT /api/recipes/{id} — update a recipe (owner only)
    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(
            @PathVariable Long id,
            @Valid @RequestBody RecipeRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, request, userDetails.getUsername()));
    }

    // DELETE /api/recipes/{id} — delete a recipe (owner only)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        recipeService.deleteRecipe(id, userDetails.getUsername());
        return ResponseEntity.ok("Recipe deleted successfully");
    }
}