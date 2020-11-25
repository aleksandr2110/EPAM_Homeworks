package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Aleksandr on 24.11.2020.
 */
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class AppConfig {
}
