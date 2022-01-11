package com.github.uyt.ui.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.github.uyt"})
@EntityScan(basePackages = {"com.github.uyt.model"})
@EnableJpaRepositories(basePackages = {"com.github.uyt.bl.repository"})
public class AppConfig {}
