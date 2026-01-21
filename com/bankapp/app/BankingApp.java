package com.bankapp.app;
import com.bankapp.model.BankAccount;
import com.bankapp.repository.AccountRepository;
import com.bankapp.service.BankService;

import java.util.Scanner;

public class BankingApp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AccountRepository repo = new AccountRepository();
        BankService service = new BankService(repo);

        while (true) {
            System.out.println("\n1. Create Account\n2. Login\n3. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Account No: ");
                String acc = sc.next();
                System.out.print("PIN: ");
                String pin = sc.next();
                System.out.print("Initial Deposit: ");
                double amt = sc.nextDouble();

                repo.addAccount(new BankAccount(acc, name, pin, amt));
                System.out.println("Account created successfully");

            } else if (choice == 2) {
                System.out.print("Account No: ");
                String acc = sc.next();
                System.out.print("PIN: ");
                String pin = sc.next();

                BankAccount account = repo.findAccount(acc);
                if (account == null || !account.validatePin(pin)) {
                    System.out.println("Invalid credentials");
                    continue;
                }

                userMenu(account, repo, service);
            } else {
                break;
            }
        }
    }


    private static void userMenu(BankAccount acc,
                                 AccountRepository repo,
                                 BankService service) {

        while (true) {
            System.out.println("\n1.Balance 2.Deposit 3.Withdraw 4.Transfer 5.Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Balance: ₹" + acc.getBalance());
                    break;

                case 2:
                    System.out.print("Amount: ");
                    service.deposit(acc, sc.nextDouble());
                    break;

                case 3:
                    System.out.print("Amount: ");
                    try {
                        service.withdraw(acc, sc.nextDouble());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("To Acc: ");
                    String to = sc.next();
                    System.out.print("Amount: ");
                    service.transfer(acc.getAccountNumber(), to, sc.nextDouble());
                    break;

                case 5:
                    return;
            }
        }
    }

}
