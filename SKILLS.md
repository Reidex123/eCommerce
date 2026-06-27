Building a simple eCommerce app using Java Spring

# Tools
 - Java 17+
 - Maven (v3.4+)

# Dependencies
 - Spring Web
 - Spring Data JPA
 - MySQL Driver
 - Lombok
 - Validation I/O

# PackagesAndEntities
 - Model: 1. Product
          2. Image
          3. Category
 - Service: - Product: 1. ProductService
                       2. IProductService (Interface)
            - Image: 1. ImageService
                     2. iImageService(interface)
            - Category: 1. CategoryService
                        2. iCategoryService(interface)
 - Repository: 1. ProductRepository
               2. ImageRepository
               3. CategoryRepository
 - Exceptions