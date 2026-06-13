package com.burakcanaksoy.springbootpaginationspecification.product;


import com.burakcanaksoy.common.advanced.ApiResponse;
import com.burakcanaksoy.common.advanced.controller.AbstractCrudController;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractCrudController<ProductCreateRequest, ProductResponse, Long> {
    private final ProductService service;

    protected ProductController(ProductService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getProduct(@RequestParam int pageNumber,
                                                                         @RequestParam int pageSize,
                                                                         @RequestParam(required = false) String field,
                                                                         @RequestParam(required = false) String sort) {
        ApiResponse<Page<ProductResponse>> response = ApiResponse.success("Sayfalı olarak başarıyla listelendi", service.getAllForPage(pageNumber, pageSize, field, sort), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/pageimpl")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getProducts() {
        ApiResponse<Page<ProductResponse>> response = ApiResponse.success("Sayfalı olarak başarıyla listelendi", service.getForPage(), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<Map<String, Object>>> info() {
        ApiResponse<Map<String, Object>> response = ApiResponse.success("Bilgilendirme mesajı", service.info(), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<Product>>> searchAndPage(@RequestParam int page,
                                                                    @RequestParam int size,
                                                                    @RequestParam String field,
                                                                    @RequestParam String value) {
        ApiResponse<Page<Product>> response = ApiResponse.success("search and page mesajı", service.searchAndPage(page, size, field, value), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @GetMapping("/spec")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> spec(@RequestParam(defaultValue = "", required = false) String sku,
                                                                   @RequestParam(defaultValue = "", required = false) String name,
                                                                   @RequestParam(required = false) BigDecimal price) {
        ApiResponse<List<ProductResponse>> response = ApiResponse.success("Search operation", service.spec(sku, name, price), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/price")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> betweenPrice(@RequestParam(required = false) BigDecimal minPrice,
                                                                           @RequestParam(required = false) BigDecimal maxPrice) {
        ApiResponse<List<ProductResponse>> response = ApiResponse.success("Search operation", service.betweenPrice(minPrice, maxPrice), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> filter(@Valid @RequestBody ProductFilter filter) {
        ApiResponse<List<ProductResponse>> response = ApiResponse.success("Filtered operation", service.filter(filter), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/filter-sort")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsWithFilterAndSort(@RequestParam(required = false) String name,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortField,
                                                     @RequestParam(defaultValue = "asc") String sortDirection) {
        ApiResponse<List<ProductResponse>> response = ApiResponse.success("Filtered operation", service.getProductsWithFilterAndSort(name,page,size,sortField,sortDirection), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
