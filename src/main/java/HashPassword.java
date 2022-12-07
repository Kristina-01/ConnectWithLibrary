import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public  class HashPassword {
    //PBKDF2
    static char [] password;
    //передаем пароль
    public HashPassword(String password){
        this.password= password.toCharArray();
    }



    static SecureRandom secureRandom = new SecureRandom();
    //Рандомная генерация соли
    static String salt = "4123890";

    static int iteractions = 10000;
    static int keyLenght = 128;

    public  byte[] hashPassword(){
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt.getBytes(), iteractions, keyLenght);
            SecretKey key = skf.generateSecret(spec);
            byte [] res = key.getEncoded();
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
