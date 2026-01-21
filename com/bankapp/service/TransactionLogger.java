package com.bankapp.service;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {
    private static final String DIR = "C:\\Users\\mapon\\Downloads\\demo\\Banking\\src\\history\\";


    public static void log(String accNo, String message) {
        new File(DIR).mkdirs();
        System.out.println(
                "Logging to: " + new File(DIR + accNo + ".txt").getAbsolutePath()
        );
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(new FileWriter(DIR + accNo + ".txt", true)))) {

            String time = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            pw.println("[" + time + "] " + message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
