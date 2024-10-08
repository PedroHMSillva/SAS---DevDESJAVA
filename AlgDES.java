import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AlgDES {
    private static SecretKey key;
    private static Cipher cipher;

    // Gera uma chave DES
    public static void generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); // Tamanho da chave DES é igual a 56 bits
        key = keyGenerator.generateKey();
    }

    // Encripta a mensagem
    public static String encrypt(String message) throws Exception {
        cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Desencripta a mensagem
    public static String decrypt(String encryptedMessage) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        // Gerar chave
        generateKey();
        
        // Mensagem original
        String originalMessage = "Oi aqui é o Pedro";
        System.out.println("Mensagem Original: " + originalMessage);
        
        // Encriptar
        String encryptedMessage = encrypt(originalMessage);
        System.out.println("Mensagem Criptografada: " + encryptedMessage);
        
        // Desencriptar
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("Mensagem Descriptografada: " + decryptedMessage);
    }
}
