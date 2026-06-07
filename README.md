# 🍴 BiteBuddy — Recipe Sharing Platform API

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=flat-square&logo=docker)
![Render](https://img.shields.io/badge/Deployed-Render-46E3B7?style=flat-square&logo=render)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

A production-ready **RESTful API** for a recipe sharing platform built with **Java 21** and **Spring Boot 3**. Features full JWT-based authentication, recipe CRUD operations, category management, and keyword search — all deployed live on Render with a Railway MySQL database.

🌐 **Live API:** [https://bitebuddy-nb4t.onrender.com](https://bitebuddy-nb4t.onrender.com)

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [API Endpoints](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [Project Structure](#-project-structure)
- [Testing](#-testing)

---

## ✨ Features

- 🔐 **JWT Authentication** — Secure register & login with Bearer token
- 📖 **Recipe CRUD** — Create, read, update, delete recipes
- 🏷️ **Categories** — Organize recipes by category with auto-generated slugs
- 🔍 **Search & Filter** — Search recipes by keyword or filter by category
- 👤 **User Ownership** — Only recipe owners can edit or delete their recipes
- 🖼️ **Image URL Support** — Attach image URLs to recipes
- ⚡ **Global Exception Handling** — Clean, consistent error responses
- 🐳 **Dockerized** — Multi-stage Docker build for easy deployment

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.2.5 |
| Security | Spring Security + JWT (jjwt 0.11.5) |
| Database | MySQL 8 (Railway) |
| ORM | Spring Data JPA + Hibernate |
| Build Tool | Maven |
| Containerization | Docker |
| Deployment | Render |

---

## 🏗 Architecture

```
Client (Postman / Frontend)
        │
        ▼
  Spring Security Filter (JWT validation)
        │
        ▼
  REST Controllers  (/api/auth, /api/recipes, /api/categories)
        │
        ▼
  Service Layer  (business logic)
        │
        ▼
  Repository Layer  (Spring Data JPA)
        │
        ▼
  MySQL Database (Railway)
```

---

## 📡 API Endpoints

### Auth (Public)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and get JWT token |

### Recipes
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| GET | `/api/recipes` | ❌ | Get all recipes |
| GET | `/api/recipes/{id}` | ❌ | Get recipe by ID |
| GET | `/api/recipes/search?q={keyword}` | ❌ | Search recipes by keyword |
| GET | `/api/recipes/category/{slug}` | ❌ | Filter by category |
| GET | `/api/recipes/my` | ✅ | Get my recipes |
| POST | `/api/recipes` | ✅ | Create a new recipe |
| PUT | `/api/recipes/{id}` | ✅ | Update a recipe (owner only) |
| DELETE | `/api/recipes/{id}` | ✅ | Delete a recipe (owner only) |

### Categories
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| GET | `/api/categories` | ❌ | Get all categories |
| GET | `/api/categories/{id}` | ❌ | Get category by ID |
| POST | `/api/categories` | ✅ | Create a category |
| DELETE | `/api/categories/{id}` | ✅ | Delete a category |

---

## 🚀 Getting Started

### Prerequisites
- Java 21
- Maven
- MySQL 8 (local or cloud)

### Run Locally

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/recipe-api.git
cd recipe-api

# Configure your database credentials in
# src/main/resources/application.properties

# Run the application
mvn spring-boot:run
```

The API will start at `http://localhost:8080`

### Run with Docker

```bash
# Build the Docker image
docker build -t bitebuddy .

# Run the container
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=your_db_url \
  -e SPRING_DATASOURCE_USERNAME=your_username \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  -e APP_JWT_SECRET=your_jwt_secret \
  bitebuddy
```
---

## 📁 Project Structure

```
src/main/java/com/mehul/recipeapi/
├── config/
│   ├── JwtAuthFilter.java       # JWT request filter
│   └── SecurityConfig.java      # Spring Security configuration
├── controller/
│   ├── AuthController.java      # Register & login endpoints
│   ├── RecipeController.java    # Recipe CRUD endpoints
│   └── CategoryController.java  # Category endpoints
├── dto/
│   ├── AuthRequest.java         # Register request body
│   ├── LoginRequest.java        # Login request body
│   ├── AuthResponse.java        # Auth response with token
│   ├── RecipeRequest.java       # Create/update recipe body
│   ├── RecipeResponse.java      # Recipe response shape
│   └── CategoryRequest.java     # Create category body
├── entity/
│   ├── User.java                # User table
│   ├── Recipe.java              # Recipe table
│   └── Category.java            # Category table
├── exception/
│   └── GlobalExceptionHandler.java  # Centralized error handling
├── repository/
│   ├── UserRepository.java      # User DB queries
│   ├── RecipeRepository.java    # Recipe DB queries (incl. search)
│   └── CategoryRepository.java  # Category DB queries
├── security/
│   ├── JwtUtil.java             # JWT generation & validation
│   └── UserDetailsServiceImpl.java  # Load user from DB
└── service/
    ├── AuthService.java         # Auth business logic
    ├── RecipeService.java       # Recipe business logic
    └── CategoryService.java     # Category business logic
```

---

## 🧪 Testing

Import this collection into Postman to test all endpoints:

**1. Register**
```json
POST /api/auth/register
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "123456"
}
```

**2. Login**
```json
POST /api/auth/login
{
  "username": "testuser",
  "password": "123456"
}
```

**3. Create Recipe** *(add `Authorization: Bearer <token>` header)*
```json
POST /api/recipes
{
  "title": "Spaghetti Carbonara",
  "description": "Classic Italian pasta",
  "ingredients": "Spaghetti, eggs, pancetta, parmesan",
  "instructions": "Cook pasta, fry pancetta, mix eggs and cheese, combine.",
  "prepTime": 30,
  "servings": 2,
  "categoryId": 1
}
```

**4. Search Recipes**
```
GET /api/recipes/search?q=pasta
```

---

## 📄 License

This project is licensed under the MIT License.
