import java.util.Scanner;
class EnhancedProductCipher {
    // Enhanced substitution cipher that preserves case and non-alphabetic characters
    public static String substitutionEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char encrypted = (char) (((c - 'A' + shift) % 26) + 'A');
                result.append(encrypted);
            } else if (Character.isLowerCase(c)) {
                char encrypted = (char) (((c - 'a' + shift) % 26) + 'a');
                result.append(encrypted);
            } else {
                // Leave non-alphabetic characters unchanged
                result.append(c);
            }
        }
        return result.toString();
    }
    public static String substitutionDecrypt(String text, int shift) {
        return substitutionEncrypt(text, 26 - (shift % 26));
    }
    // Enhanced transposition cipher that preserves all characters
    public static String transpositionEncrypt(String text, int columns) {
        if (columns <= 0) {
            throw new IllegalArgumentException("Columns must be positive");
        }
        // Calculate rows needed
        int length = text.length();
        int rows = (int) Math.ceil((double) length / columns);
        // Pad with placeholder if needed (we'll remove later)
        StringBuilder paddedText = new StringBuilder(text);
        while (paddedText.length() < rows * columns) {
            paddedText.append(' ');
        }
        StringBuilder result = new StringBuilder();
        // Read by columns
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                int index = row * columns + col;
                if (index < length) {
                    result.append(paddedText.charAt(index));
                }
            }
        }


        return result.toString().trim(); // Remove any padding spaces
    }
    public static String transpositionDecrypt(String text, int columns) {
        if (columns <= 0) {
            throw new IllegalArgumentException("Columns must be positive");
        }
        int length = text.length();
        int rows = (int) Math.ceil((double) length / columns);
        StringBuilder result = new StringBuilder();
        // Reconstruct the original matrix
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int index = col * rows + row;
                if (index < length) {
                    result.append(text.charAt(index));
                }
            }
        }
        return result.toString();
    }




    public static void main(String[] args) {
        System.out.println("Enhanced Product Cipher Implementation by Kshitij Nangare D15B 52");
        System.out.println("=====================================");
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter text to encrypt:");
            String plaintext = scanner.nextLine();
            System.out.println("Enter substitution shift value (0-25):");
            int shift = scanner.nextInt();
            System.out.println("Enter transposition columns:");
            int columns = scanner.nextInt();
            // Validate inputs
            if (shift < 0 || shift > 25) {
                System.out.println("Shift must be between 0 and 25");
                return;
            }
            if (columns <= 0) {
                System.out.println("Columns must be positive");
                return;
            }


            // Encryption
            System.out.println("\n=== Encryption Process ===");
            String subEncrypted = substitutionEncrypt(plaintext, shift);
            System.out.println("After substitution: " + subEncrypted);


            String finalEncrypted = transpositionEncrypt(subEncrypted, columns);
            System.out.println("After transposition: " + finalEncrypted);


            // Decryption
            System.out.println("\n=== Decryption Process ===");
            String transDecrypted = transpositionDecrypt(finalEncrypted, columns);
            System.out.println("After transposition decryption: " + transDecrypted);


            String finalDecrypted = substitutionDecrypt(transDecrypted, shift);
            System.out.println("After substitution decryption: " + finalDecrypted);


            System.out.println("\nOriginal text: " + plaintext);
            System.out.println("Decrypted text: " + finalDecrypted);


            if (plaintext.equals(finalDecrypted)) {
                System.out.println("\nSuccess: Decrypted text matches original!");
            } else {
                System.out.println("\nWarning: Decrypted text doesn't match original!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
