import java.sql.*;

public class SimpleJDBC {

    public static void main(String[] args) {

        Connection conn = null;

        try {
            // STEP 1: Load the JDBC Driver
            System.out.println("Step 1: Loading JDBC Driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver loaded successfully!\n");

            // STEP 2: Establish Connection to Database
            System.out.println("Step 2: Connecting to database...");
            conn = DriverManager.getConnection("jdbc:sqlite:mydata.db");
            System.out.println("Connected successfully!\n");

            // STEP 3: Create Table
            System.out.println("Step 3: Creating table...");
            Statement stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "salary REAL)";
            stmt.execute(createTableSQL);
            System.out.println("Table created!\n");

            // STEP 4: Insert Data
            System.out.println("Step 4: Inserting data...");
            String insertSQL = "INSERT INTO employees(name, salary) VALUES(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            // Insert multiple employees
            String[] names = {"Shruti", "Siddhi", "Manya", "Samruddhi", "Gauri"};
            double[] salaries = {75000.00, 72000.00, 68000.00, 71000.00, 73000.00};

            for (int i = 0; i < names.length; i++) {
                pstmt.setString(1, names[i]);
                pstmt.setDouble(2, salaries[i]);
                pstmt.executeUpdate();
                System.out.println("Inserted: " + names[i]);
            }

            pstmt.close();
            System.out.println();

            // STEP 5: Retrieve Data
            System.out.println("Step 5: Retrieving all data...");
            System.out.println("=====================================");
            System.out.println("ID\tName\t\tSalary");
            System.out.println("=====================================");

            String selectSQL = "SELECT * FROM employees";
            ResultSet rs = stmt.executeQuery(selectSQL);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                System.out.println(id + "\t" + name + "\t\t" + salary);
            }

            System.out.println("=====================================\n");

            rs.close();
            stmt.close();

            // STEP 6: Close Connection
            System.out.println("Step 6: Closing connection...");
            conn.close();
            System.out.println("Connection closed. Program finished!");

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: JDBC Driver not found!");
            System.out.println("Make sure sqlite-jdbc jar is in classpath.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("ERROR: Database error!");
            e.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
