import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PalindromeImpl extends UnicastRemoteObject implements PalindromeInterface {
    
    public PalindromeImpl() throws RemoteException {
        super();
    }
    
    public String checkPalindrome(String input) throws RemoteException {
        if (input == null || input.isEmpty()) {
            return "Input is empty!";
        }
        
        // Remove spaces and convert to lowercase for comparison
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        
        if (cleaned.equals(reversed)) {
            return "'" + input + "' is a PALINDROME!";
        } else {
            return "'" + input + "' is NOT a palindrome.";
        }
    }
}
