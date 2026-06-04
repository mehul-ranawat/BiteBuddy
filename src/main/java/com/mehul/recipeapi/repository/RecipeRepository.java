package com.mehul.recipeapi.repository;

import com.mehul.recipeapi.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // Find all recipes by a specific user
    List<Recipe> findByUserId(Long userId);

    // Find all recipes in a category
    List<Recipe> findByCategoryId(Long categoryId);

    // Search recipes by title (case-insensitive)
    List<Recipe> findByTitleContainingIgnoreCase(String keyword);

    // Search by title OR description
    @Query("SELECT r FROM Recipe r WHERE " +
            "LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Recipe> searchRecipes(@Param("keyword") String keyword);

    // Find by category slug
    @Query("SELECT r FROM Recipe r WHERE r.category.slug = :slug")
    List<Recipe> findByCategorySlug(@Param("slug") String slug);
}