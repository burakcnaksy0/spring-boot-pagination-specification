package com.burakcanaksoy.springbootpaginationspecification.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilter {
    @NotBlank(message = "Ürün adı boş olamaz.")
    @Size(max = 255, message = "Ürün adı en fazla 255 karakter olabilir.")
    private String name;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Minimum fiyat negatif olamaz.")
    private BigDecimal minPrice;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Maksimum fiyat negatif olamaz.")
    private BigDecimal maxPrice;

    @Min(value = 0, message = "Minimum stok miktarı negatif olamaz.")
    private Integer minStockQuantity;

    @Min(value = 0, message = "Maksimum stok miktarı negatif olamaz.")
    private Integer maxStockQuantity;
}