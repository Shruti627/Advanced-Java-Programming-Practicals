import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class InetAddressDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("    InetAddress Class Demonstration");
        System.out.println("============================================");

        boolean running = true;

        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Get Local Host Information");
            System.out.println("2. Get IP Address by Hostname");
            System.out.println("3. Get Hostname by IP Address");
            System.out.println("4. Get All IP Addresses for a Hostname");
            System.out.println("5. Check if IP is Reachable");
            System.out.println("6. Demonstrate All Factory Methods");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1-7.");
                continue;
            }

            switch (choice) {
                case 1:
                    demonstrateLocalHost();
                    break;
                case 2:
                    getByHostname(scanner);
                    break;
                case 3:
                    getByIPAddress(scanner);
                    break;
                case 4:
                    getAllAddresses(scanner);
                    break;
                case 5:
                    checkReachability(scanner);
                    break;
                case 6:
                    demonstrateAllMethods();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    private static void demonstrateLocalHost() {
        System.out.println("\n--- Local Host Information ---");

        try {
            // getLocalHost() - returns InetAddress of local host
            InetAddress localHost = InetAddress.getLocalHost();

            System.out.println("Local Host Name: " + localHost.getHostName());
            System.out.println("Canonical Host Name: " + localHost.getCanonicalHostName());
            System.out.println("IP Address: " + localHost.getHostAddress());
            System.out.println("Is Loopback Address: " + localHost.isLoopbackAddress());
            System.out.println("Is Site Local Address: " + localHost.isSiteLocalAddress());
            System.out.println("Is Link Local Address: " + localHost.isLinkLocalAddress());
            System.out.println("Is Multicast Address: " + localHost.isMulticastAddress());

            // getLoopbackAddress() - returns loopback address (127.0.0.1)
            InetAddress loopback = InetAddress.getLoopbackAddress();
            System.out.println("Loopback Address: " + loopback.getHostAddress());

        } catch (UnknownHostException e) {
            System.out.println("Error: Could not determine local host information.");
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void getByHostname(Scanner scanner) {
        System.out.println("\n--- Get IP Address by Hostname ---");
        System.out.print("Enter hostname (e.g., google.com, github.com): ");
        String hostname = scanner.nextLine().trim();

        if (hostname.isEmpty()) {
            System.out.println("Hostname cannot be empty!");
            return;
        }

        try {
            // getByName() - returns InetAddress for the given hostname
            InetAddress address = InetAddress.getByName(hostname);

            System.out.println("\nResults for '" + hostname + "':");
            System.out.println("Host Name: " + address.getHostName());
            System.out.println("Canonical Host Name: " + address.getCanonicalHostName());
            System.out.println("IP Address: " + address.getHostAddress());
            System.out.println("Is Reachable (5s timeout): " + address.isReachable(5000));

        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host '" + hostname + "'");
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void getByIPAddress(Scanner scanner) {
        System.out.println("\n--- Get Hostname by IP Address ---");
        System.out.print("Enter IP address (e.g., 8.8.8.8, 142.251.16.100): ");
        String ipAddress = scanner.nextLine().trim();

        if (ipAddress.isEmpty()) {
            System.out.println("IP address cannot be empty!");
            return;
        }

        try {
            // getByName() with IP address - performs reverse lookup
            InetAddress address = InetAddress.getByName(ipAddress);

            System.out.println("\nResults for IP '" + ipAddress + "':");
            System.out.println("Host Name: " + address.getHostName());
            System.out.println("Canonical Host Name: " + address.getCanonicalHostName());
            System.out.println("IP Address: " + address.getHostAddress());
            System.out.println("Is Reachable (5s timeout): " + address.isReachable(5000));

        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown IP address '" + ipAddress + "'");
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void getAllAddresses(Scanner scanner) {
        System.out.println("\n--- Get All IP Addresses for a Hostname ---");
        System.out.print("Enter hostname (e.g., google.com, amazon.com): ");
        String hostname = scanner.nextLine().trim();

        if (hostname.isEmpty()) {
            System.out.println("Hostname cannot be empty!");
            return;
        }

        try {
            // getAllByName() - returns all IP addresses for the given hostname
            InetAddress[] allAddresses = InetAddress.getAllByName(hostname);

            System.out.println("\nAll IP addresses for '" + hostname + "':");
            for (int i = 0; i < allAddresses.length; i++) {
                InetAddress addr = allAddresses[i];
                System.out.println((i + 1) + ". " + addr.getHostAddress() +
                        " (Hostname: " + addr.getHostName() + ")");
            }

        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host '" + hostname + "'");
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkReachability(Scanner scanner) {
        System.out.println("\n--- Check Host Reachability ---");
        System.out.print("Enter hostname or IP address: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Input cannot be empty!");
            return;
        }

        try {
            InetAddress address = InetAddress.getByName(input);

            System.out.println("\nTesting reachability of: " + address.getHostAddress());
            System.out.println("Hostname: " + address.getHostName());

            // isReachable() - tests if the address is reachable
            boolean isReachable = address.isReachable(5000); // 5 second timeout

            System.out.println("Reachable: " + isReachable);
            System.out.println("Is Loopback: " + address.isLoopbackAddress());
            System.out.println("Is Site Local: " + address.isSiteLocalAddress());

        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host '" + input + "'");
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void demonstrateAllMethods() {
        System.out.println("\n--- Demonstrating All InetAddress Factory Methods ---");

        try {
            // 1. getLocalHost()
            System.out.println("\n1. getLocalHost():");
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("   " + localHost);

            // 2. getLoopbackAddress()
            System.out.println("\n2. getLoopbackAddress():");
            InetAddress loopback = InetAddress.getLoopbackAddress();
            System.out.println("   " + loopback);

            // 3. getByName() with hostname
            System.out.println("\n3. getByName() with hostname:");
            InetAddress google = InetAddress.getByName("google.com");
            System.out.println("   google.com -> " + google.getHostAddress());

            // 4. getByName() with IP address
            System.out.println("\n4. getByName() with IP address:");
            InetAddress byIP = InetAddress.getByName("8.8.8.8");
            System.out.println("   8.8.8.8 -> " + byIP.getHostName());

            // 5. getAllByName() - multiple addresses
            System.out.println("\n5. getAllByName() with google.com:");
            InetAddress[] allGoogle = InetAddress.getAllByName("google.com");
            for (int i = 0; i < Math.min(allGoogle.length, 3); i++) {
                System.out.println("   " + (i + 1) + ". " + allGoogle[i].getHostAddress());
            }
            if (allGoogle.length > 3) {
                System.out.println("   ... and " + (allGoogle.length - 3) + " more addresses");
            }

            // 6. getByAddress() with byte array
            System.out.println("\n6. getByAddress() with byte array:");
            byte[] ipBytes = {(byte) 8, (byte) 8, (byte) 8, (byte) 8};
            InetAddress byBytes = InetAddress.getByAddress(ipBytes);
            System.out.println("   byte[8,8,8,8] -> " + byBytes.getHostAddress());

            // 7. getByAddress() with hostname and byte array
            System.out.println("\n7. getByAddress() with hostname and byte array:");
            InetAddress byBytesWithName = InetAddress.getByAddress("dns.google", ipBytes);
            System.out.println("   dns.google -> " + byBytesWithName.getHostAddress());

            // Demonstrate instance methods
            System.out.println("\n--- Instance Methods Demonstration ---");
            System.out.println("Local Host Name: " + localHost.getHostName());
            System.out.println("Local Canonical Name: " + localHost.getCanonicalHostName());
            System.out.println("Local IP Address: " + localHost.getHostAddress());
            System.out.println("Local isLoopback: " + localHost.isLoopbackAddress());
            System.out.println("Google isReachable: " + google.isReachable(3000));

        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Utility method to display byte array as IP address
    private static String bytesToIp(byte[] bytes) {
        if (bytes == null) return "null";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(bytes[i] & 0xFF);
            if (i < bytes.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }
}