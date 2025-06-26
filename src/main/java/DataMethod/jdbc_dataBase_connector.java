package DataMethod;

import java.sql.*;

import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class jdbc_dataBase_connector {
    /**
     * @return Two Dimintanal array
     * @throws SQLException
     * @throws RuntimeException
     */
    public static Object[][] ReadData_base(String query_input ,String URL ,String User , String Password ) throws SQLException, RuntimeException {

        Connection con = DriverManager.getConnection(URL, User, Password);
        Statement st = con.createStatement(TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
        ResultSet data = st.executeQuery(query_input);
        //get number Rows
        data.last();
        int n_rows = data.getRow();
        data.beforeFirst();

        //get number coulum
        ResultSetMetaData meta = data.getMetaData();
        int n_colums = meta.getColumnCount();

        Object arr[][] = new Object[n_rows][n_colums];
        int j = 0;
        //read data from database and store it in two Dimintional array
        while (data.next()) {
            for (int i = 0; i < n_colums; i++) {
                arr[j][i] = data.getString(i + 1);
            }
            j++;
        }
        return arr;
    }

    public static void main(String[] args) throws SQLException {
      System.out.println((ReadData_base("SELECT `email` FROM `users` WHERE `first_name` = 'Root'","jdbc:mysql://localhost:3306/visitor_new","root","Password123")));
    }



}

