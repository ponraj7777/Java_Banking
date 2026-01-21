package com.bankapp.model;


import java.time.LocalDateTime;

public class BankAccount {
    private String accountNumber;
    private String holderName;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String holderName, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(String inputPin) {
        return pin.equals(inputPin);
    }

    public void changePin(String newPin) {
        this.pin = newPin;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public String toFileString() {
        return accountNumber + "," + holderName + "," + pin + "," + balance;
    }
}
