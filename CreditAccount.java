public class CreditAccount extends Account implements Transferable
{

    private int creditLimit = -300;
    private final double interestRate = 0.004;

    public CreditAccount(String name, double balance){
        super(name, balance);
    }

    public void withdraw(double amount) {
        if ((balance - amount) >= creditLimit) {
        balance -= amount;
        }
    }
    
    public void deposit(double amount) {
        if ((balance + amount) <= 0) {
            balance += amount;
        } else {
            System.out.println("Cannot have money in Credit Account");
        }
    }

    public boolean transferTo(Account target, double amount){
        if ((amount > 0) && ((balance - amount >= creditLimit))) {
            balance -= amount;
            target.balance += amount;
            return true;
        } else {
            System.out.println("Unable to transfer funds.");
            return false;
        }
    }

    public String getAccountType(){
        return "Credit Account";
    }

    public void applyMonthly(){
        if (balance <= -200) {
            balance += balance * interestRate; 
        }
    }

    public String getFormattedCredit(){
        return Utils.formatMoney(balance);
    }

   

}