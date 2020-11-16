package utilities.filereading.files.excel;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

import java.util.List;

public class FilloExcelWrite {

    private Fillo fillo;
    private Connection connection = null;
    private String connectionString = null;

    public FilloExcelWrite(String filePath) throws FilloException {
        fillo = new Fillo();

        if (connection == null) {
            connection = fillo.getConnection(filePath);
        }
    }

    public void writeData(String sheetName, String status) throws FilloException {
        System.setProperty("ROW", "1");
        System.setProperty("COLUMN", "1");

        connectionString = "update  " + sheetName + " set Status = '" + status + "'" + " where RunToTest='Y'";
        connection.executeUpdate(connectionString);
        connection.close();
    }

    public List<String> getSheetNames(){
        List<String> sheetNameList = connection.getMetaData().getTableNames();
        System.out.println(connection.getMetaData().getTableNames());
        connection.close();
        return sheetNameList;
    }
}
