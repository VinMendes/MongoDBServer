package mongodbServer;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

public class AESCrypto {

    static {
        // Registra o provedor Bouncy Castle
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String encrypt(String plaintext, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM, "BC");  // BC para Bouncy Castle
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM, "BC");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String key = "SixteenByteKey!!";  // A chave AES deve ter 16, 24 ou 32 bytes
        String plaintext = "Esta é uma mensagem secreta.";

        // Criptografar
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Criptografado: " + encryptedText);

        // Descriptografar
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Descriptografado: " + decryptedText);
    }
}
