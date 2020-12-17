package com.epam.springcloud.notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class NotificationController {
    private final Set<Notification> notifications = new HashSet<Notification>();

    @PostMapping
    public Notification notify(@RequestBody String user) {
        Notification notification = new Notification();
        notification.setUser(user);
        notifications.add(notification);
        return notification;
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return new ArrayList(notifications);
    }
}
