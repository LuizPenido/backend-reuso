package com.inventorymanager.project.service;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class TransactionFilter implements Filter {

    private final TransactionContext transactionContext;

    @Autowired
    public TransactionFilter(TransactionContext transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String transactionCode = UUID.randomUUID().toString();
        transactionContext.setTransactionCode(transactionCode);

        try {
            chain.doFilter(request, response);
        } finally {
            transactionContext.clear();
        }
    }

    @Override
    public void destroy() {}
}