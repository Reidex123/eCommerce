Building a simple eCommerce app using Java Spring

# Tools
 - Java 17+
 - Maven (v3.4+)
 - MySQL

# Dependencies
 - Spring Web
 - Spring Data JPA
 - MySQL Driver
 - Lombok
 - Validation I/O
 - Spring Boot Actuator

# Concepts Practiced So Far
 - Spring Boot project setup and structure (Maven, starter dependencies)
 - JPA entity modeling and relationships: `@ManyToOne` (Product → Category) and
   `@OneToMany` with cascading + orphan removal (Product → Image)
 - Reducing boilerplate with Lombok (`@Getter`, `@Setter`, `@Data`,
   `@AllArgsConstructor`, `@NoArgsConstructor`)
 - Spring Data JPA repositories and derived query methods (`findBy...`, `countBy...`)
 - Layered architecture: model → repository → service, coding service classes
   against an interface (`iProductService` / `ProductService`)
 - Constructor-based dependency injection (`@RequiredArgsConstructor`)
 - Custom runtime exceptions for domain error handling (`ProductNotFoundException`)
 - Hibernate schema auto-generation from entities (`ddl-auto=update`)

# PackagesAndEntities

 - Model:
   1. Product ✅ implemented
   2. Image ✅ implemented (entity only)
   3. Category ✅ implemented (entity only)

 - Service:
   - Product:
     1. ProductService ✅ implemented
     2. iProductService (interface) ✅ implemented
   - Image:
     1. ImageService ⬜ not started
     2. iImageService (interface) ⬜ not started
   - Category:
     1. CategoryService ⬜ not started
     2. iCategoryService (interface) ⬜ not started

 - Repository:
   1. productRepository ✅ implemented (Product CRUD + derived queries by
      category, brand, name, and combinations)
   2. ImageRepository ⬜ not started
   3. CategoryRepository ⬜ not started

 - Exceptions:
   1. ProductNotFoundException ✅ implemented
   2. (Image / Category not-found exceptions) ⬜ not started

 - Controller: ⬜ not started (no REST endpoints exposed yet — this is the
   next concept to tackle: `@RestController`, request mapping, and turning
   exceptions into proper HTTP responses via `@ControllerAdvice`)

# Next Concepts to Learn / Apply
 - Building REST controllers (`@RestController`, `@RequestMapping`, `@GetMapping`,
   `@PostMapping`, etc.) to expose ProductService over HTTP
 - Global exception handling with `@ControllerAdvice` / `@ExceptionHandler`
 - DTOs and bean validation (`@Valid`, `spring-boot-starter-validation`) on
   incoming requests
 - Filling out Image and Category repository/service layers
 - Writing unit and integration tests beyond the default context-load test
 - Securing endpoints (Spring Security) once core CRUD is in place
