import java.sql.*;
import java.util.Scanner;

//import java
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
            Scanner sc = new Scanner(System.in);
            Statement stmt= conn.createStatement();
            while(true){
                System.out.println("Enter your name : ");
                String name = sc.next();
                System.out.println("Enter your age : ");
                int age = sc.nextInt();
                System.out.println("Enter your marks : ");
                double marks = sc.nextDouble();

                System.out.println("Enter more details (y/n) ?");
                String choice = sc.next();
                String query = String.format("insert into students(name, age, marks) values('%s', %d, %f)", name, age, marks);

                stmt.addBatch(query);
                if(choice.equalsIgnoreCase("n")){
                    break;
                }
            }

//            int [] rowsAffected = stmt.executeBatch();
        }catch ( SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}