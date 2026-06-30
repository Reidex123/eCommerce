# eCommerce Backend (Spring Boot)

A backend API for a simple eCommerce application, built with Spring Boot as a learning project to understand core Spring concepts and how to structure an application that can scale as it grows.

- **Author:** Koketso Gaowelwe
- **Start date:** 19/06/2026
- **Purpose:** Understand the fundamentals of Spring, and build the ability to structure and scale applications as they grow.

> ⚠️ **Project status: in progress.** The data layer (entities, repository, service) is built out for the `Product` domain. There is currently **no REST controller / API layer yet** — the application runs, connects to the database, and exposes Actuator endpoints, but there are no HTTP endpoints for clients to call yet. See [Roadmap](#roadmap--next-steps) below.

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.0**
  - Spring Web (`spring-boot-starter-webmvc`)
  - Spring Data JPA (`spring-boot-starter-data-jpa`)
  - Spring Validation (`spring-boot-starter-validation`)
  - Spring Boot Actuator
- **MySQL** (via `mysql-connector-j`), accessed through Hibernate/JPA
- **Lombok** — reduces boilerplate (getters/setters/constructors) on entity and service classes
- **Maven** — build and dependency management
- **JUnit 5** — testing (currently just the default context-load test)

## Project Structure

```
src/main/java/com/API/eCommerce/
├── ECommerceApplication.java        # Spring Boot entry point
├── Exceptions/
│   └── ProductNotFoundException.java
├── model/
│   ├── Product.java                 # Product entity
│   ├── Category.java                # Category entity
│   └── Image.java                   # Image entity (stored as BLOB)
├── Repository/
│   └── productRepository.java       # Spring Data JPA repository for Product
└── service/Product/
    ├── iProductService.java         # Product service contract
    └── ProductService.java          # Product service implementation
```

## What's Implemented So Far

### Domain model

Three JPA entities, with relationships mapped using Lombok for boilerplate:

- **`Product`** — `id`, `name`, `brand`, `price`, `quantity`, `description`. Each product belongs to one `Category` (`@ManyToOne`) and can have many `Image`s (`@OneToMany`, cascading, with orphan removal).
- **`Category`** — `id`, `name`, and a one-to-many link back to its `Product`s.
- **`Image`** — `id`, `fileName`, `fileType`, a `Blob` for the actual image data, a `sourceUrl`, and a `@ManyToOne` link back to its `Product`.

### Repository layer

`productRepository` extends `JpaRepository<Product, Long>` and adds derived query methods:
- `findByCategory`, `findByBrand`, `findByName`
- `findByBrandAndCategory`, `findByBrandAndName`
- `countByBrandAndName`

### Service layer

`iProductService` defines the contract, implemented by `ProductService`, covering:
- Get all products / get product by ID
- Add a product
- Update a product by ID
- Delete a product by ID
- Query products by category, brand, name, or brand+category/brand+name combinations
- Count products by brand and name

Each "not found" path throws a custom `ProductNotFoundException`.

### Configuration

- Connects to a local MySQL database (`ecommerce` schema) via Hibernate, with `ddl-auto=update` so the schema evolves automatically from the entities.
- Runs on port `8080`.

## What's Not Built Yet

- **REST controllers** — no `@RestController` classes yet, so there's currently no way to hit the Product service over HTTP.
- **Category and Image services/repositories** — only the `Product` domain has its own repository and service so far; `Category` and `Image` exist only as entities.
- **DTOs / request-response mapping** — entities are currently used directly; no separation between persistence model and API contract yet.
- **Global exception handling** (`@ControllerAdvice`) to turn `ProductNotFoundException` into proper HTTP error responses.
- **Validation** on incoming data (the `spring-boot-starter-validation` dependency is included but not yet used).
- **Authentication / authorization.**
- **Tests** beyond the default Spring context-load smoke test.

## Getting Started

### Prerequisites
- Java 17+
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper)
- A running MySQL instance

### Setup

1. Create a local MySQL database named `ecommerce`.
2. Update `src/main/resources/application.properties` with your own MySQL username/password.
3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

The app will start on `http://localhost:8080`. With `ddl-auto=update`, Hibernate will create the `product`, `category`, and `image` tables automatically based on the entity classes.

## Roadmap / Next Steps

1. Add a `ProductController` exposing CRUD endpoints over `ProductService`.
2. Build out `Category` and `Image` repositories/services, and wire image upload handling.
3. Add a `@ControllerAdvice` / `@ExceptionHandler` layer for clean error responses.
4. Introduce DTOs and request validation.
5. Expand test coverage (unit tests for services, integration tests for repositories/controllers).
6. Add authentication (e.g. Spring Security) once core CRUD is stable.

## License

MIT — see [LICENSE](LICENSE).
