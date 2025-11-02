import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PalindromeServer {
    public static void main(String[] args) {
        try {
            // Create RMI registry on port 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry created on port 1099");
            
            // Create the remote object
            PalindromeImpl obj = new PalindromeImpl();
            
            // Bind the object to registry
            Naming.rebind("PalindromeService", obj);
            
            System.out.println("Palindrome Server is ready!");
            System.out.println("Waiting for client requests...");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}