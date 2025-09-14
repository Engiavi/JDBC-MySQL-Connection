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
            Statement stmt  = conn.createStatement();
//            for selecting the row from table
//            String query = "SELECT * FROM students";

//            for inserting the row in table
//            String query = String.format("INSERT INTO students (name, age, marks) VALUES ('%s', %o, %f)", "Kri",22,88.4);

//            for updating the row in table
            String query = String.format("UPDATE students SET marks = %f WHERE id = %d", 95.5, 2);

//            ResultSet rs    = stmt.executeQuery(query); // for select and execute the query

//          if any rows are affected then it will return number of rows affected
            int rowsAffected = stmt.executeUpdate(query);
            if(rowsAffected > 0){ //if affected rows are greater than 0 then insertion is successful
                System.out.println("Insertion successful, rows affected: " + rowsAffected);
            } else { //otherwise insertion failed
                System.out.println("Insertion failed, no rows affected.");
            }

//          now printing each row of table using while loop
//            while(rs.next()){
//               int id = rs.getInt("id");
//               String name = rs.getString("name");
//               int age = rs.getInt("age");
//               double marks = rs.getDouble("marks");
//                System.out.println("id : " + id + ", name : " + name + ", age : " + age + ", marks : " + marks);
//            }
        }catch ( SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}