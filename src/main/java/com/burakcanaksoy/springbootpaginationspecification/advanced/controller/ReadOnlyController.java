package com.burakcanaksoy.springbootpaginationspecification.advanced.controller;

import com.burakcanaksoy.springbootpaginationspecification.advanced.ApiResponse;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface ReadOnlyController<Response, ID> {
    ResponseEntity<ApiResponse<Response>> getById(ID id);
    ResponseEntity<ApiResponse<List<Response>>> getAll();
}
