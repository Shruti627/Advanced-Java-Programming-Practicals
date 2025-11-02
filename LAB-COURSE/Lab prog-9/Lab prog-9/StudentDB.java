import java.sql.*;
import java.util.Scanner;

public class StudentDB {
    
    // Database connection object
    private static Connection conn = null;
    
    // Method to connect to database
    public static void connect() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to database (creates file if not exists)
            conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            System.out.println("Connected to database!");
            
            // Create table if not exists
            createTable();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Method to create students table
    public static void createTable() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS students (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "age INTEGER," +
                        "grade TEXT)";
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
    
    // Method to add a student
    public static void addStudent(String name, int age, String grade) {
        try {
            String sql = "INSERT INTO students(name, age, grade) VALUES(?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, grade);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    
    // Method to view all students
    public static void viewAllStudents() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            
            System.out.println("\n--- All Students ---");
            System.out.println("ID\tName\t\tAge\tGrade");
            System.out.println("------------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");
                System.out.println(id + "\t" + name + "\t\t" + age + "\t" + grade);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }
    
    // Method to search student by ID
    public static void searchStudent(int id) {
        try {
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("\n--- Student Found ---");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Grade: " + rs.getString("grade"));
            } else {
                System.out.println("Student not found!");
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }
    
    // Method to delete student
    public static void deleteStudent(int id) {
        try {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            pstmt.close();
            
            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
    
    // Method to close connection
    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Connect to database
        connect();
        
        // Menu loop
        while (true) {
            System.out.println("\n=== Student Database ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();
                    addStudent(name, age, grade);
                    break;
                    
                case 2:
                    viewAllStudents();
                    break;
                    
                case 3:
                    System.out.print("Enter student ID: ");
                    int searchId = sc.nextInt();
                    searchStudent(searchId);
                    break;
                    
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteStudent(deleteId);
                    break;
                    
                case 5:
                    closeConnection();
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}