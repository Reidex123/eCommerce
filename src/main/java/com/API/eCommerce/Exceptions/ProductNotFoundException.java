/**
 * Custom exception class for handling cases where a product is not found in the eCommerce application.
 * @author Koketso
 * @version 1.0
 * @since 2026-06-20
 */

package com.API.eCommerce.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

}
