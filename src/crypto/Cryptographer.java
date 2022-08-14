package crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptographer {
    // Constants
    final private int KEY_LENGTH = 16;
    final private int IV_LENGTH = 16;
    final private int SALT_LENGTH = 16;
    final private int BITS_PER_BYTE = 8;
    final private int MATERIALS_LENGTH = SALT_LENGTH + IV_LENGTH;
    
    // Fields
    public boolean encrypt;
    public byte[] text;    
    public String password;
    
    private byte[] salt;
    private SecretKey key;
    private IvParameterSpec iv;
    private byte[] encodedMaterials = new byte[MATERIALS_LENGTH];
    private byte[] output;
    
    public void initialize() {
        if (this.encrypt) {
            this.salt = generateSalt();
            this.iv = generateIv();
            this.encodeMaterials();
        } else {
            this.parseMaterials();
        }
        
        try {
            this.key = deriveKey(password, salt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    // The following three methods generate the secrets fro the encryption
    private IvParameterSpec generateIv() {
        byte[] iv = new byte[IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    private SecretKey deriveKey(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final String DERIVE_ALGORITHM = "PBKDF2WithHmacSHA256";
        final String CIPHER_ALGORITHM = "AES";
        final int ITERATION_COUNT = 1024;

        KeySpec specs = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH * BITS_PER_BYTE);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DERIVE_ALGORITHM);
        SecretKey key = keyFactory.generateSecret(specs);

        return new SecretKeySpec(key.getEncoded(), CIPHER_ALGORITHM);
    }

    // Parse retrieves the required secrets from the beginning of the ciphertext.
    private void parseMaterials() {
        this.encodedMaterials = new byte[MATERIALS_LENGTH];

        this.encodedMaterials = Arrays.copyOf(this.text, MATERIALS_LENGTH);

        this.salt = Arrays.copyOfRange(this.encodedMaterials, 0, SALT_LENGTH);

        this.iv = new IvParameterSpec(Arrays.copyOfRange(this.encodedMaterials, SALT_LENGTH, MATERIALS_LENGTH));
    }

    // Encode stores the required secrets into the beginning of the ciphertext.
    private void encodeMaterials() {
        System.arraycopy(this.salt, 0, this.encodedMaterials, 0, SALT_LENGTH);
        System.arraycopy(this.iv.getIV(), 0, this.encodedMaterials, SALT_LENGTH, IV_LENGTH );
    }

    /* Processes the file either encrypting or decrypting it depending on the way the cryptographer object
    was constructed.*/
    public void processCipher() {
        final String ALGORITHM = "AES/CBC/PKCS5Padding";

        try {
            byte[] buffer;
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            
            if (this.encrypt) {
                cipher.init(Cipher.ENCRYPT_MODE, this.key, this.iv);
                buffer = cipher.doFinal(this.text);
                this.output = new byte[MATERIALS_LENGTH + buffer.length];
                
                // Store encoded salt and iv, together with the cipher into the output array.
                System.arraycopy(this.encodedMaterials, 0, this.output, 0, MATERIALS_LENGTH);
                System.arraycopy(buffer, 0, this.output, MATERIALS_LENGTH, buffer.length);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, this.key, this.iv);
                buffer = Arrays.copyOfRange(this.text, MATERIALS_LENGTH, text.length);
                this.output = cipher.doFinal(buffer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    // Getter for the output field.
    public byte[] getOutput() {
        return this.output;
    }
}
