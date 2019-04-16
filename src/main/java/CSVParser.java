import org.apache.commons.csv.*;

import java.sql.*;
import java.io.Reader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVParser {

    private String _fileName;
    private Connection _con;

    public CSVParser(){
        _fileName = null;
        _con = null;
    }

    public CSVParser(String fileName, Connection con){
        _fileName = fileName;
        _con = con;
    }

    public String getFileName(){
        return this._fileName;
    }

    public void setFileName(String fileName) {
        this._fileName = fileName;
    }

    public void parseCSV() {
        try{

            Reader in = new FileReader(getFileName());

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

            for (CSVRecord record: records){
                String Name = record.get(0);
                String Email = record.get(1);
                String Address = record.get(2);
                String Country = record.get(3);
                String City = record.get(4);
                String Zip = record.get(5);
                String SSN = record.get(6);
                String Job = record.get(7);
                String Company = record.get(8);
                String CompanyAddress = record.get(9);
                String CompanyCountry = record.get(10);
                String CompanyCity = record.get(11);
                String CompanyZip = record.get(12);
                String Time = record.get(13);
                String PhoneNumber = record.get(14);
                String Currency = record.get(15);
                String Amount = record.get(16);

                // Insert into people table
                PreparedStatement st = _con.prepareStatement("INSERT INTO People(Name, Email, PhoneNumber)" +
                        "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                st.setString(1, Name);
                st.setString(2, Email);
                st.setString(3, PhoneNumber);
                st.executeUpdate();

                ResultSet rs_key = st.getGeneratedKeys();

                int p_id = 0;
                if (rs_key.next()) {
                    p_id = rs_key.getInt(1);
                }

                System.out.println("Made it to just before company table");


                // insert into company table
                PreparedStatement st1 = _con.prepareStatement("INSERT INTO Company(Name, Street, Country, City, ZipCode)" +
                        "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                st1.setString(1, Company);
                st1.setString(2, CompanyAddress);
                st1.setString(3, CompanyCountry);
                st1.setString(4, CompanyCity);
                st1.setString(5, CompanyZip);
                st1.executeUpdate();

                ResultSet rs1_key = st1.getGeneratedKeys();
                int c_id = 0;
                if (rs1_key.next()) {
                    c_id = rs1_key.getInt(1);
                }



                // insert into jobs table
                PreparedStatement st3 = _con.prepareStatement("INSERT INTO Jobs(Name, CompanyId)" +
                        "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);


                System.out.println(Job);
                System.out.println(c_id);

                st3.setString(1, Job);
                st3.setInt(2, c_id);
                st3.executeUpdate();


                // insert into purchases table
                PreparedStatement st2 = _con.prepareStatement("INSERT INTO Purchases(Amount, Currency, DatePurchased, PersonId, CompanyId)" +
                        "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                float f_amount = Float.parseFloat(Amount);
                SimpleDateFormat formatter1=new SimpleDateFormat("MM/dd/yy kk:mm");
                Date date1=formatter1.parse(Time);
                java.sql.Date sqlDate = new java.sql.Date(date1.getTime());


                st2.setFloat(1, f_amount);
                st2.setString(2, Currency);
                st2.setDate(3, sqlDate);
                st2.setInt(4, p_id);
                st2.setInt(5, c_id);
                st2.executeUpdate();

                // insert into personalinformation table
                PreparedStatement st4 = _con.prepareStatement("INSERT INTO PersonalInformation(Street, Country, City, ZipCode, SSN, PersonId)" +
                        "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);


                System.out.println(Address);

                st4.setString(1, Address);
                st4.setString(2, Country);
                st4.setString(3, City);
                st4.setString(4, Zip);
                st4.setString(5, SSN);
                st4.setInt(6, p_id);
                st4.executeUpdate();


            }
        }

        catch(Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }
}
