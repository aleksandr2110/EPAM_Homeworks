package com.epam.springcloud.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notifications")
public interface NotificationClient {

    @PostMapping("/{userName}")
    UserDto notify(@PathVariable String userName);
}
