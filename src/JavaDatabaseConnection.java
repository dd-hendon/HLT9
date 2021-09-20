import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JavaDatabaseConnection {

    public static void main(String[] args) {

        // Assign connection strings to variables
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sakila";
        String userName = "root";
        String password = "2St^%s*AHV5hM7";

        try {
            // Create database connection
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();


            // Insert 5 records
            statement.executeUpdate("INSERT INTO employee VALUES " +
                    "(1, 'Daniel Hendon', 'ddhendon@bluebook.com', " +
                    "07448669952, 'Junior Developer', 50000)");

            statement.executeUpdate("INSERT INTO employee VALUES " +
                    "(2, 'Caleb Smith', 'CSmith@bluebook.com', " +
                    "07359776254, 'Technical Lead', 120000)");

            statement.executeUpdate("INSERT INTO employee VALUES " +
                    "(3, 'Nathan Bateman', 'NBateman@bluebook.com', " +
                    "07995277721, 'CEO', 2000000)");

            statement.executeUpdate("INSERT INTO employee VALUES " +
                    "(4, 'Ava', 'MKVII@bluebook.com', " +
                    "00100001101, 'Android', 0)");

            statement.executeUpdate("INSERT INTO employee VALUES " +
                    "(5, 'Kyoko', 'MKIV@bluebook.com', " +
                    "00101100100, 'Android', 0)");


            // Read all records and print to console

            System.out.println("BLUEBOOK DATABASE QUERY 2014\n");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                System.out.println("Employee ID: " + resultSet.getInt(1));
                System.out.println("Employee Name: " + resultSet.getString(2));
                System.out.println("Employee Email: " + resultSet.getString(3));
                System.out.println("Employee Mobile: " + resultSet.getString(4));
                System.out.println("Employee Designation: " + resultSet.getString(5));
                System.out.println("Employee Salary: " + resultSet.getDouble(6));
                System.out.println();
            }

            System.out.println();


            // Update record 3, retiring the CEO
            statement.executeUpdate("UPDATE employee SET employee_designation = 'Retired', employee_salary = 0 " +
                    "WHERE employee_name = 'Nathan Bateman' AND employee_id = 3;");


            // Delete records for Caleb and Ava
            statement.executeUpdate("DELETE FROM employee WHERE employee_id = 2");
            statement.executeUpdate("DELETE FROM employee WHERE employee_id = 4");


            // Read all records again and print to show change
            System.out.println("BLUEBOOK DATABASE QUERY 2015\n");
            resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                System.out.println("Employee ID: " + resultSet.getInt(1));
                System.out.println("Employee Name: " + resultSet.getString(2));
                System.out.println("Employee Email: " + resultSet.getString(3));
                System.out.println("Employee Mobile: " + resultSet.getString(4));
                System.out.println("Employee Designation: " + resultSet.getString(5));
                System.out.println("Employee Salary: " + resultSet.getDouble(6));
                System.out.println();
            }

            System.out.println();


            connection.close();
        }

        catch (Exception exception) {
            System.out.println("Database error: " + exception);
        }

    }
}
