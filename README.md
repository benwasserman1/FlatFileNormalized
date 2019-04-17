# Flat File Normalized

## Instructions for running

### Necessary dependencies

1. Ensure that you're using Java 1.8
2. Make sure to add the following two external libraries to your project. If you're using IntelliJ IDEA, they can be added with Maven from File -> Project Structure. 
    - mysql:mysql-connector-java:6.0.5
    - org.apache.commons:commons-csv:1.6
3. Run `pip install faker` so that the data generator can be run with Python

### Start by running the code generator with Python 

1. Navigate to the /Database3/src/main/java/ directory and run main.py with Python 3. The number of records you want to produce will be taken as a command line argument. For example, to generate 500 records to test.csv, run `python main.py 500`. The name of the file will be ass3.csv.

### Next run the Java code to read in the CSV and dump it into a normalized database

1. Navigate to /Database3/src/main/java/ and run main.java. This code should upload the data to the normalized tables.  
