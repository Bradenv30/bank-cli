// Bank.java — Logic Layer

// This is the main logic layer of the app.
// - It calls constructors and methods from core classes like User and Account.
// - BankPortal (the CLI controller) will call Bank methods using user input.

// Responsibilities:
// - Acts as a bridge between the User and internal objects like Account.
// - Handles business operations like registering users, creating accounts, monthly updates, etc.
// - Encapsulates logic so BankPortal doesn't need to know how things work under the hood.

// Think of it this way:
// - BankPortal is the router/controller between the user and the Bank.
// - Bank is the logic engine between the user and the actual data/models.

//CREATE HIGH LEVEL EXPLANATION OF EACH FILE LIKE THIS IN EVERY OTHER FILE

import java.util.ArrayList;

public class Bank
{
    private User currentUser;
    public ArrayList<User> users;
    private int month = 0;


    public Bank(){
        users = new ArrayList<>();
    }
    public void registerUser(String name, String pin, double income){
        User user = new User(name, pin, income);
        users.add(user);
        currentUser = user;
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public void advanceMonth(){
       month++;
       currentUser.monthly();
    }

    public void printDetails() {
        currentUser.printInfo();
    }

    public String printName(){
        return currentUser.getName();
    }

    public double getFunds(){
        return currentUser.getCurrentFunds();
    }

    public void registerSavings(String id, String name, double balance){
        SavingsAccount savings = new SavingsAccount(id, name, balance);
        currentUser.addAccount(savings);

    }

    public void registerChecking(String id, String name, double balance){
        CheckingAccount checking = new CheckingAccount(id, name, balance);
        currentUser.addAccount(checking);
    }

    public void registerCredit(String id, String name, double balance){
        CreditAccount credit = new CreditAccount(id, name, balance);
        currentUser.addAccount(credit);
    }

    public double getSavingsMoney(){
        Account savings = currentUser.getAccountType("Savings Account");
        return savings.getBalance();
    }

    public double getCheckingMoney(){
        Account checking = currentUser.getAccountType("Checking Account");
        return checking.getBalance();
    }

    public double getCreditMoney(){
        Account credit = currentUser.getAccountType("Credit Account");
        return credit.getBalance();
    }

    public void depositFunds(String type, double amount){
        Account account = currentUser.getAccountType(type);
        account.deposit(amount);
    }

    public void withdrawFunds(String type, double amount){
        Account account = currentUser.getAccountType(type);
        account.withdraw(amount);
    }

    public void transferFunds(String from, String to, double amount){
        
        Account transferFrom = currentUser.getAccountType(from);
        Account transferTo = currentUser.getAccountType(to);

        if (transferFrom instanceof Transferable) {
            Transferable account = (Transferable) transferFrom;
            account.transferTo(transferTo, amount);
        }
    }

    //This is logic layer. Will call methods from other classes and constructors, and BankPortal will call Bank methods using user input variables.
    //Need a method to create an account, different types, or maybe the user starts with all 3. But the interplay and actual implementation/initialization of those classes goes here
    //Bank is the bridge between the user and the actual classes, BankPortal is the rotuer between the user and the Bank

}