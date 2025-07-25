import java.util.ArrayList;

public class User {

 private String id = "1";
 private String name;
 private String pin;
 private double income;
 private double currentFunds;
 private ArrayList<Account> accounts;

public User(String name, String pin, double income){  
    this.name = name;
    this.pin = pin;
    this.income = income;
    currentFunds = income;
    accounts = new ArrayList<>();
}


public String getName(){
    return name;
} 

public String getPin(){
    return pin;
}

public String getId(){
    return id;
}

public double getIncome(){
    return income;
}

public double getCurrentFunds() {
    return currentFunds;
}

public ArrayList<Account> getAccounts(){
    return accounts;
}

public Account getAccountType(String type){
    for (Account acc : accounts){
        if (acc.getAccountType().equals(type)){
            return acc;
        }
    }
    return null; 
}

public void setName(String newName){
    name = newName;
}

public void setPin(String newPin){
    pin = newPin;
}

public void setIncome(double newIncome){
    income = newIncome;
}

public void setFunds(double newFunds){
    currentFunds = newFunds;
}

public void addIncome(double income){
    currentFunds += income;
}

public void addAccount(Account account){
    accounts.add(account);
}

// public void closeAccount(Account account){
//     accounts.remove(account);
// }

public void monthly(){
    for (Account account : accounts){
        account.applyMonthly();
    }
    addIncome(income);
}

public void printInfo() {
    System.out.println("Name: " + name);
    System.out.println("PIN: " + pin);
    System.out.println("Income: $" + String.format("%.2f", income));
}


}