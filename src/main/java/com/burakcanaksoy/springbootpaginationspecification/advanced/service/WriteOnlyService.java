package com.burakcanaksoy.springbootpaginationspecification.advanced.service;

import java.util.*;

public interface WriteOnlyService<Request,Response,ID> {
    Response create(Request request);
    List<Response> createBatch(List<Request> requests);
    void delete(ID id);
}
