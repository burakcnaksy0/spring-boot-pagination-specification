package com.burakcanaksoy.springbootpaginationspecification.product;


import com.burakcanaksoy.springbootpaginationspecification.advanced.ApiResponse;
import com.burakcanaksoy.springbootpaginationspecification.advanced.controller.AbstractCrudController;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractCrudController<ProductCreateRequest,ProductResponse,Long> {
    private final ProductService service;
    protected ProductController(ProductService service) {
        super(service);
        this.service=service;
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getProduct(@RequestParam int pageNumber ,
                                                                         @RequestParam int pageSize ,
                                                                         @RequestParam(required = false) String field,
                                                                         @RequestParam(required = false) String sort){
        ApiResponse<Page<ProductResponse>> response = ApiResponse.success("Sayfalı olarak başarıyla listelendi", service.getAllForPage(pageNumber,pageSize,field,sort), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
