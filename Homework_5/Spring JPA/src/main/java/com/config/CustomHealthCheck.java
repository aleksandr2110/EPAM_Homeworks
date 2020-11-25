package com.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * Created by Aleksandr on 25.11.2020.
 */
@Component
public class CustomHealthCheck extends AbstractHealthIndicator {

    // localhost:8080/actuator/health
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up()
                .withDetail("Application", "Faculty").build();
    }
}
