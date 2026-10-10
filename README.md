# 🍴 Bite Buddy — Recipe Sharing Platform & REST API

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger OpenAPI 3](https://img.shields.io/badge/OpenAPI%203-Swagger%20UI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Railway](https://img.shields.io/badge/Deployed-Railway-0B0D0E?style=for-the-badge&logo=railway&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**Bite Buddy** is a production-ready, full-featured **Recipe Sharing Platform and REST API** engineered with **Java 21** and **Spring Boot 3**. Designed for food enthusiasts and developers, it delivers complete JWT authentication, layered CRUD architecture, category taxonomies, keyword search, user profiles, community likes, and an interactive modern web dashboard with live Swagger documentation.

---

## 🌐 Live Demonstrations & Links

* 🚀 **Live Web Platform & API:** [https://bitebuddy-production.up.railway.app/](https://bitebuddy-production.up.railway.app/)
* 📖 **Interactive Swagger UI Docs:** [https://bitebuddy-production.up.railway.app/swagger-ui.html](https://bitebuddy-production.up.railway.app/swagger-ui.html)
* 🩺 **Health Check Probe:** [https://bitebuddy-production.up.railway.app/api/health](https://bitebuddy-production.up.railway.app/api/health)
* 📁 **Postman Collection:** [`docs/BiteBuddy.postman_collection.json`](docs/BiteBuddy.postman_collection.json)

---

## ✨ Key Features

* 🔐 **Stateless JWT Authentication** — Secure registration & login with BCrypt password encryption and Spring Security filter chains.
* 📖 **Detailed Recipe Publishing** — Create, update, and manage recipes with ingredients, step-by-step instructions, prep/cook times, servings, difficulty ratings, and category associations.
* 🔍 **Recipe Discovery & Search** — Search recipes by keyword across titles, descriptions, and ingredients (`/api/recipes/search?q=...`), or filter by category slug (`/api/recipes/category/{slug}`).
* 👤 **User Management & Profiles** — Dedicated chef profiles (`/api/users/{username}`, `/api/users/me`) with published recipe counts and creation history.
* ❤️ **Community Engagement** — Instant recipe like system (`POST /api/recipes/{id}/like`) and trending recipe discovery (`/api/recipes/trending`).
* 🎨 **Interactive Live Web Dashboard** — Glassmorphic web UI served directly from Spring Boot (`/`) with instant search, category filters, and recipe modal views.
* 🌱 **Automated Data Seeder** — Populates categories and delicious sample recipes (Carbonara, Avocado Toast, Butter Chicken, Lava Cake) on first boot.
* 🛡️ **Enterprise Security & CORS** — Parameterized environment variables for zero secret leakage, pre-configured CORS, and role-based ownership checks.

---

## 🏗 System Architecture

```
                                  ┌───────────────────────────────┐
                                  │   Clients / Web UI / Postman  │
                                  └───────────────┬───────────────┘
                                                  │
                                                  ▼
                                  ┌───────────────────────────────┐
                                  │    CORS & Security Filter     │
                                  │      (JwtAuthFilter)          │
                                  └───────────────┬───────────────┘
                                                  │
                                                  ▼
                      ┌───────────────────────────────────────────────────────┐
                      │                   REST Controllers                    │
                      │  AuthController  │ RecipeController │ UserController  │
                      │         CategoryController │ HealthController         │
                      └───────────────────────────┬───────────────────────────┘
                                                  │
                                                  ▼
                      ┌───────────────────────────────────────────────────────┐
                      │                    Service Layer                      │
                      │    AuthService   │   RecipeService  │   UserService   │
                      │                 CategoryService                       │
                      └───────────────────────────┬───────────────────────────┘
                                                  │
                                                  ▼
                      ┌───────────────────────────────────────────────────────┐
                      │             Data Access Layer (JPA/Hibernate)         │
                      │    UserRepository │ RecipeRepository │ CategoryRepo   │
                      └───────────────────────────┬───────────────────────────┘
                                                  │
                                                  ▼
                                  ┌───────────────────────────────┐
                                  │     MySQL Database (Railway)  │
                                  └───────────────────────────────┘
```

---

## 📡 API Reference Table

### 🔐 Authentication (`/api/auth`)
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `POST` | `/api/auth/register` | Public | Register a new user account & get JWT token |
| `POST` | `/api/auth/login` | Public | Login with username/password & get JWT token |

### 📖 Recipes (`/api/recipes`)
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `GET` | `/api/recipes` | Public | Retrieve all published recipes |
| `GET` | `/api/recipes/trending` | Public | Retrieve top 10 trending recipes by likes |
| `GET` | `/api/recipes/{id}` | Public | Get single recipe by ID with full steps & ingredients |
| `GET` | `/api/recipes/search?q={keyword}` | Public | Search recipes by title, description, or ingredients |
| `GET` | `/api/recipes/category/{slug}` | Public | Filter recipes by category slug (`italian`, `desserts`, etc.) |
| `GET` | `/api/recipes/my` | Bearer JWT | Retrieve recipes created by authenticated user |
| `POST` | `/api/recipes` | Bearer JWT | Create and publish a new recipe |
| `POST` | `/api/recipes/{id}/like` | Public | Increment community like counter for a recipe |
| `PUT` | `/api/recipes/{id}` | Bearer JWT | Update an existing recipe (Owner only) |
| `DELETE` | `/api/recipes/{id}` | Bearer JWT | Delete a recipe (Owner only) |

### 🏷️ Categories (`/api/categories`)
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `GET` | `/api/categories` | Public | Get all available categories |
| `GET` | `/api/categories/{id}` | Public | Get category details by ID |
| `POST` | `/api/categories` | Bearer JWT | Create a new category with auto-generated slug |
| `DELETE` | `/api/categories/{id}` | Bearer JWT | Delete a category |

### 👤 User Profiles & Health (`/api/users`, `/api/health`)
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `GET` | `/api/users/me` | Bearer JWT | Retrieve current authenticated user profile & stats |
| `GET` | `/api/users/{username}` | Public | Retrieve public profile and recipes of any chef |
| `GET` | `/api/health` | Public | Health check and deployment status probe |

---

## 🚀 Getting Started Locally

### Prerequisites
* **Java 21** or higher
* **Maven 3.8+** (or included Maven Wrapper `./mvnw`)
* **MySQL 8** (local or cloud)

### 1. Clone & Navigate
```bash
git clone https://github.com/mehul-ranawat/recipe-api.git
cd recipe-api
```

### 2. Configure Database & Secrets
Set your local environment variables or update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/recipe_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
app.jwt.secret=your_super_secret_jwt_key_at_least_256_bits
```

### 3. Run the Application
```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or on Windows PowerShell:
.\mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`:
* Web Dashboard: `http://localhost:8080/`
* Swagger UI Docs: `http://localhost:8080/swagger-ui.html`

---

## 🐳 Docker Containerization

Build and run using Docker:

```bash
# Build Docker image
docker build -t bitebuddy .

# Run Docker container
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=your_mysql_connection_string \
  -e SPRING_DATASOURCE_USERNAME=your_user \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  -e APP_JWT_SECRET=your_jwt_secret \
  bitebuddy
```

---

## 🚂 Railway Deployment Guide

1. Create a new project on [Railway.app](https://railway.app/).
2. Add a **MySQL** database service on Railway.
3. Add a **GitHub Repo** service and select `mehul-ranawat/recipe-api`.
4. Under **Variables**, add:
   * `SPRING_DATASOURCE_URL`: `${{MySQL.DATABASE_URL}}`
   * `SPRING_DATASOURCE_USERNAME`: `${{MySQL.MYSQLUSER}}`
   * `SPRING_DATASOURCE_PASSWORD`: `${{MySQL.MYSQLPASSWORD}}`
   * `APP_JWT_SECRET`: `your-random-jwt-secret-key`
5. Railway will automatically build via Dockerfile and deploy to `https://bitebuddy-production.up.railway.app/`.

---

## 🧪 Testing with Postman

1. Open Postman.
2. Click **Import** -> Select `docs/BiteBuddy.postman_collection.json`.
3. Set the `baseUrl` collection variable to `http://localhost:8080` (local) or `https://bitebuddy-production.up.railway.app` (live).
4. Execute `POST /api/auth/login` — the JWT token is automatically saved into the environment!

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
