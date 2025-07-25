public class SavingsAccount extends Account implements Transferable 
{

private final double interestRate = 0.004;

    public SavingsAccount(String id, String name, double balance){
        super(id, name, balance);
    }    

    public void deposit(double amount) {
    if (amount > 0) {
    balance += amount;
    }
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0){
        balance -= amount;
        } else {
            System.out.println("Insufficient Funds");
        }
    }

    public void applyMonthly(){
        balance += balance * interestRate;
    }

    public String getAccountType(){
        return "Savings Account";
    }

    public double getSavings(){
        return balance;
    }

    @Override
    public void transferTo(Account target, double amount) {
        if (amount <= balance && amount > 0) {
        balance -= amount;
        target.balance += amount;
        } else {
            System.out.println("Unable to complete transfer");
        }
 }


}