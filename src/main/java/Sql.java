import java.sql.*;
import java.io.Reader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sql {

    private Connection _con;

    // default constructor
    public Sql() {
        _con = null;
    }

    // overloaded constructor, set the connection string
    public Sql(Connection con) {
        _con = con;
    }

    // function to insert a record into the table People
    public int PeopleInsert(String Name, String Email, String PhoneNumber){
        int p_id = 0;
        try {
            PreparedStatement st = _con.prepareStatement("INSERT INTO People(Name, Email, PhoneNumber)" +
                    "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, Name);
            st.setString(2, Email);
            st.setString(3, PhoneNumber);
            st.executeUpdate();

            ResultSet rs_key = st.getGeneratedKeys();

            //int p_id = 0;
            if (rs_key.next()) {
                p_id = rs_key.getInt(1);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return p_id;

    }

    // function to insert a record into the table Company
    public int CompanyInsert(String Name, String Street, String Country, String City, String ZipCode) {

        int c_id = 0;
        try {
            PreparedStatement st1 = _con.prepareStatement("INSERT INTO Company(Name, Street, Country, City, ZipCode)" +
                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st1.setString(1, Name);
            st1.setString(2, Street);
            st1.setString(3, Country);
            st1.setString(4, City);
            st1.setString(5, ZipCode);
            st1.executeUpdate();

            ResultSet rs1_key = st1.getGeneratedKeys();
            //int c_id = 0;
            if (rs1_key.next()) {
                c_id = rs1_key.getInt(1);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return c_id;


    }

    // function to insert a record into the table Jobs
    public void JobsInsert(String Job, int c_id) {
        try {
            PreparedStatement st3 = _con.prepareStatement("INSERT INTO Jobs(Name, CompanyId)" +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);


            st3.setString(1, Job);
            st3.setInt(2, c_id);
            st3.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // function to insert a record into the table Purchases
    public void PurchasesInsert(String Amount, String Currency, String DatePurchased, int PersonId, int CompanyId) {
        try {
            PreparedStatement st2 = _con.prepareStatement("INSERT INTO Purchases(Amount, Currency, DatePurchased, PersonId, CompanyId)" +
                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            float f_amount = Float.parseFloat(Amount);
            SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            Date date1=formatter1.parse(DatePurchased);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());


            st2.setFloat(1, f_amount);
            st2.setString(2, Currency);
            st2.setDate(3, sqlDate);
            st2.setInt(4, PersonId);
            st2.setInt(5, CompanyId);
            st2.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // function to insert a record into the table Personal information
    public void PersonalInformationInsert(String Street, String Country, String City, String ZipCode, String SSN, int PersonId) {
        try {
            PreparedStatement st4 = _con.prepareStatement("INSERT INTO PersonalInformation(Street, Country, City, ZipCode, SSN, PersonId)" +
                    "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);


            st4.setString(1, Street);
            st4.setString(2, Country);
            st4.setString(3, City);
            st4.setString(4, ZipCode);
            st4.setString(5, SSN);
            st4.setInt(6, PersonId);
            st4.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
