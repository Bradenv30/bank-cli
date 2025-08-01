
import java.io.Serializable;
import java.util.UUID;

public abstract class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String id;
    protected String name;
    protected double balance;

    public Account(String name, double balance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public abstract String getAccountType();

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void applyMonthly();

    public abstract boolean transferTo(Account target, double amount);

}
