<div align="center">

# Simple E-Commerce Web App 

**A layered Spring Boot backend, paired with a minimal dark-mode React storefront.**

[![Java](https://img.shields.io/badge/Java-Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/Frontend-React%20%2B%20Vite-61DAFB?style=flat-square&logo=react&logoColor=white)](https://vitejs.dev/)
[![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Status](https://img.shields.io/badge/Status-Completed%20%7C%20may%20be%20updated-c9a876?style=flat-square)]()

</div>

---

## Overview

This is a small e-commerce catalog built to properly connect a Spring Boot backend to a React frontend — end to end, for real, no shortcuts. It started from an **EmbarkX** tutorial, but diverged early: several practices from the original walkthrough were reworked to fit tighter backend conventions, and the frontend was built independently, without following any tutorial for it.

Right now, it's a **read-only product catalog**: categories and products are seeded into MySQL on startup, served through a REST API, and rendered in a clean, dark, distraction-free UI. This first version is complete — the frontend and backend are properly linked and working end to end — though it may see further updates down the line. The roadmap below outlines what those could look like.

---

## Features

-  Browse all products, or filter by category
-  Data persisted in MySQL via Spring Data JPA
-  Automatic seeding of sample categories and products on startup
-  REST API consumed by a separate React frontend over CORS
-  Minimal, dark-mode-first UI — built for readability, not flash

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot, Spring Data JPA |
| Database | MySQL |
| Frontend | React (JavaScript), Vite |
| API style | REST, JSON |

---

## Architecture

The backend follows a standard layered structure:

```
Controller → Service → Repository → Entity
```

- **Repositories** extend `JpaRepository` for standard CRUD, with a custom query on `ProductRepository` (`findByCategoryId`) for category filtering.
- **Services** currently delegate directly to repositories — there's no business logic layer yet, and that's intentional at this stage rather than an oversight.
- **Controllers** return `ResponseEntity` rather than raw lists or objects. This was a deliberate deviation from the original tutorial: wrapping responses in `ResponseEntity` keeps explicit control over status codes and response shape, instead of letting Spring's defaults decide.

> **Entity relationship:** one `Category` has many `Product`s. `Category.products` is marked `@JsonIgnore` to prevent recursive JSON serialization when a category is fetched.

The frontend is a separate concern entirely — no shared tooling, no monorepo — just a Vite-powered React app that talks to the backend purely over HTTP.

---

## API Reference

Base URL: `http://localhost:8080/api/v1`

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/categories` | Returns all categories |
| `GET` | `/products` | Returns all products, each with a nested category |
| `GET` | `/products/category/{categoryId}` | Returns products belonging to a given category UUID |

<details>
<summary><strong>Example response — <code>GET /products</code></strong></summary>

```json
[
  {
    "id": "product-uuid",
    "name": "Wireless Headphones",
    "description": "Over-ear Bluetooth headphones with noise cancellation",
    "imageUrl": "https://picsum.photos/seed/zahranstore/500/500",
    "price": 79.99,
    "category": {
      "id": "category-uuid",
      "name": "Electronics"
    }
  }
]
```

</details>

Only `GET` endpoints exist at this stage — there is no create, update, or delete functionality yet.

---

## Getting Started

### Prerequisites

- Java (with Maven)
- Node.js + npm
- A running MySQL instance

### 1. Backend

```bash
./mvnw spring-boot:run
```

The backend starts on `http://localhost:8080`. On every startup, `DataSeeder` clears and repopulates the `products` and `categories` tables with sample data — useful for development, but worth knowing before pointing this at real data.

Database connection is configured in `application.properties` (database name, credentials, and Hibernate's `update` schema mode).

### 2. Frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend starts on `http://localhost:5173` and talks to the backend at `http://localhost:8080/api/v1`. CORS is explicitly configured on the backend to allow requests from this origin.

---

## Frontend Structure

```
frontend/
└── src/
    ├── api/          # fetch wrapper + per-resource API calls
    ├── components/
    │   ├── layout/   # Header, navigation
    │   └── product/  # ProductCard, ProductGrid
    ├── pages/        # Home
    └── styles/       # design tokens (colors, spacing)
```

---

## Design Notes

The UI intentionally avoids the "generic dashboard" look: a dark, low-contrast palette, one deliberate accent color, hairline borders instead of shadows on static content, and just enough rounding on interactive elements (buttons, inputs) to keep things from feeling cold. No gradients, no decoration for decoration's sake.

---

## Roadmap

- [ ] Introduce DTOs instead of exposing entities directly through the API
- [ ] Add `POST` / `PUT` / `DELETE` endpoints for products and categories
- [ ] Global exception handling
- [ ] Guard the seeder so it only populates an empty database, instead of wiping data on every restart

---

## Credits

Backend structure initially inspired by an **EmbarkX** tutorial, with several implementation choices — notably the use of `ResponseEntity` for explicit HTTP response control — revised along the way. The frontend was built independently.

---

<div align="center">

*Built one layer at a time.*

</div>
