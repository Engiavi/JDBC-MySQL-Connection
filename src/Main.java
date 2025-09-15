import java.sql.*;
import java.util.Scanner;

public class Main {
    //    url for database connection with database name
    private static final String url = "jdbc:mysql://localhost:3306/jdbcTransaction";
    private static final String username = "root";
    private static final String password = "qwerty";

    public static void main(String[] args) {
//      below code is to load and register the driver class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
        //   create connection, statement and execute query
        try {
            Connection con = DriverManager.getConnection(url, username, password);

            con.setAutoCommit(false); // disable auto-commit mode

//            creating two query one for debit and other for credit
            String debit_query = "update account set balance = balance - ? where account_number = ?";
            String credit_query = "update account set balance = balance + ? where account_number = ?";

//            likewise two statement will be created
            PreparedStatement debit_stmt = con.prepareStatement(debit_query);
            PreparedStatement credit_stmt = con.prepareStatement(credit_query);

//            input the amount to be transferred
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter amount to be transferred: ");
            double amount = scanner.nextDouble();


//           enter the parameter(?) value for debit operation for account 101
            debit_stmt.setDouble(1, amount);
            debit_stmt.setInt(2, 101);

//            enter the parameter(?) value for debit operation for account 102
            credit_stmt.setDouble(1, amount);
            credit_stmt.setInt(2, 103);

            int affectedRows1 = debit_stmt.executeUpdate();
            int affectedRows2 = credit_stmt.executeUpdate();

            // check if account has sufficient balance
            if (!isSufficientBalance(con, 101,amount)) {
                con.rollback();
                System.out.println("transaction failed due to insufficient balance");
            }else{
                con.commit();
                System.out.println("Transaction successful");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    static boolean isSufficientBalance(Connection conn, int accountNumber, double amount) {
        try {
            String query = "select balance from account where account_number = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                return balance >= amount; // assuming we want to check if balance is at least
            }
        } catch (SQLException e) {
            System.out.println("Error checking balance: " + e.getMessage());
        }
        return false;
    }
}