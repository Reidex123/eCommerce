# eCommerce Backend (Spring Boot)

A backend REST API for a simple eCommerce application, built with Spring Boot as a learning project to understand core Spring concepts and how to structure a scalable layered architecture.

- **Author:** Koketso Gaowelwe
- **Start date:** 19/06/2026
- **Purpose:** Understand the fundamentals of Spring Boot and build the ability to structure and scale applications as they grow.

> ⚠️ **Project status: in progress.** The full data and service layers are implemented across all three domains (Product, Category, Image). The Image controller is functional with upload and download endpoints. Product and Category controllers are scaffolded but not yet wired up. See [Roadmap](#roadmap--next-steps) below.

---

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.0**
  - Spring Web MVC (`spring-boot-starter-webmvc`)
  - Spring Data JPA (`spring-boot-starter-data-jpa`)
  - Spring Validation (`spring-boot-starter-validation`)
  - Spring Boot Actuator
- **MySQL** — accessed via Hibernate/JPA (`mysql-connector-j`)
- **Lombok** — reduces boilerplate on entity, service, and DTO classes
- **Maven** — build and dependency management
- **JUnit 5** — testing (currently default context-load test)

---

## Project Structure

```
src/main/java/com/API/eCommerce/
├── ECommerceApplication.java
│
├── model/
│   ├── Product.java
│   ├── Category.java
│   └── Image.java
│
├── Repository/
│   ├── productRepository.java
│   ├── categoryRepository.java
│   └── imageRepository.java
│
├── service/
│   ├── Product/
│   │   ├── iProductService.java
│   │   └── ProductService.java
│   ├── Category/
│   │   ├── iCategoryService.java
│   │   └── CategoryService.java
│   └── Image/
│       ├── iImageService.java
│       └── ImageService.java
│
├── controller/
│   ├── ProductController.java     ⬜ stub — not yet implemented
│   ├── CategoryController.java    ⬜ stub — not yet implemented
│   └── ImageController.java      ✅ implemented
│
├── DTOs/
│   └── ImageDTO.java
│
├── request/
│   └── AddProductRequest.java
│
├── response/
│   └── ApiResponse.java
│
└── Exceptions/
    ├── ProductNotFoundException.java
    ├── ResourceNotFoundException.java
    └── AlreadyExistException.java
```

---

## What's Implemented

### Domain model

Three JPA entities with mapped relationships:

- **`Product`** — `id`, `name`, `brand`, `price`, `quantity`, `description`. Belongs to one `Category` (`@ManyToOne`, cascading) and owns many `Image`s (`@OneToMany`, cascading + orphan removal).
- **`Category`** — `id`, `name`, and a one-to-many back-reference to its `Product`s.
- **`Image`** — `id`, `fileName`, `fileType`, raw image data stored as a `Blob` (`@Lob`), `downloadUrl`, and a `@ManyToOne` link back to its `Product`.

### Repository layer

All three repositories extend `JpaRepository`. `productRepository` adds derived query methods beyond standard CRUD:

| Method | Description |
|---|---|
| `findByCategory(String)` | All products in a category |
| `findByBrand(String)` | All products by brand |
| `findByName(String)` | Products matching name |
| `findByBrandAndCategory(String, String)` | Filter by brand + category |
| `findByBrandAndName(String, String)` | Filter by brand + name |
| `countByBrandAndName(String, String)` | Count by brand + name |

`categoryRepository` adds `findByName(String)` and `existsByName(String)`.

### Service layer

All three service contracts (interface + implementation) are complete:

**ProductService** — full CRUD plus queries by category, brand, name, and combinations. Throws `ProductNotFoundException` on missing IDs.

**CategoryService** — get all, get by ID, get by name, add (with duplicate-name guard via `AlreadyExistException`), update by ID, delete by ID. Throws `ResourceNotFoundException` on missing entries.

**ImageService** — get by ID, delete by ID, save a batch of `MultipartFile` images linked to a product (stored as `SerialBlob`, returns `ImageDTO` list with generated download URLs), and update an existing image file.

### DTOs / Request / Response

- **`ImageDTO`** — `id`, `fileName`, `downloadUrl` — used as the API response shape for image data.
- **`AddProductRequest`** — flat request object (`name`, `brand`, `price`, `quantity`, `description`, `category`) for creating products.
- **`ApiResponse`** — generic wrapper `{ message, data }` used by controllers to return consistent responses.

### Exceptions

| Exception | When thrown |
|---|---|
| `ProductNotFoundException` | Product ID not found |
| `ResourceNotFoundException` | Category or Image ID/name not found |
| `AlreadyExistException` | Category with same name already exists |

### REST API

Base path: `/api/v1` (configured in `application.properties`)

The only fully wired controller right now is `ImageController`:

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/images/upload` | Upload one or more images for a product (`files` + `productId` as request params) |
| `GET` | `/api/v1/images/image/download/{imageId}` | Download an image by ID (returns raw binary with correct `Content-Type`) |

`ProductController` and `CategoryController` exist as `@RestController` stubs with no mapped endpoints yet.

---

## Getting Started

### Prerequisites
- Java 17+
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper)
- MySQL running locally

### Setup

1. Create a local MySQL database:
   ```sql
   CREATE DATABASE ecommerce;
   ```

2. Set your MySQL credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

   > ⚠️ Do not commit real credentials to a public repo. Move sensitive values to a local `.env` file or use environment variable substitution.

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The app starts on `http://localhost:8080`. Hibernate will auto-create/update the `product`, `category`, and `image` tables from the entity definitions (`ddl-auto=update`).

---

## What's Not Built Yet

- **Product and Category controllers** — stubs exist but no endpoints are mapped yet.
- **Global exception handling** — no `@ControllerAdvice` to turn `ProductNotFoundException`, `ResourceNotFoundException`, and `AlreadyExistException` into clean HTTP error responses.
- **Request validation** — `spring-boot-starter-validation` is included but `@Valid` annotations aren't applied to controller methods yet.
- **Authentication / authorization** — no Spring Security setup yet.
- **Tests** — only the default Spring context-load smoke test exists.

---

## Roadmap / Next Steps

1. Implement `ProductController` — wire `ProductService` to HTTP endpoints (GET all, GET by ID, POST, PUT, DELETE, filter by brand/category).
2. Implement `CategoryController` — expose Category CRUD over HTTP.
3. Add `@ControllerAdvice` / `@ExceptionHandler` for consistent error response formatting.
4. Apply `@Valid` on incoming request bodies in controllers.
5. Expand test coverage — unit tests for services, integration tests for repositories and controllers.
6. Add Spring Security once core CRUD is stable.

---

## License

MIT — see [LICENSE](LICENSE).
