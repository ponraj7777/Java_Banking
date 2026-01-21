package com.bankapp.service;
import com.bankapp.model.BankAccount;
import com.bankapp.repository.AccountRepository;


import com.bankapp.model.BankAccount;
import com.bankapp.repository.AccountRepository;

public class BankService {
    private AccountRepository repository;

    public BankService(AccountRepository repository) {
        this.repository = repository;
    }

    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);
        repository.saveAll();
        TransactionLogger.log(
                account.getAccountNumber(),
                "Deposited ₹" + amount
        );
    }

    public void withdraw(BankAccount account, double amount) {
        account.withdraw(amount);
        repository.saveAll();
        TransactionLogger.log(
                account.getAccountNumber(),
                "Withdrew ₹" + amount
        );
    }

    public void transfer(String from, String to, double amount) {
        BankAccount sender = repository.findAccount(from);
        BankAccount receiver = repository.findAccount(to);

        if (receiver == null) {
            throw new IllegalArgumentException("Invalid recipient account");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);


        System.out.println(from +
                "Transferred ₹" + amount + " to " + receiver.getHolderName());
        TransactionLogger.log(from,
                "Transferred ₹" + amount + " to " + receiver.getHolderName());
        TransactionLogger.log(to,
                "Received ₹" + amount + " from " + sender.getHolderName());
        repository.saveAll();
    }
}


//public class BankService {
//    private AccountRepository repository;
//
//    public BankService(AccountRepository repository) {
//        this.repository = repository;
//    }
//
//    public void transfer(String from, String to, double amount) {
//        BankAccount sender = repository.findAccount(from);
//        BankAccount receiver = repository.findAccount(to);
//
//        if (receiver == null) {
//            throw new IllegalArgumentException("Invalid recipient account");
//        }
//
//        sender.withdraw(amount);
//        receiver.deposit(amount);
//
//        TransactionLogger.log(from, "Transferred ₹" + amount + " to " + receiver.getHolderName());
//        TransactionLogger.log(to, "Received ₹" + amount + " from " + sender.getHolderName());
//
//        repository.saveAll();
//    }
//}
