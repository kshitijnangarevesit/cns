import java.util.Scanner;
class PlayfairCipherDecrypt {
   // Generate the 5x5 key matrix from the key string
   public static char[][] generateKeyMatrix(String key) {
       key = key.toUpperCase().replace('J', 'I').replaceAll("[^A-Z]", "");
       boolean[] used = new boolean[26]; // Track used letters
       char[][] matrix = new char[5][5];
       int index = 0;
       for (char c : key.toCharArray()) {
           if (!used[c - 'A']) {
               used[c - 'A'] = true;
               matrix[index / 5][index % 5] = c;
               index++;
           }
       }
       // Fill remaining letters (excluding 'J')
       for (char c = 'A'; c <= 'Z'; c++) {
           if (c == 'J') continue; // Skip J
           if (!used[c - 'A']) {
               used[c - 'A'] = true;
               matrix[index / 5][index % 5] = c;
               index++;
           }
       }
       return matrix;
   }
   // Find the position of a character in the matrix
   public static int[] findPosition(char[][] matrix, char c) {
       for (int i = 0; i < 5; i++) {
           for (int j = 0; j < 5; j++) {
               if (matrix[i][j] == c) {
                   return new int[]{i, j};
               }
           }
       }
       return null; // Should never happen if input valid
   }
   // Decrypt the ciphertext using the Playfair cipher rules
   public static String decrypt(String ciphertext, String key) {
       ciphertext = ciphertext.toUpperCase().replace('J', 'I').replaceAll("[^A-Z]", "");
       char[][] matrix = generateKeyMatrix(key);
       StringBuilder plaintext = new StringBuilder();
       for (int i = 0; i < ciphertext.length(); i += 2) {
           char a = ciphertext.charAt(i);
           char b = ciphertext.charAt(i + 1);
           int[] posA = findPosition(matrix, a);
           int[] posB = findPosition(matrix, b);
           if (posA[0] == posB[0]) { // Same row
               plaintext.append(matrix[posA[0]][(posA[1] + 4) % 5]);
               plaintext.append(matrix[posB[0]][(posB[1] + 4) % 5]);
           } else if (posA[1] == posB[1]) { // Same column
               plaintext.append(matrix[(posA[0] + 4) % 5][posA[1]]);
               plaintext.append(matrix[(posB[0] + 4) % 5][posB[1]]);
           } else { // Rectangle swap columns
               plaintext.append(matrix[posA[0]][posB[1]]);
               plaintext.append(matrix[posB[0]][posA[1]]);
           }
       }
       return plaintext.toString();
   }
   public static void main(String[] args) {
       System.out.println("Kshitij Nangare | D15B | 52");
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter the ciphertext: ");
       String ciphertext = scanner.nextLine();
       System.out.print("Enter the key: ");
       String key = scanner.nextLine();
       // Make sure ciphertext length is even
       if (ciphertext.length() % 2 != 0) {
           System.out.println("Ciphertext length must be even.");
           return;
       }
       String decryptedText = decrypt(ciphertext, key);
       System.out.println("Decrypted Text: " + decryptedText);
       scanner.close();
   }
}
