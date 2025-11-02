import java.rmi.Naming;
import java.util.Scanner;

public class PalindromeClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object
            PalindromeInterface service = (PalindromeInterface) Naming.lookup("rmi://localhost/PalindromeService");
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("=== Palindrome Checker Client ===");
            System.out.println("Enter 'quit' to exit\n");
            
            while (true) {
                System.out.print("Enter a string or number: ");
                String input = sc.nextLine();
                
                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                
                // Call remote method
                String result = service.checkPalindrome(input);
                System.out.println(result + "\n");
            }
            
            sc.close();
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}