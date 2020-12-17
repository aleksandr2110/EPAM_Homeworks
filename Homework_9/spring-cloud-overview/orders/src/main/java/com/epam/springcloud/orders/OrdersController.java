package com.epam.springcloud.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping
@RestController
public class OrdersController {
    private List<Order> orderList = new ArrayList<>();

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping
    public String health() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        ServiceInstance usersInfo = discoveryClient.getInstances("users").get(0);
        String hostName = usersInfo.getHost();
        int port = usersInfo.getPort();

        try {
            notificationClient.notify(order.getUserName());
        } catch (RestClientException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

//        try {
//            restTemplate.put("http://localhost:8181/" + order.getProduct(), null);
//        } catch (RestClientException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        orderList.add(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public List<String> getProductsForUser(@PathVariable String userName) {
        return orderList.stream()
                .filter(order -> userName.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }

}
