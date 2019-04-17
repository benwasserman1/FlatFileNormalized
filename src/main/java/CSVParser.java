import org.apache.commons.csv.*;

import java.sql.*;
import java.io.Reader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVParser {

    private String _fileName;
    private Connection _con;

    // default constructor
    public CSVParser(){
        _fileName = null;
        _con = null;
    }

    // overloaded constructor
    public CSVParser(String fileName, Connection con){
        _fileName = fileName;
        _con = con;
    }

    // getter
    public String getFileName(){
        return this._fileName;
    }

    // setter
    public void setFileName(String fileName) {
        this._fileName = fileName;
    }

    // function to parse the CSV and call functions to insert into the tables
    public void parseCSV() {
        try{

            Reader in = new FileReader(getFileName());

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

            // instantiate sql class
            Sql query = new Sql(_con);

            // get the attributes for each record
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

                // insert into people table
                int p_id = query.PeopleInsert(Name, Email, PhoneNumber);

                // insert into company table
                int c_id = query.CompanyInsert(Company, CompanyAddress, CompanyCountry, CompanyCity, CompanyZip);

                // insert into jobs table
                query.JobsInsert(Job, c_id);

                // insert into purchases table
                query.PurchasesInsert(Amount, Currency, Time, p_id, c_id);

                // insert into personalinformation table
                query.PersonalInformationInsert(Address, Country, City, Zip, SSN, p_id);

            }
        }

        catch(Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }
}
