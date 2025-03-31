package com.hr.training_management_system.application.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/cursos/*");
        registrationBean.addUrlPatterns("/funcionarios/*");
        registrationBean.addUrlPatterns("/turmas/*");
        registrationBean.addUrlPatterns("/turmaParticipantes/*");

        return registrationBean;
    }
}
