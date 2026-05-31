package com.burakcanaksoy.springbootpaginationspecification.advanced.controller;


import com.burakcanaksoy.springbootpaginationspecification.advanced.ApiResponse;
import com.burakcanaksoy.springbootpaginationspecification.advanced.service.ReadOnlyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractReadOnlyController<Response, ID> implements ReadOnlyController<Response, ID> {
    protected final ReadOnlyService<Response, ID> service;

    protected AbstractReadOnlyController(ReadOnlyService<Response, ID> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponse<Response>> getById(@PathVariable("id") ID id) {
        ApiResponse<Response> response = ApiResponse.success("Başarıyla bulundu: " + id, service.getById(id), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponse<List<Response>>> getAll() {
        ApiResponse<List<Response>> response = ApiResponse.success("Başarıyla listelendi", service.getAll(), HttpStatus.OK);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
