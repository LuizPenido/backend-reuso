package com.inventorymanager.project.service;

import org.springframework.stereotype.Component;

@Component
public class TransactionContext {

    private static final ThreadLocal<String> transactionCode = new ThreadLocal<>();

    public void setTransactionCode(String code) {
        transactionCode.set(code);
    }

    public String getTransactionCode() {
        return transactionCode.get();
    }

    public void clear() {
        transactionCode.remove();
    }
}