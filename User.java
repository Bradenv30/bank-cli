import java.util.ArrayList;

public class User {

 private String name;
 private String pin;
 private ArrayList<Account> accounts;

public User(String name, String pin){  
    this.name = name;
    this.pin = pin;
    accounts = new ArrayList<>();
}


public String getName(){
    return name;
} 

public String getPin(){
    return pin;
}

public ArrayList<Account> getAccounts(){
    return accounts;
}

public void setName(String newName){
    name = newName;
}

public void setPin(String newPin){
    pin = newPin;
}

public void addAccount(Account account){
    accounts.add(account);
}
// public void removeAccount(){}


}