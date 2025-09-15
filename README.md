# Java JDBC MySQL Example

## Overview
This project demonstrates how to use JDBC (Java Database Connectivity) in Java to interact with a MySQL database. It covers JDBC basics, drivers, components, setup in IntelliJ IDEA, CRUD operations, batch processing, and transaction handling.

## What is JDBC?
JDBC is an API that enables Java applications to connect and execute queries with databases. It provides methods for querying and updating data, as well as managing database connections.

## JDBC Drivers
A JDBC driver is a software component that enables Java applications to communicate with a specific database. For MySQL, use the `mysql-connector-java` driver.

## Components of JDBC
- **DriverManager**: Manages a list of database drivers.
- **Connection**: Establishes a session with the database.
- **Statement/PreparedStatement**: Executes SQL queries.
- **ResultSet**: Represents the result of a query.

## Setting up JDBC in IntelliJ and Connecting to MySQL
1. Add `mysql-connector-java` to your project dependencies (via Maven, Gradle, or manually).
2. Configure your database connection URL, username, and password in your Java code.
3. Example connection code:
   ```java
   String url = "jdbc:mysql://localhost:3306/testmysql";
   String username = "root";
   String password = "your_password";
   Connection conn = DriverManager.getConnection(url, username, password);
   ```

## JDBC Boilerplate
Typical steps for JDBC operations:
1. Load the driver: `Class.forName("com.mysql.cj.jdbc.Driver");`
2. Establish connection: `DriverManager.getConnection(...)`
3. Create statement: `Statement stmt = conn.createStatement();`
4. Execute query: `ResultSet rs = stmt.executeQuery("SELECT ...");`
5. Process results
6. Close resources

## CRUD Operations Using Statement and PreparedStatement
- **Create**:
  ```java
  String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
  PreparedStatement ps = conn.prepareStatement(sql);
  ps.setString(1, "John");
  ps.setInt(2, 20);
  ps.executeUpdate();
  ```
- **Read**:
  ```java
  ResultSet rs = stmt.executeQuery("SELECT * FROM students");
  while (rs.next()) {
      // process results
  }
  ```
- **Update**:
  ```java
  String sql = "UPDATE students SET age = ? WHERE id = ?";
  PreparedStatement ps = conn.prepareStatement(sql);
  ps.setInt(1, 21);
  ps.setInt(2, 1);
  ps.executeUpdate();
  ```
- **Delete**:
  ```java
  String sql = "DELETE FROM students WHERE id = ?";
  PreparedStatement ps = conn.prepareStatement(sql);
  ps.setInt(1, 1);
  ps.executeUpdate();
  ```

## Batch Processing
Batch processing allows executing multiple SQL statements in one go for efficiency.
```java
PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, age) VALUES (?, ?)");
ps.setString(1, "Alice"); ps.setInt(2, 22); ps.addBatch();
ps.setString(1, "Bob"); ps.setInt(2, 23); ps.addBatch();
ps.executeBatch();
```

## Transaction Handling (commit, rollback, auto_commit)
- By default, JDBC uses auto-commit mode (each statement is committed immediately).
- To manage transactions manually:
  ```java
  conn.setAutoCommit(false); // disable auto-commit
  try {
      // execute multiple statements
      conn.commit(); // commit if all succeed
  } catch (SQLException e) {
      conn.rollback(); // rollback on error
  }
  conn.setAutoCommit(true); // restore auto-commit
  ```

## License
This project is provided for educational purposes.

