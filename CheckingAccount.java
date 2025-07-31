public class CheckingAccount extends Account implements Transferable
{

    private final double overdraftLimit = -100;

    private final double overdraftFee = 20.50;



     public CheckingAccount(String name, double balance){
        super(name, balance);
    }    


    public void deposit(double amount) {
        if (amount > 0) {
        balance += amount;
        }
    }

    public void withdraw(double amount) {
        if ((balance - amount) >= overdraftLimit && amount > 0 ) {
            balance -= amount;
        }
    }

    public void applyMonthly(){
        if (balance < 0) {
            balance -= overdraftFee;
        }
    }


    public String getAccountType(){
        return "Checking Account";
    }

    public String getFormattedChecking(){
        return Utils.formatMoney(balance);
    }

    @Override
    public boolean transferTo(Account target, double amount) {
        if ((balance - amount) >= overdraftLimit && amount > 0 ) {
        balance -= amount;
        target.balance += amount;
        return true;
        } else {
            System.out.println("Unable to transfer funds.");
            return false;
        }
 }
}