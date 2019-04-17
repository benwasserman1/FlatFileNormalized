import java.sql.DriverManager;
import java.sql.Connection;

public class Statements {

    private Connection getSqlConnection;

    // function to create the sql connection
    public static Connection getMySqlConnection ()
    {
        Connection mySqlConnection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //String connection
            String connectionUrl = "jdbc:mysql://35.239.35.57:3306/assignment3";
            mySqlConnection = DriverManager.getConnection(connectionUrl, "benwass", "sample");


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return mySqlConnection;
    }

}
