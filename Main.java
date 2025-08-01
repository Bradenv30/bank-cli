
import java.io.IOException;
import java.util.ArrayList;
import javax.crypto.SecretKey;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            SecretKey key = EncryptedStorage.loadKey();

            if (key == null) {
                key = EncryptedStorage.generateKey();
                EncryptedStorage.saveKey(key);
            }

            ArrayList<User> users = EncryptedStorage.loadData(key);

            Bank bank = new Bank();
            if (users != null) {
                bank.setUsers(users);
            }
            BankPortal portal = new BankPortal(bank);
            portal.start();

            EncryptedStorage.saveData(bank.getUsers(), key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
