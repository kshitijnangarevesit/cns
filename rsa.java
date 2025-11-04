import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;


class RSAExample {
   private BigInteger n, d, e;
   private int bitlen = 1024; // Key size


   // Key generation
   public RSAExample(int bits) {
       bitlen = bits;
       SecureRandom r = new SecureRandom();
       BigInteger p = new BigInteger(bitlen / 2, 100, r);
       BigInteger q = new BigInteger(bitlen / 2, 100, r);
       n = p.multiply(q);
       BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
       e = new BigInteger("65537"); // Common public exponent
       d = e.modInverse(phi);
   }


   // Encryption: c = m^e mod n
   public BigInteger encrypt(BigInteger message) {
       return message.modPow(e, n);
   }


   // Decryption: m = c^d mod n
   public BigInteger decrypt(BigInteger encrypted) {
       return encrypted.modPow(d, n);
   }


   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);


       // Create RSA object with 1024-bit keys
       RSAExample rsa = new RSAExample(1024);


       // Take message from user
       System.out.print("Enter the original message: ");
       String message = sc.nextLine();


       System.out.println("Original Message: " + message);


       // Convert input string to BigInteger
       BigInteger plaintext = new BigInteger(message.getBytes());


       // Encrypt
       BigInteger ciphertext = rsa.encrypt(plaintext);
       System.out.println("Encrypted Message: " + ciphertext);


       // Decrypt
       BigInteger decrypted = rsa.decrypt(ciphertext);
       String decryptedMessage = new String(decrypted.toByteArray());
       System.out.println("Decrypted Message: " + decryptedMessage);


       sc.close();
   }
}
