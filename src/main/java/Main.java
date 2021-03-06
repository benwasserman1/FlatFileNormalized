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

            CSVParser parser = new CSVParser("src/main/java/ass3.csv", conn);

            // Run CSV parse
            parser.parseCSV();
            System.out.println("Connection successful");

            if (conn.isClosed()) {
                conn = Statements.getMySqlConnection();
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


    }
}
