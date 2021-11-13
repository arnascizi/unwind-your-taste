package com.github.uyt.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.github.uyt.model"})
// @EnableJpaRepositories(basePackages = {"com.github.uyt.bl"})
public class AppConfig {}
