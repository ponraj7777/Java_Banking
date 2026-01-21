package com.bankapp.repository;

import com.bankapp.model.BankAccount;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private static final String FILE_PATH = "src/Accounts.txt";
    private Map<String, BankAccount> accounts = new HashMap<>();

    public AccountRepository() {
        loadAccounts();
    }

    public Map<String, BankAccount> getAccounts() {
        return accounts;
    }

    public BankAccount findAccount(String accNo) {
        return accounts.get(accNo);
    }

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
        saveAll();
    }

    private void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                BankAccount account = new BankAccount(
                        data[0], data[1], data[2], Double.parseDouble(data[3])
                );
                accounts.put(data[0], account);
            }
        } catch (IOException ignored) {}
    }

    public void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (BankAccount acc : accounts.values()) {
                bw.write(acc.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
