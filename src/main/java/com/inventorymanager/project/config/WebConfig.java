package com.inventorymanager.project.config;

import com.inventorymanager.project.service.TransactionFilter;
import com.inventorymanager.project.service.TransactionContext;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<TransactionFilter> transactionFilter(TransactionContext transactionContext) {
        FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TransactionFilter(transactionContext));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public TransactionContext transactionContext() {
        return new TransactionContext();
    }
}