package com.epam.springcloud.notification;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Notification {
    String user;
    Notifier notifyBy = Notifier.EMAIL;

    public void setUser(String user) {
        this.user = user;
    }


    enum Notifier {
        EMAIL
    }
}
