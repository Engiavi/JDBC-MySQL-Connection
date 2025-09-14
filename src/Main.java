import java.sql.*;

public class Main{
//    url for database connection with database name
    private static final String url = "jdbc:mysql://localhost:3306/testmysql";
    private static final String username = "root";
    private static final String password = "qwerty";

    public static void main(String [] args){
//        below code is to load and register the driver class
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Driver not found: " + e.getMessage());
        }
        //        create connection, statement and execute query
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt  = conn.createStatement();
            String query = "SELECT * FROM student";
            ResultSet rs    = stmt.executeQuery(query);
        }catch ( SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}