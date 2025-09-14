import java.sql.*;

public class Main{
//    url for database connection with database name
    private static final String url = "jdbc:mysql://localhost:3306/testmysql";
    private static final String username = "root";
    private static final String password = "qwerty";

    public static void main(String [] args){
//      below code is to load and register the driver class
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Driver not found: " + e.getMessage());
        }
        //   create connection, statement and execute query
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
//            PreparedStatement
//            String query = "Insert into students (name, age,marks) values (?,?,?)";//? is placeholder

//            update using prepared statement
//            String query = "Update students set marks = ? where name = ?";

//            Delete using prepared statement
            String query = "Delete from students where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query); // till
            // this, query is compiled and after this we set values for placeholders
//            pstmt.setString(2, "Arpita"); //1st placeholder
//            pstmt.setInt(2,22);
            pstmt.setDouble(1, 2);

            int rowsAffected = pstmt.executeUpdate(); // for insert, update, delete
            if(rowsAffected > 0){
                System.out.println("Deletion successful, rows affected: " + rowsAffected);
            }else{
                System.out.println("Deletion failed");
            }
            pstmt.close();
            conn.close();

        }catch ( SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}