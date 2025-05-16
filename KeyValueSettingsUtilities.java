
import java.io.*;
import java.util.Properties;

public class KeyValueSettingsUtilities {

    private static Properties properties = new Properties();
    private static String filename;

    public static void setFileName(String filename) {
        KeyValueSettingsUtilities.filename = filename;
    }

    public static String getValue(String key) throws Exception {

        sanityChecks(key);

        String value = null;
        try (FileInputStream input = new FileInputStream(filename)) {
            // Load the properties from the file
            properties.load(input);
            // Access the properties
            value = properties.getProperty(key);
        } catch (Exception e) {
            String msg = String.format("Error retrieving property %s from file %s: %s", key, filename, e.getMessage());
            throw new Exception(msg);
        }
        return value;
    }

    private static void sanityChecks(String key) throws Exception {
        if(filename == null || filename.isEmpty()) {
            throw new Exception("Filename was not set.  Please set it first before getting or setting key/value pairs.");
        }
        if(!new File(filename).exists()) {
            String msg = String.format("File %s does not exist", filename);
            throw new FileNotFoundException(msg);
        }
        if(key == null || key.isEmpty()) {
            throw new Exception("Key is empty or null.");
        }
    }

    public static void setValue(String key, String value) throws Exception {
        sanityChecks(key);
        if(value == null || value.isEmpty()) {
            String msg = String.format("You cannot set the value of key %s to null or empty.", key);
            throw new Exception(msg);
        }
        properties.setProperty(key, value);

        // Save the properties to a file
        try (FileOutputStream output = new FileOutputStream(filename)) {
            // Store properties to the file
            properties.store(output, "Configuration File");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean contains(String key) {
        return properties.containsKey(key);
    }
}
