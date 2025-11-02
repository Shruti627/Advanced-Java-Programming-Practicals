import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PalindromeInterface extends Remote {
    String checkPalindrome(String input) throws RemoteException;
}