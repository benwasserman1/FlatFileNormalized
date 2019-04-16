import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class Main {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;


        try {
            // get SQL connection
            conn = Statements.getMySqlConnection();

            CSVParser parser = new CSVParser("/Users/benjaminwasserman/IdeaProjects/Database3/src/main/java/ass3.csv", conn);

            // Run CSV parse
            parser.parseCSV();
            System.out.println("Connection successful");

            if (conn.isClosed()) {
                conn = Statements.getMySqlConnection();
            }

            /*
            st = conn.createStatement();
            rs = st.executeQuery("Select * from Table");
            */

            /*
            while (rs.next()) {
                //System.out.println("Id is: " + rs.getString(columnindex: 1));
            }
            */
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


    }
}
