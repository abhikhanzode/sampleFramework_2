package utilities.filereading.files.excel;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.util.Hashtable;
import java.util.List;

public class FilloExcelRead {

    private Fillo fillo;
    private Connection connection = null;
    private String connectionString = null;

    public FilloExcelRead(String filePath) throws FilloException {
        fillo = new Fillo();

        if (connection == null) {
          connection = fillo.getConnection(filePath);
        }
    }

    public Object[][] getDataFromSheet(String sheetName) throws FilloException {

        connectionString = "select * from " + sheetName + " where RunToTest='Y'";

        Recordset recordset = connection.executeQuery(connectionString);

//        System.out.println(recordset.getCount() + " display total number of records");
//
//        ArrayList<String> columnLength = recordset.getFieldNames();
//        int columnsCount = columnLength.size();
//        System.out.println(columnsCount + " display column size...");

        Hashtable<String, String> table;
        Object[][] excelData = new Object[recordset.getCount()][1];

        int rowIndex = 0;

        while (recordset.next()) {
            table = new Hashtable<>();
            for (String strColumnName : recordset.getFieldNames()) {
                if (strColumnName.equalsIgnoreCase("RunToTest")) {
                    continue;
                }
                table.put(strColumnName, recordset.getField(strColumnName));
            }
            excelData[rowIndex][0] = table;
            rowIndex++;
        }

        recordset.close();
        connection.close();
        return excelData;
    }

    public List<String> getSheetNames(){
        List<String> sheetNameList = connection.getMetaData().getTableNames();
        System.out.println(connection.getMetaData().getTableNames());
        connection.close();
        return sheetNameList;
    }
}
