
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String pin;
    private double income;
    private double currentFunds;
    private ArrayList<Account> accounts;

    public User(String name, String pin, double income) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.pin = pin;
        this.income = income;
        currentFunds = income;
        accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public String getId() {
        return id;
    }

    public double getIncome() {
        return income;
    }

    public double getCurrentFunds() {
        return currentFunds;
    }

    public void addToFunds(double amount) {
        currentFunds += amount;
    }

    public void subtractFunds(double amount) {
        currentFunds -= amount;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account getAccountType(String type) {
        for (Account acc : accounts) {
            if (acc.getAccountType().equals(type)) {
                return acc;
            }
        }
        return null;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setPin(String newPin) {
        pin = newPin;
    }

    public void setIncome(double newIncome) {
        income = newIncome;
    }

    public void setFunds(double newFunds) {
        currentFunds = newFunds;
    }

    public void addIncome(double income) {
        currentFunds += income;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountById(String id) {
        for (Account acc : accounts) {
            if (acc.getId().equals(id)) {
                return acc;
            }
        }
        return null;
    }

    public void monthly() {
        for (Account account : accounts) {
            account.applyMonthly();
        }
        addIncome(income);
    }

    public String getFormattedIncome() {
        return Utils.formatMoney(income);
    }

    public String getFormattedFunds() {
        return Utils.formatMoney(currentFunds);
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("PIN: " + pin);
        System.out.println("Income: $" + getFormattedIncome());
        System.out.println("Available Funds: $" + getFormattedFunds());
    }

}
