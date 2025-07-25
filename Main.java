public class Main
{

    public static void main(String[] args) {
        Bank bank = new Bank();
        BankPortal portal = new BankPortal(bank);
        portal.run(); 
    }
}