package com.mehul.recipeapi.service;

import com.mehul.recipeapi.dto.RecipeRequest;
import com.mehul.recipeapi.dto.RecipeResponse;
import com.mehul.recipeapi.entity.Category;
import com.mehul.recipeapi.entity.Recipe;
import com.mehul.recipeapi.entity.User;
import com.mehul.recipeapi.repository.CategoryRepository;
import com.mehul.recipeapi.repository.RecipeRepository;
import com.mehul.recipeapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // Get all recipes
    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get trending recipes
    public List<RecipeResponse> getTrendingRecipes() {
        return recipeRepository.findTop10ByOrderByLikeCountDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get single recipe by ID
    public RecipeResponse getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
        return mapToResponse(recipe);
    }

    // Search recipes by keyword
    public List<RecipeResponse> searchRecipes(String keyword) {
        return recipeRepository.searchRecipes(keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Filter recipes by category slug
    public List<RecipeResponse> getRecipesByCategory(String slug) {
        return recipeRepository.findByCategorySlug(slug)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get recipes by logged-in user
    public List<RecipeResponse> getMyRecipes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return recipeRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Create a new recipe
    @Transactional
    public RecipeResponse createRecipe(RecipeRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));
        }

        Recipe recipe = Recipe.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .ingredients(request.getIngredients())
                .instructions(request.getInstructions())
                .imageUrl(request.getImageUrl())
                .prepTime(request.getPrepTime())
                .cookTime(request.getCookTime())
                .servings(request.getServings())
                .difficulty(request.getDifficulty() != null ? request.getDifficulty().toUpperCase() : "MEDIUM")
                .likeCount(0)
                .user(user)
                .category(category)
                .build();

        return mapToResponse(recipeRepository.save(recipe));
    }

    // Update an existing recipe
    @Transactional
    public RecipeResponse updateRecipe(Long id, RecipeRequest request, String username) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));

        // Only the owner can update
        if (!recipe.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to update this recipe");
        }

        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));
        }

        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setIngredients(request.getIngredients());
        recipe.setInstructions(request.getInstructions());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setPrepTime(request.getPrepTime());
        if (request.getCookTime() != null) {
            recipe.setCookTime(request.getCookTime());
        }
        recipe.setServings(request.getServings());
        if (request.getDifficulty() != null) {
            recipe.setDifficulty(request.getDifficulty().toUpperCase());
        }
        recipe.setCategory(category);

        return mapToResponse(recipeRepository.save(recipe));
    }

    // Like / favorite a recipe (Community engagement)
    @Transactional
    public RecipeResponse likeRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
        recipe.setLikeCount((recipe.getLikeCount() == null ? 0 : recipe.getLikeCount()) + 1);
        return mapToResponse(recipeRepository.save(recipe));
    }

    // Delete a recipe
    @Transactional
    public void deleteRecipe(Long id, String username) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));

        // Only the owner can delete
        if (!recipe.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to delete this recipe");
        }

        recipeRepository.deleteById(id);
    }

    // Map Recipe entity to RecipeResponse DTO
    public RecipeResponse mapToResponse(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .ingredients(recipe.getIngredients())
                .instructions(recipe.getInstructions())
                .imageUrl(recipe.getImageUrl())
                .prepTime(recipe.getPrepTime())
                .cookTime(recipe.getCookTime())
                .servings(recipe.getServings())
                .difficulty(recipe.getDifficulty() != null ? recipe.getDifficulty() : "MEDIUM")
                .likeCount(recipe.getLikeCount() != null ? recipe.getLikeCount() : 0)
                .categoryId(recipe.getCategory() != null ? recipe.getCategory().getId() : null)
                .categoryName(recipe.getCategory() != null ? recipe.getCategory().getName() : null)
                .categorySlug(recipe.getCategory() != null ? recipe.getCategory().getSlug() : null)
                .authorUsername(recipe.getUser() != null ? recipe.getUser().getUsername() : null)
                .createdAt(recipe.getCreatedAt())
                .updatedAt(recipe.getUpdatedAt())
                .build();
    }
}