package utilities.filereading.files.csv;

/*
   Note: CsvJdbc accepts only SQL SELECT queries from a single table and does not support INSERT, UPDATE, DELETE or CREATE statements.
   SQL sub-queries are permitted but joins between tables in SQL SELECT queries are not yet supported.
*/

import org.relique.jdbc.csv.CsvConnection;
import org.relique.jdbc.csv.CsvResultSet;
import org.relique.jdbc.csv.CsvResultSetMetaData;
import org.relique.jdbc.csv.CsvStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CSVRead {

    public CSVRead() {
        try {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public Object[][] getDataFromCSVFile(String csvFileDirectory, String csvFileName) throws SQLException {
        CsvConnection csvConnection = (CsvConnection) DriverManager.getConnection("jdbc:relique:csv:" + csvFileDirectory);
        CsvStatement csvStatement = (CsvStatement) csvConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        CsvResultSet csvResultSet = (CsvResultSet) csvStatement.executeQuery("select * from " + csvFileName + " where RunToTest='Y'");

        csvResultSet.last();
        int totalRecordCount = csvResultSet.getRow();
        csvResultSet.beforeFirst();

        CsvResultSetMetaData csvResultSetMetaData = (CsvResultSetMetaData) csvResultSet.getMetaData();
        int totalColumnCount = csvResultSetMetaData.getColumnCount();

        ArrayList<String> columnNameList = new ArrayList<>();
        for (int i = 1; i <= totalColumnCount; i++) {
            columnNameList.add(csvResultSetMetaData.getColumnName(i));
        }

        Object csvData[][] = new Object[totalRecordCount][1];
        Map<String, String> csvHashtable = new Hashtable<>();
        int rowIndex = 0;
        while (csvResultSet.next()) {
            for (String columnName : columnNameList) {
                if (columnName.equalsIgnoreCase("RunToTest")){
                    continue;
                }
                csvHashtable.put(columnName, csvResultSet.getString(columnName));
            }
            csvData[rowIndex][0] = csvHashtable;
            rowIndex++;
        }

        /*csvResultSet.next();
        for (int i = 0; i < totalRecordCount; i++) {
            for (int j = 2; j <= totalColumnCount; j++) {
                System.out.println("Column No. : " + j + ", Column Name " + csvResultSetMetaData.getColumnName(j) + " and data present in it is : " + csvResultSet.getString(j));
                csvData[i][j - 2] = csvResultSet.getString(j);
            }
            csvResultSet.next();
        }*/

        csvResultSet.close();
        csvConnection.close();

        return csvData;
    }

    public void printData(String csvFileDirectory, String csvFileName) {
        try {
            CsvConnection csvConnection = (CsvConnection) DriverManager.getConnection("jdbc:relique:csv:" + csvFileDirectory);
            CsvStatement csvStatement = (CsvStatement) csvConnection.createStatement();
            CsvResultSet csvResultSet = (CsvResultSet) csvStatement.executeQuery("select * from " + csvFileName + " where RunToTest='Y'");

            CsvResultSetMetaData csvResultSetMetaData = (CsvResultSetMetaData) csvResultSet.getMetaData();
            int colNumber = csvResultSetMetaData.getColumnCount();

            while (csvResultSet.next()) {
                for (int i = 1; i <= colNumber; i++) {
                    if (i > 1) {
                        System.out.print(",");
                    }
                    String columnValue = csvResultSet.getString(i);
                    System.out.print(columnValue + " ");
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
