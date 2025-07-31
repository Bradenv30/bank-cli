//BankPortal gets user input and routes to different CLI views. It acts as a controller/view.
//Only Bank has the methods to actually use the input for meaningful action, so all BankPortal can do is call Bank methods

//Only supports routing by type. In future if expanding to have multiple accounts, will need to route by ID

import java.util.ArrayList;
import java.util.Scanner;

public class BankPortal 
{
    Scanner myObj = new Scanner(System.in);
    private Bank bank;
    ArrayList<String> accountTypes = new ArrayList<>();
    ArrayList<String> possibleAccounts = new ArrayList<>(); 

    public BankPortal(Bank bank) {
        this.bank = bank;
    }


    public void start(){
        System.out.println("Welcome!");
        userMenu();
    }

    public void userMenu(){
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            String choice = myObj.nextLine();
            
            switch (choice) {
                case "1" -> login();
                case "2" -> register();
                case "0" -> System.out.println("Goodbye!");
            }
        }

        public void login(){
            System.out.print("Enter your username: ");
            String name = myObj.nextLine();
            System.out.print("Enter your PIN: ");
            String pin = myObj.nextLine();

            if (bank.loginUser(name, pin)) {
                loadAccountTypes();
                System.out.println("Login successful. Welcome back, " + name + "!");
                dashboard();
            } else {
                System.out.println("Login failed. Invalid credentials");
                userMenu();
            }
        }

        public void register(){
            System.out.print("Enter your username: ");
            String userName = myObj.nextLine();
            System.out.print("Enter your PIN code (Must be at least 4 characters): ");
            String pincode = myObj.nextLine();
            while (pincode.length() < 4) {
                System.out.println("PIN must be at least 4 characters.");
                System.out.print("Enter your pin again: ");
                pincode = myObj.nextLine();
            }
            double salary = validateInput("What is your monthly income? ");
            bank.registerUser(userName, pincode, salary);
            bank.registerChecking("Checking Account", 0);
            bank.registerSavings("Savings Account", 0);
            bank.registerCredit("Credit Account", 0);
            loadAccountTypes();
            System.out.println("Registration Successful. Welcome " + userName + "!");
            dashboard();
        }

        public void loadAccountTypes(){
            accountTypes.clear();
            accountTypes.add("Checking Account");
            accountTypes.add("Savings Account");
            accountTypes.add("Credit Account");
        }

        public void dashboard() {
            System.out.println(" ");
            System.out.println(bank.printName() + "'s Dashboard");
            System.out.println("You have $" + Utils.formatMoney(bank.getFunds()) + " to deposit into your accounts.");
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1. Savings Account");
            System.out.println("2. Checking Account");
            System.out.println("3. Credit Account");
            System.out.println("4. Advance Month");
            System.out.println("5. Edit Income (Takes effect next month)");
            System.out.println("6. View Account Details");
            System.out.println("7. Logout");
            System.out.println("0. Exit");
            

            String option = myObj.nextLine();

            switch (option) {
                case "1" -> savingsAccountPage();
                case "2" -> checkingAccountPage();
                case "3" -> creditAccountPage();
                case "4" -> advanceMonth();
                case "5" -> editIncomePage();
                case "6" -> printDetails();
                case "7" -> logout();
                case "0" -> System.out.println("Goodbye!");
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    dashboard();
                }
            }
        }

        public void savingsAccountPage(){
            System.out.println("");
            System.out.println("-- Savings Account -- ");
            System.out.println("Balance: $" + Utils.formatMoney(bank.getSavingsMoney()));
            System.out.println("");
            System.out.println("Choose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Funds to Another Account");
            System.out.println("0. Return to Dashboard");

            String option = myObj.nextLine();

            switch (option) {
                case "1" -> deposit("Savings Account", this::savingsAccountPage);
                case "2" -> withdraw("Savings Account", this::savingsAccountPage);
                case "3" -> transfer("Savings Account", this::savingsAccountPage);
                case "0" -> dashboard();
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    dashboard();
                }
            }
        }

        public void checkingAccountPage(){
            System.out.println("");
            System.out.println("-- Checking Account -- ");
            System.out.println("Balance: $" + Utils.formatMoney(bank.getCheckingMoney()));
            System.out.println("");
            System.out.println("Choose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw (Up to $-100 overdraft)");
            System.out.println("3. Transfer Funds to Another Account");
            System.out.println("0. Return to Dashboard");

            String option = myObj.nextLine();

            switch (option) {
                case "1" -> deposit("Checking Account", this::checkingAccountPage);
                case "2" -> withdraw("Checking Account", this::checkingAccountPage);
                case "3" -> transfer("Checking Account", this::checkingAccountPage);
                case "0" -> dashboard();
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    dashboard();
                }
            }
        }


        public void creditAccountPage(){
            System.out.println("");
            System.out.println("-- Credit Account -- ");
            System.out.println("Balance: $" + Utils.formatMoney(bank.getCreditMoney()));
            System.out.println("Credit Limit: $300");
            System.out.println("");
            System.out.println("Choose an action:");
            System.out.println("1. Repay Balance");
            System.out.println("2. Use Credit");
            System.out.println("3. Transfer Credit to Another Account");
            System.out.println("0. Return to Dashboard");

            String option = myObj.nextLine();
            switch (option) {
                case "1" -> deposit("Credit Account", this::creditAccountPage);
                case "2" -> withdraw("Credit Account", this::creditAccountPage);
                case "3" -> transfer("Credit Account", this::creditAccountPage);
                case "0" -> dashboard();
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    dashboard();
                }
            }
            
        }


        public void deposit(String type, Runnable page){
            double funds = validateInput("Enter the amount you would like to deposit: ");
            String id = getAccountIdByType(type);
            bank.depositFunds(id, funds);
            page.run();
        }

        public void withdraw(String type, Runnable page){
            double funds = validateInput("Enter the amount you would like to withdraw: ");
            String id = getAccountIdByType(type);
            bank.withdrawFunds(id, funds);
            page.run();

        }

        public void transfer(String current, Runnable page) {
            System.out.println("\nChoose the account you are transferring to: ");
            possibleAccounts.clear();
            int index = 1;
            for (String acc : accountTypes) {
                if (current.equals(acc)) continue;
                System.out.println(index + ". " + acc);
                possibleAccounts.add(acc);
                index++;
            }

            String destination = null;

            while (destination == null) {
                System.out.print("Enter your choice: ");
                String input = myObj.nextLine();

            try {
                int choice = Integer.parseInt(input);

                if (choice < 1 || choice > possibleAccounts.size()) {
                System.out.println("Invalid selection. Please enter a number between 1 and " + possibleAccounts.size() + ".");
                } else {
                    destination = possibleAccounts.get(choice - 1);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        double amount = validateInput("Enter the amount you would like to transfer: ");
        String fromId = getAccountIdByType(current);
        String toId = getAccountIdByType(destination);
        boolean success = bank.transferFunds(fromId, toId, amount);
        if (success){
            System.out.println("Transfer completed.");
        } else {
            System.out.println("Transfer failed. Please check your account information.");
        }
        page.run();
        
        }
        

        public void advanceMonth(){
            bank.advanceMonth();
            dashboard();
        }

        public void editIncomePage(){
            System.out.println("Your current income is " + Utils.formatMoney(bank.getIncome()) + ".");
            double newIncome = validateInput("What is your new income? ");
            bank.setIncome(newIncome);
            dashboard();

        }

        public void printDetails(){
            System.out.println("");
            System.out.println("User: " + bank.printName());
            System.out.println("Savings Account: $" + Utils.formatMoney(bank.getSavingsMoney()));
            System.out.println("Checking Account: $" + Utils.formatMoney(bank.getCheckingMoney()));
            System.out.println("Credit Account: $" + Utils.formatMoney(bank.getCreditMoney()));
            dashboard();
        }

        public double validateInput(String prompt) {
            while (true){
                System.out.print(prompt);
                String input = myObj.nextLine();
                try {
                    double value = Double.parseDouble(input);
                    if (value < 0) {
                        System.out.println("Please enter a positive number.");
                        continue;
                    }
                    return value;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }
        }

        public String getAccountIdByType(String type){
            return bank.getCurrentUser().getAccountType(type).getId();
        }


        public void logout(){
            System.out.println("Logout successful.");
            bank.logout();
            userMenu();
        }

        
    }

   


