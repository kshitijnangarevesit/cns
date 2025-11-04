import java.util.Scanner;
class VigenereDecrypt {
   private static String vigenereDecrypt(String ciphertext, String keyword) { StringBuilder plaintext = new StringBuilder();
       keyword = keyword.toUpperCase();


       for (int i = 0; i < ciphertext.length(); i++) { char ciphertextChar = ciphertext.charAt(i);
           char keywordChar = keyword.charAt(i % keyword.length());


           if (Character.isLetter(ciphertextChar)) { int shift = keywordChar - 'A';
               char decryptedChar = (char) ('A' + (ciphertextChar - 'A' - shift + 26) % 26); plaintext.append(decryptedChar);
           } else {
               plaintext.append(ciphertextChar);
           }
       }


       return plaintext.toString();
   }


   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);


       System.out.print("Enter the ciphertext: "); String ciphertext = scanner.nextLine();


       System.out.print("Enter the keyword: "); String keyword = scanner.nextLine();


       String plaintext = vigenereDecrypt(ciphertext, keyword); System.out.println("Decrypted plaintext: " + plaintext);


       scanner.close();
   }
}
