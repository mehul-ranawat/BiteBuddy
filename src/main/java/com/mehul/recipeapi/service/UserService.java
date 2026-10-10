package com.mehul.recipeapi.service;

import com.mehul.recipeapi.dto.RecipeResponse;
import com.mehul.recipeapi.dto.UserProfileResponse;
import com.mehul.recipeapi.entity.User;
import com.mehul.recipeapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RecipeService recipeService;

    public UserService(UserRepository userRepository, RecipeService recipeService) {
        this.userRepository = userRepository;
        this.recipeService = recipeService;
    }

    public UserProfileResponse getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<RecipeResponse> recipes = recipeService.getMyRecipes(username);

        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .memberSince(user.getCreatedAt())
                .recipeCount(recipes.size())
                .recipes(recipes)
                .build();
    }
}
