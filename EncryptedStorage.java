
import java.io.*;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptedStorage {

    private static final String KEY_FILE = "secret.key";
    private static final String DATA_FILE = "data.enc";
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public static void saveKey(SecretKey key) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(KEY_FILE)) {
            fos.write(key.getEncoded());
        }
    }

    public static SecretKey loadKey() throws IOException {
        File keyFile = new File(KEY_FILE);
        if (!keyFile.exists()) {
            return null;
        }
        byte[] keyBytes = new byte[(int) keyFile.length()];
        try (FileInputStream fis = new FileInputStream(KEY_FILE)) {
            fis.read(keyBytes);
        }
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static void saveData(ArrayList<User> users, SecretKey key) {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(users);
            oos.close();
            byte[] serializedBytes = baos.toByteArray();

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(serializedBytes);

            try (FileOutputStream fos = new FileOutputStream(DATA_FILE)) {
                fos.write(encryptedBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<User> loadData(SecretKey key) {
        try {
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                return null;
            }

            byte[] encryptedBytes;
            try (FileInputStream fis = new FileInputStream(file)) {
                encryptedBytes = fis.readAllBytes();
            }

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            ByteArrayInputStream bais = new ByteArrayInputStream(decryptedBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            ois.close();

            return (ArrayList<User>) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
