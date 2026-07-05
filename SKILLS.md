# 🛒 eCommerce Backend — Skills & Progress Tracker

> A personal log of tools, concepts, and architectural patterns applied while building a production-style Spring Boot REST API from scratch.

---

## 🧰 Tools & Environment

| Tool | Version |
|---|---|
| Java | 17+ |
| Maven | 3.4+ |
| MySQL | 8.x |
| Spring Boot | 4.1.0 |
| IDE | VS Code / IntelliJ IDEA |

---

## 📦 Dependencies

| Dependency | Purpose |
|---|---|
| `spring-boot-starter-webmvc` | REST controller layer |
| `spring-boot-starter-data-jpa` | ORM and repository abstraction |
| `spring-boot-starter-validation` | Bean validation (`@Valid`, `@NotNull`, etc.) |
| `spring-boot-starter-actuator` | Application health and monitoring |
| `mysql-connector-j` | MySQL JDBC driver |
| `lombok` | Boilerplate reduction (getters, constructors, etc.) |

---

## 🧠 Concepts Mastered

### Architecture & Design
- Layered architecture: **Model → Repository → Service → Controller**
- Programming to interfaces — separating contract (`iProductService`) from implementation (`ProductService`)
- DTO pattern — decoupling the persistence model from the API response shape
- Generic API response wrapper (`ApiResponse { message, data }`) for consistent endpoint responses
- Duplicate-entry guard logic using `Optional.filter().map().orElseThrow()`

### Spring Boot & JPA
- Spring Boot project setup and structure via Spring Initializr
- JPA entity modeling with mapped relationships:
  - `@ManyToOne` — Product → Category
  - `@OneToMany` with `CascadeType.ALL` and `orphanRemoval` — Product → Image
- Spring Data JPA derived query methods (`findBy...`, `countBy...`, `existsBy...`)
- Hibernate schema auto-generation from entities (`ddl-auto=update`)
- Constructor-based dependency injection with `@RequiredArgsConstructor`

### REST API Development
- Building REST controllers with `@RestController`, `@RequestMapping`, `@PostMapping`, `@GetMapping`
- Path variable and request parameter binding (`@PathVariable`, `@RequestParam`)
- Multipart file upload handling (`MultipartFile`, `List<MultipartFile>`)
- Storing binary image data in MySQL as a `Blob` (`@Lob`, `SerialBlob`)
- Serving file downloads from a REST endpoint (`ByteArrayResource`, `Content-Disposition` header, `MediaType`)

### Exception Handling
- Custom runtime exceptions for domain-specific error scenarios
- Multiple exception types for different failure modes (`ProductNotFoundException`, `ResourceNotFoundException`, `AlreadyExistException`)

### Lombok
- `@Getter`, `@Setter`, `@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`, `@RequiredArgsConstructor`

## 📋 Implementation Status

### Model
| Class | Status |
|---|---|
| `Product` | ✅ Complete |
| `Category` | ✅ Complete |
| `Image` | ✅ Complete |

### Repository
| Class | Status | Notes |
|---|---|---|
| `productRepository` | ✅ Complete | CRUD + derived queries by brand, category, name, and combinations |
| `categoryRepository` | ✅ Complete | `findByName`, `existsByName` |
| `imageRepository` | ✅ Complete | Standard CRUD |

### Service
| Class | Status |
|---|---|
| `iProductService` + `ProductService` | ✅ Complete |
| `iCategoryService` + `CategoryService` | ✅ Complete |
| `iImageService` + `ImageService` | ✅ Complete |

### DTOs / Request / Response
| Class | Status |
|---|---|
| `ImageDTO` | ✅ Complete |
| `AddProductRequest` | ✅ Complete |
| `ApiResponse` | ✅ Complete |

### Exceptions
| Class | Status |
|---|---|
| `ProductNotFoundException` | ✅ Complete |
| `ResourceNotFoundException` | ✅ Complete |
| `AlreadyExistException` | ✅ Complete |

### Controllers
| Class | Status | Endpoints |
|---|---|---|
| `ImageController` | ✅ Complete | `POST /api/v1/images/upload`, `GET /api/v1/images/image/download/{imageId}` |
| `ProductController` | 🔧 In Progress | Stub only — no endpoints mapped yet |
| `CategoryController` | 🔧 In Progress | Stub only — no endpoints mapped yet |

---

## 🎯 Next Concepts to Apply

| Concept | Description |
|---|---|
| **REST Controllers** | Wire `ProductController` and `CategoryController` to their service layers with full CRUD endpoints |
| **Global Exception Handling** | `@ControllerAdvice` + `@ExceptionHandler` to return clean, structured HTTP error responses |
| **Bean Validation** | Apply `@Valid`, `@NotNull`, `@NotBlank` to incoming request bodies in controllers |
| **Unit Testing** | Test service logic in isolation using Mockito to mock repositories |
| **Integration Testing** | Test controller endpoints with `@WebMvcTest` and `MockMvc` |
| **Spring Security** | Add authentication and authorization once core CRUD is stable |

---