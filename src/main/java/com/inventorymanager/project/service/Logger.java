package com.inventorymanager.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Logger {

    private static final String LOG_FILE_PATH = "log.txt";

    @Autowired
    private TransactionContext transactionContext;

    public void log(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String transactionCode = transactionContext.getTransactionCode();
        String logMessage = String.format("%s [TransactionID: %s] %s%n", timestamp, transactionCode, message);

        try (FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print(logMessage);
            System.out.println("System started succesfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}