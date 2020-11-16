package utilities.filereading.files.properties;

import utilities.filereading.io.FileLocation;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ReadProperties {
    Properties props;

    public ReadProperties() {
        File sourceFileLocation = new File(Objects.requireNonNull(ReadProperties.class.getClassLoader().getResource("project.properties")).getFile().replaceAll("%20", " "));
        File arbitraryFileLocation = Paths.get(System.getProperty("user.dir") + "\\project.properties").toFile();
        if (!arbitraryFileLocation.exists()) {
            new FileLocation().fileCopy(sourceFileLocation, arbitraryFileLocation);
            props = new Properties();
            try {
                FileInputStream fis = new FileInputStream(arbitraryFileLocation);
                props.load(fis);
            } catch (FileNotFoundException fileException) {
                fileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            props = new Properties();
            try {
                FileInputStream fis = new FileInputStream(arbitraryFileLocation);
                props.load(fis);
            } catch (FileNotFoundException fileException) {
                fileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public String getPropertyValue(String key) {
        String keyValue = null;
        if (key != null) {
            keyValue = props.getProperty(key).trim().toLowerCase();
            if (keyValue.contains("${user.dir}")) {
                String[] filePathSplit = keyValue.replaceAll("/", "\\\\").split("\\\\");
                keyValue = System.getProperty("user.dir") + "\\" + filePathSplit[1];
            }
        }
        return keyValue;
    }

    public String getPropertyValue1(String key) {

        Map<String, String> propsMap = new HashMap<String, String>((Map) props);
        Map<String, String> newpropdMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        newpropdMap.putAll(propsMap);

        return newpropdMap.get(key);
    }

    private ArrayList<String> getPropertyValues(String key) throws IOException {
        String keyValueslist = null;
        if (key != null) {
            keyValueslist = props.getProperty(key).trim().toLowerCase();
        }
        return new ArrayList<>(Arrays.asList(keyValueslist.split(",")));
    }
}
