//BankPortal gets user input and routes to different CLI views. It acts as a controller/view.
//Only Bank has the methods to actually use the input for meaningful action, so all BankPortal can do is call Bank methods

import java.util.Scanner;

public class BankPortal 
{
    Scanner myObj = new Scanner(System.in);
    private Bank bank;

    public BankPortal(Bank bank) {
        this.bank = bank;
    }

    public void run(){
        System.out.println("Welcome!");
        userMenu();
    }

    public void userMenu(){
            // System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            String choice = myObj.nextLine();
            
            switch (choice) {
                case "2" -> register();
                case "0" -> System.out.println("Goodbye!");
            }
        }


        public void register(){
            System.out.print("Enter your username: ");
            String userName = myObj.nextLine();
            System.out.print("Enter your 4-digit PIN code: ");
            String pincode = myObj.nextLine();
            System.out.print("What is your monthly income? ");
            String input = myObj.nextLine();
            double salary = Double.parseDouble(input);
            bank.registerUser(userName, pincode, salary);
            bank.registerChecking("0", "checking", 0);
            bank.registerSavings("0", "savings", 0);
            bank.registerCredit("0", "credit", 0);
            System.out.println("Registration Successful. Welcome " + userName + "!");
            System.out.println("");
            dashboard();
        }

        public void dashboard() {
            System.out.println("");
            System.out.println(bank.printName() + "'s Dashboard");
            System.out.println("You have $" + bank.getFunds() + " to deposit into your accounts.");
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1. Savings Account");
            System.out.println("2. Checking Account");
            System.out.println("3. Credit Account");
            System.out.println("4. Advance Month");
            System.out.println("5. Edit Income (Takes effect next month)");
            System.out.println("0. Exit");

            String option = myObj.nextLine();

            switch (option) {
                case "1" -> savingsAccountPage();
                case "2" -> checkingAccountPage();
                case "3" -> creditAccountPage();
                case "4" -> advanceMonth();
                case "5" -> editIncomePage();
                case "0" -> System.out.println("Goodbye!");
            }
        }

        public void savingsAccountPage(){
            System.out.println("");
            System.out.println("-- Savings Account == ");
            System.out.println("Balance: $" + bank.getSavingsMoney());
            System.out.println("");
            System.out.println("Choose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Funds to Another Account");
            System.out.println("0. Return to Dashboard");

            String option = myObj.nextLine();

            switch (option) {
                case "1" -> deposit("Savings Account");
                case "2" -> withdraw("Savings Account");
                case "3" -> transfer("Saving Account");
                case "0" -> dashboard();
            }
        }

        public void checkingAccountPage(){
            System.out.println("");
            System.out.println("-- Checking Account == ");
            System.out.println("Balance: $" + bank.getCheckingMoney());
            System.out.println("");
            System.out.println("Choose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw (Up to $-100 overdraft)");
            System.out.println("3. Transfer Funds to Another Account");
            System.out.println("0. Return to Dashboard");

            String option = myObj.nextLine();

            switch (option) {
                case "1" -> deposit("Checking Account");
                case "2" -> withdraw("Checking Account");
                case "3" -> transfer("Checking Account");
                case "0" -> dashboard();
            }
        }


        public void creditAccountPage(){
            System.out.println("");
            System.out.println("-- Credit Account == ");
            System.out.println("Balance: $" + bank.getCreditMoney());
            System.out.println("Credit Limit: $300");
            System.out.println("");
            System.out.println("Choose an action:");
            System.out.println("1. Repay Balance");
            System.out.println("2. Use Credit");
            System.out.println("3. Transfer Credit to Another Account");
            System.out.println("0. Return to Dashboard");

            String option = myObj.nextLine();
            switch (option) {
                case "1" -> deposit("Credit Account");
                case "2" -> withdraw("Credit Account");
                case "3" -> transfer("Credit Account");
                case "0" -> dashboard();
            }

        }


        public void deposit(String type){
            System.out.println("Enter the amount you would like to deposit: ");
            String input = myObj.nextLine();
            double funds = Double.parseDouble(input);
            bank.depositFunds(type, funds);
            //need to figure out what account it is, and call the proper method. same for the other 2. not making a method for each type
        }

        public void withdraw(String type){
            System.out.println("Enter the amount you would like to withdraw: ");
            String input = myObj.nextLine();
            double funds = Double.parseDouble(input);
            bank.withdrawFunds(type, funds);
        }

        public void transfer(String type){
            System.out.println("Choose the account you are transfering to: ");
            //extract variables from user input, where they want to transfer to and how much, call trasnfer method using them
        }



        public void advanceMonth(){
            bank.advanceMonth();
            dashboard();
        }

        public void editIncomePage(){
            //
        }
    }

   


