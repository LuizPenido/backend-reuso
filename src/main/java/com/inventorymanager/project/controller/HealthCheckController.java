package com.inventorymanager.project.controller;

import com.inventorymanager.project.service.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @Autowired
    private Logger transactionLogger;

    @GetMapping
    public String healthCheck() {
        transactionLogger.log("Health check endpoint was accessed.");
        return "Application is running!";
    }
}
