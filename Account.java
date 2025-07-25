public abstract class Account
{
    protected String id;
    protected String name;
    protected double balance;
    

    public Account(String id, String name, double balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }    

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance;
    }   


    public abstract String getAccountType();

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void applyMonthly();

   

   
    
}