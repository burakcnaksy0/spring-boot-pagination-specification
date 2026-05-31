package com.burakcanaksoy.springbootpaginationspecification.advanced.controller;


import com.burakcanaksoy.springbootpaginationspecification.advanced.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

public interface WriteOnlyController<Request , Response , ID> {
    ResponseEntity<ApiResponse<Response>> create(@Valid @RequestBody Request request);
    ResponseEntity<ApiResponse<List<Response>>> createBatch(@RequestBody List<@Valid Request> requests);
    ResponseEntity<ApiResponse<Void>> delete(ID id);
}
