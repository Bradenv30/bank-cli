public class CheckingAccount extends Account implements Transferable
{

    private final double overdraftLimit = -100;

    private final double overdraftFee = 20.50;



     public CheckingAccount(String id, String name, double balance){
        super(id, name, balance);
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

    @Override
    public void transferTo(Account target, double amount) {
        if ((balance - amount) >= overdraftLimit && amount > 0 ) {
        balance -= amount;
        target.balance += amount;
        } else {
            System.out.println("Unable to complete transfer");
        }
 }
}