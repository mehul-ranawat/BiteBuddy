package com.mehul.recipeapi.config;

import com.mehul.recipeapi.entity.Category;
import com.mehul.recipeapi.entity.Recipe;
import com.mehul.recipeapi.entity.User;
import com.mehul.recipeapi.repository.CategoryRepository;
import com.mehul.recipeapi.repository.RecipeRepository;
import com.mehul.recipeapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CategoryRepository categoryRepository,
                           UserRepository userRepository,
                           RecipeRepository recipeRepository,
                           PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        try {
            seedData();
        } catch (Exception e) {
            log.warn("Sample data initialization skipped or already seeded: {}", e.getMessage());
        }
    }

    private void seedData() {
        // Seed Categories if empty
        if (categoryRepository.count() == 0) {
            log.info("Seeding initial categories...");
            List<Category> categories = List.of(
                    Category.builder().name("Italian").slug("italian").build(),
                    Category.builder().name("Desserts").slug("desserts").build(),
                    Category.builder().name("Quick Meals").slug("quick-meals").build(),
                    Category.builder().name("Vegetarian").slug("vegetarian").build(),
                    Category.builder().name("Breakfast").slug("breakfast").build(),
                    Category.builder().name("Asian Cuisine").slug("asian-cuisine").build()
            );
            categoryRepository.saveAll(categories);
        }

        // Seed Users if empty
        User chefMehul = userRepository.findByUsername("chef_mehul").orElseGet(() -> {
            log.info("Creating default chef user 'chef_mehul'...");
            return userRepository.save(User.builder()
                    .username("chef_mehul")
                    .email("mehul@bitebuddy.com")
                    .password(passwordEncoder.encode("Password123!"))
                    .role("ADMIN")
                    .build());
        });

        User foodieEmma = userRepository.findByUsername("foodie_emma").orElseGet(() -> {
            log.info("Creating default user 'foodie_emma'...");
            return userRepository.save(User.builder()
                    .username("foodie_emma")
                    .email("emma@bitebuddy.com")
                    .password(passwordEncoder.encode("Password123!"))
                    .role("USER")
                    .build());
        });

        // Seed Recipes if empty
        if (recipeRepository.count() == 0) {
            log.info("Seeding featured recipes...");
            Category italian = categoryRepository.findBySlug("italian").orElse(null);
            Category desserts = categoryRepository.findBySlug("desserts").orElse(null);
            Category quickMeals = categoryRepository.findBySlug("quick-meals").orElse(null);
            Category vegetarian = categoryRepository.findBySlug("vegetarian").orElse(null);
            Category breakfast = categoryRepository.findBySlug("breakfast").orElse(null);
            Category asian = categoryRepository.findBySlug("asian-cuisine").orElse(null);

            List<Recipe> recipes = List.of(
                    Recipe.builder()
                            .title("Classic Creamy Carbonara")
                            .description("Traditional Roman pasta made with crispy guanciale, pecorino romano, egg yolks, and cracked black pepper. Rich, savory, and comforting without heavy cream.")
                            .ingredients("• 400g Spaghetti\n• 200g Guanciale or Pancetta (cubed)\n• 4 Fresh Egg Yolks + 1 Whole Egg\n• 100g Pecorino Romano (finely grated)\n• 1 tsp Freshly Cracked Black Pepper\n• Salt to taste")
                            .instructions("1. Bring a large pot of salted water to a gentle boil and cook spaghetti until al dente.\n2. In a skillet, sauté diced guanciale over medium heat until crispy and golden (approx. 6-8 mins). Remove from heat.\n3. In a bowl, whisk together egg yolks, whole egg, grated Pecorino Romano, and generous black pepper.\n4. Reserve 1/2 cup pasta cooking water. Drain pasta and transfer immediately to the pan with rendered guanciale fat.\n5. Let the pan cool slightly for 30 seconds, then pour in egg-cheese mixture, tossing continuously while splashing pasta water to create a silky, glossy sauce.\n6. Serve immediately garnished with extra Pecorino and cracked pepper.")
                            .imageUrl("https://images.unsplash.com/photo-1612874742237-6526221588e3?auto=format&fit=crop&w=1200&q=80")
                            .prepTime(10)
                            .cookTime(15)
                            .servings(4)
                            .difficulty("MEDIUM")
                            .likeCount(42)
                            .category(italian)
                            .user(chefMehul)
                            .build(),

                    Recipe.builder()
                            .title("Avocado Sourdough & Poached Eggs")
                            .description("Artisan toasted sourdough bread topped with creamy mashed Hass avocado, lemon zest, chili flakes, and perfectly runny poached eggs.")
                            .ingredients("• 2 Slices Artisan Sourdough Bread\n• 1 Ripe Hass Avocado\n• 2 Large Free-Range Eggs\n• 1 tbsp Extra Virgin Olive Oil\n• 1/2 Lemon (juiced)\n• 1/2 tsp Red Chili Flakes\n• Flaky Sea Salt & Microgreens")
                            .instructions("1. Toast sourdough slices until golden and crisp.\n2. In a small bowl, coarsely mash the avocado with lemon juice, olive oil, and flaky sea salt.\n3. Bring a small pot of water with 1 tbsp vinegar to a gentle simmer. Swirl water to create a vortex and gently drop in cracked eggs. Poach for 3 minutes.\n4. Spread mashed avocado generously over warm sourdough.\n5. Carefully top with poached eggs, sprinkle red chili flakes and fresh microgreens.\n6. Slice open and enjoy the runny golden yolk!")
                            .imageUrl("https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=1200&q=80")
                            .prepTime(10)
                            .cookTime(5)
                            .servings(2)
                            .difficulty("EASY")
                            .likeCount(35)
                            .category(breakfast)
                            .user(foodieEmma)
                            .build(),

                    Recipe.builder()
                            .title("Velvety Molten Chocolate Lava Cake")
                            .description("Decadent individual chocolate cakes with a luscious, warm, gooey molten center. Perfect for date night or special occasions.")
                            .ingredients("• 150g High-quality Dark Chocolate (70%)\n• 100g Unsalted Butter\n• 2 Whole Eggs + 2 Egg Yolks\n• 50g Granulated Sugar\n• 30g All-purpose Flour\n• Cocoa powder (for dusting ramekins)\n• Vanilla bean ice cream for serving")
                            .instructions("1. Preheat oven to 200°C (400°F). Butter 4 ramekins and dust thoroughly with cocoa powder.\n2. Melt dark chocolate and butter together in a heatproof bowl set over simmering water; stir until smooth.\n3. In a separate bowl, whisk eggs, yolks, and sugar until pale and fluffy.\n4. Gently fold melted chocolate into egg mixture, then sift in flour and fold until just combined.\n5. Divide batter into prepared ramekins. Bake for 10-12 minutes until edges are firm but center is soft.\n6. Invert onto dessert plates and serve immediately with vanilla bean ice cream.")
                            .imageUrl("https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&w=1200&q=80")
                            .prepTime(15)
                            .cookTime(12)
                            .servings(4)
                            .difficulty("MEDIUM")
                            .likeCount(58)
                            .category(desserts)
                            .user(chefMehul)
                            .build(),

                    Recipe.builder()
                            .title("Authentic Butter Chicken (Murgh Makhani)")
                            .description("Tender tandoori spiced chicken pieces simmered in a velvety, buttery tomato gravy infused with aromatic fenugreek and garam masala.")
                            .ingredients("• 600g Boneless Chicken Thighs (cubed)\n• 1/2 cup Greek Yogurt\n• 400g Canned Crushed San Marzano Tomatoes\n• 3 tbsp Butter + 1 tbsp Ghee\n• 1/2 cup Heavy Cream\n• 1 tbsp Ginger-Garlic Paste\n• 1 tbsp Garam Masala & 1 tbsp Kashmiri Chili\n• 1 tbsp Dried Fenugreek Leaves (Kasuri Methi)")
                            .instructions("1. Marinate chicken in yogurt, ginger-garlic paste, Kashmiri chili, and salt for at least 30 minutes.\n2. Sear chicken in a hot skillet with ghee until charred on the outside. Set aside.\n3. In the same pan, melt butter, add tomato puree, spices, and simmer for 15 minutes.\n4. Blend gravy until silky smooth, return to pan, stir in heavy cream and crushed Kasuri Methi.\n5. Add cooked chicken back to gravy and simmer gently for 8 minutes.\n6. Garnish with cilantro and serve with hot garlic butter naan.")
                            .imageUrl("https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?auto=format&fit=crop&w=1200&q=80")
                            .prepTime(20)
                            .cookTime(25)
                            .servings(4)
                            .difficulty("HARD")
                            .likeCount(77)
                            .category(asian)
                            .user(chefMehul)
                            .build(),

                    Recipe.builder()
                            .title("15-Minute Garlic Butter Herb Shrimp")
                            .description("Succulent jumbo prawns sautéed in rich garlic butter, white wine, lemon juice, and freshly chopped Italian parsley.")
                            .ingredients("• 500g Jumbo Raw Shrimp (peeled and deveined)\n• 4 cloves Garlic (minced)\n• 3 tbsp Salted Butter\n• 2 tbsp Olive Oil\n• 1/4 cup Dry White Wine or Chicken Broth\n• 1 Lemon (juiced & zested)\n• 2 tbsp Fresh Italian Parsley (chopped)")
                            .instructions("1. Pat shrimp dry with paper towels and season with salt and black pepper.\n2. Heat olive oil and 1 tbsp butter in a large skillet over high heat.\n3. Add shrimp in a single layer; cook for 1.5 minutes per side until pink and opaque.\n4. Lower heat to medium, add minced garlic and sauté for 30 seconds until fragrant.\n5. Pour in white wine and lemon juice, scraping up pan browned bits.\n6. Stir in remaining 2 tbsp butter and chopped parsley until a glossy emulsion forms. Serve immediately with crusty bread.")
                            .imageUrl("https://images.unsplash.com/photo-1559742811-822873691df8?auto=format&fit=crop&w=1200&q=80")
                            .prepTime(5)
                            .cookTime(10)
                            .servings(3)
                            .difficulty("EASY")
                            .likeCount(29)
                            .category(quickMeals)
                            .user(foodieEmma)
                            .build(),

                    Recipe.builder()
                            .title("Mediterranean Quinoa & Roasted Veggie Bowl")
                            .description("A wholesome, nourishing plant-based bowl with fluffy quinoa, roasted chickpeas, bell peppers, Kalamata olives, creamy hummus, and tahini drizzle.")
                            .ingredients("• 1 cup Tri-color Quinoa (cooked)\n• 1 can (400g) Chickpeas (rinsed and roasted with cumin)\n• 1 Cucumber (diced) & 1 cup Cherry Tomatoes (halved)\n• 1/2 cup Kalamata Olives\n• 1/2 cup Creamy Garlic Hummus\n• 2 tbsp Tahini Dressing\n• Fresh Mint & Parsley")
                            .instructions("1. Cook quinoa in vegetable broth according to package instructions until light and fluffy.\n2. Toss chickpeas with olive oil, smoked paprika, and cumin; roast at 200°C for 20 minutes until crunchy.\n3. Assemble bowls by placing a base of fluffy quinoa.\n4. Arrange diced cucumber, cherry tomatoes, olives, roasted chickpeas, and a dollop of hummus side-by-side.\n5. Drizzle tahini lemon dressing over the top and garnish with fresh mint.")
                            .imageUrl("https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=1200&q=80")
                            .prepTime(15)
                            .cookTime(20)
                            .servings(2)
                            .difficulty("EASY")
                            .likeCount(31)
                            .category(vegetarian)
                            .user(foodieEmma)
                            .build()
            );

            recipeRepository.saveAll(recipes);
            log.info("Successfully seeded {} sample recipes!", recipes.size());
        }
    }
}
