package com.burakcanaksoy.springbootpaginationspecification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.burakcanaksoy.springbootpaginationspecification",
        "com.burakcanaksoy.common"
})
public class SpringBootPaginationSpecificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPaginationSpecificationApplication.class, args);
    }
}
