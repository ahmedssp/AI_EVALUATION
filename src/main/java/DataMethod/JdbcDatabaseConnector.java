package DataMethod;

import java.sql.*;
import java.util.Arrays;

public class JdbcDatabaseConnector {


    public static Object[][] readDatabase(String query, String url, String user, String password)
            throws SQLException, RuntimeException {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet data = st.executeQuery(query)) {

            data.last();
            int numRows = data.getRow();
            data.beforeFirst();

            ResultSetMetaData meta = data.getMetaData();
            int numColumns = meta.getColumnCount();

            Object[][] arr = new Object[numRows][numColumns];
            int row = 0;

            while (data.next()) {
                for (int col = 0; col < numColumns; col++) {
                    arr[row][col] = data.getString(col + 1);
                }
                row++;
            }
            return arr;
        }
    }

    public static void printQueryResult(String query, String url, String user, String password) {
        try {
            Object[][] result = readDatabase(query, url, user, password);

            // Print the result for testing
            for (Object[] row : result) {
                for (Object cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void insertData3(String query, String url, String user, String password, int model_id, String model_type, int role_id) {
        try (
                // Establish the database connection
                Connection connection = DriverManager.getConnection(url, user, password);

                // Create a prepared statement with the SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the values for the prepared statement
            preparedStatement.setInt(1, model_id);
            preparedStatement.setString(2, model_type);
            preparedStatement.setInt(3, role_id);


            // Execute the query to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertData(String query, String url, String user, String password, String firstName, String email, String passwordval) {
        try (
                // Establish the database connection
                Connection connection = DriverManager.getConnection(url, user, password);

                // Create a prepared statement with the SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the values for the prepared statement
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, passwordval);


            // Execute the query to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertData4(String query, String url, String user, String password, String firstName, String email, String passwordval , int active) {
        try (
                // Establish the database connection
                Connection connection = DriverManager.getConnection(url, user, password);

                // Create a prepared statement with the SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the values for the prepared statement
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, passwordval);
            preparedStatement.setInt(4, active);


            // Execute the query to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertData8(String query_m, String url_m, String user_m, String password_m, String firstName, String email, String passwordval , int active,int department_id,int company_id,int user_id,String guarde_type) {
        try (
                // Establish the database connection
                Connection connection = DriverManager.getConnection(url_m, user_m, password_m);

                // Create a prepared statement with the SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(query_m)
        ) {
            // Set the values for the prepared statement
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, passwordval);
            preparedStatement.setInt(4, active);
            preparedStatement.setInt(5, department_id);
            preparedStatement.setInt(6, company_id);
            preparedStatement.setInt(7, user_id);
            preparedStatement.setString(8, guarde_type);





            // Execute the query to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataValue(String query, String url, String user, String password, Object... values) {
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        )
        {
            // Set values for the prepared statement
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            // Execute the query to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteData(String query, String url, String user, String password, String email) {
        try (
                // Establish the database connection
                Connection connection = DriverManager.getConnection(url, user, password);

                // Create a prepared statement with the SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the value for the prepared statement
            preparedStatement.setString(1, email);

            // Execute the query to delete data
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("No data deleted. Email not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int readUserId(String query, String url, String user, String password, String userEmail) {
        int userId = -1; // Default value indicating failure

        try (
                // Establish the database connection
                Connection connection = DriverManager.getConnection(url, user, password);

                // Create a prepared statement with the SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the email parameter in the prepared statement
            preparedStatement.setString(1, userEmail);

            // Execute the query to retrieve the user id
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set has any rows
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
    public static String get_sql(String query ){
        String url_connect = "jdbc:mysql://localhost:3306/visitor_new";
        String user_connect = "root";
        String password_connect = "Password123";
        try {
            return   Arrays.deepToString(JdbcDatabaseConnector.readDatabase(query, url_connect, user_connect, password_connect)).replace("[","").replace("]","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws SQLException {

        String GetEmailOfRoot = "SELECT `email` FROM `users` WHERE `first_name` = 'Root'";
        String query2 = "SELECT `email` FROM `users` WHERE `first_name` = 'Gurd'";
        String query3 =  "INSERT INTO `users` (first_name, email) VALUES (newsql, sql@sql.com)";
        String url = "jdbc:mysql://localhost:3306/visitor_new";
        String user = "root";
        String password = "Password123";

        // Example usage
        String query = "INSERT INTO users (first_name, email, password) VALUES (?, ?, ?)";

        // Values to be inserted
        String firstNameval = "newsql";
        String emailval = "sql@sql.com";
        String passwordval="34534543";
     //insertData(query, url, user, password, firstNameval, emailval,passwordval);

        // Call the function to insert data

//        printQueryResult(query1, url, user, password);
        printQueryResult("SELECT `email` FROM `users` WHERE `first_name` = 'Root'", url, user, password);

//        printQueryResult("SELECT `id` FROM `users` WHERE `first_name` = 'John_x'",url, user, password);
    }
}
