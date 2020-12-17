package com.epam.springcloud.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "orders", fallback = OrderClient.OrderClientImpl.class)
public class OrderClient {

    @GetMapping("/users/{name}")
    List<String> getProducts(@PathVariable String name);

    @Component
    class OrderClientImpl implements OrderClient {
        @Override
        public List<String> getProducts(String name) {
            return List.of("One", "Two");
        }
    }
}
