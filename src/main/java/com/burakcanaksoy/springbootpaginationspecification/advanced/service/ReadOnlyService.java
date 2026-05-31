package com.burakcanaksoy.springbootpaginationspecification.advanced.service;

import java.util.*;

public interface ReadOnlyService<Response,ID> {
    Response getById(ID id);
    List<Response> getAll();
}
