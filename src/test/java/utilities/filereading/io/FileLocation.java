package utilities.filereading.io;

import java.io.*;

public class FileLocation {

    public FileLocation() {
    }

    //<editor-fold desc="fileCopy">
    public void fileCopy(File sourceFile, File destinationFile) {
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (NullPointerException | FileNotFoundException nullpointerexception) {
            nullpointerexception.printStackTrace();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }
    //</editor-fold>
}
