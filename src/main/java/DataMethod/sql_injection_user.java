package DataMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sql_injection_user {
    public static void insertData(String query, String url, String user, String password, String first_name_f,String last_name_f,String email_f,String phone_f,String photo_f,String password_f,String gender_f,String remember_token_f,String created_at_f,String updated_at_f,String deleted_at_f,String code_f,String active_f,String domain_f,String guard_type_f,String ldap_name_f,String guid_f) {
        try (
                // Establish the database connection
                Connection connection = DriverManager.getConnection(url, user, password);

                // Create a prepared statement with the SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the values for the prepared statement
            preparedStatement.setString(1, first_name_f);
            preparedStatement.setString(2, last_name_f);
            preparedStatement.setString(3, email_f);
            preparedStatement.setString(4, phone_f);
            preparedStatement.setString(5, photo_f);
            preparedStatement.setString(6, password_f);
            preparedStatement.setString(7, gender_f);
            preparedStatement.setString(8, remember_token_f);
            preparedStatement.setString(9, created_at_f);
            preparedStatement.setString(10, updated_at_f);
            preparedStatement.setString(11, deleted_at_f);
            preparedStatement.setString(12, code_f);
            preparedStatement.setString(13, active_f);
            preparedStatement.setString(14, domain_f);
            preparedStatement.setString(15, guard_type_f);
            preparedStatement.setString(16, ldap_name_f);
            preparedStatement.setString(17, guid_f);


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

    public static void main(String[] args) {

        String GetEmailOfRoot = "SELECT `email` FROM `users` WHERE `first_name` = 'Root'";
        String query2 = "SELECT `email` FROM `users` WHERE `first_name` = 'Gurd'";
        String query3 =  "INSERT INTO `users` (first_name, email) VALUES (newsql, sql@sql.com)";
        String url = "jdbc:mysql://localhost:3306/visitor_new";
        String user = "root";
        String password = "Password123";

        // Example usage
        String query = "INSERT INTO users (first_name, email, password) VALUES (?, ?, ?)";
        String queryall = "INSERT INTO users (first_name,last_name, email,phone, photo,password,gender,remember_token,created_at,updated_at,deleted_at,code,active,domain,guard_type,ldap_name,guid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        // Values to be inserted
//        String firstName = "newsql";
//        String email = "sql@sql.com";
//        String passwordval="34534543";
//        insertData(queryall, url, user, password, firstName, email,passwordval);


        String first_name_v,last_name_v, email_v,phone_v, photo_v,password_v,gender_v,remember_token_v,created_at_v,updated_at_v,deleted_at_v,code_v,active_v,domain_v,guard_type_v,ldap_name_v,guid_v;
        first_name_v="ahmed_test";last_name_v="Mostafa_test";email_v="ahmed_test@Mostafa_test.com";phone_v=null;photo_v=null;password_v="12345678901234567890";gender_v="mall";remember_token_v=null;created_at_v=null;updated_at_v=null;deleted_at_v=null;code_v=null;active_v=null;domain_v  = null;guard_type_v=null;ldap_name_v=null;guid_v=null;

        // Call the function to insert data
        insertData(queryall, url, user, password, first_name_v,last_name_v, email_v,phone_v, photo_v,password_v,gender_v,remember_token_v,created_at_v,updated_at_v,deleted_at_v,code_v,active_v,domain_v,guard_type_v,ldap_name_v,guid_v);

//        printQueryResult(query1, url, user, password);
//        printQueryResult("SELECT * FROM `users`",
//                "jdbc:mysql://localhost:3306/visitor_new", "root", "Password123");

//        printQueryResult("SELECT * FROM `users`",url, user, password);
    }
}
