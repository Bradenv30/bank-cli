
public class SavingsAccount extends Account {

    private static final long serialVersionUID = 1L;
    private final double interestRate = 0.004;

    public SavingsAccount(String name, double balance) {
        super(name, balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
        } else {
            System.out.println("Insufficient Funds");
        }
    }

    public void applyMonthly() {
        balance += balance * interestRate;
    }

    public String getAccountType() {
        return "Savings Account";
    }

    public double getSavings() {
        return balance;
    }

    public String getFormattedSavings() {
        return Utils.formatMoney(balance);
    }

    @Override
    public boolean transferTo(Account target, double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            target.balance += amount;
            return true;
        } else {
            System.out.println("Unable to transfer funds.");
            return false;
        }
    }

}
