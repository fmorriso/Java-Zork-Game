
import java.io.*;
import java.util.Properties;

public class KeyValueSettingsUtilities {

    private static Properties properties = new Properties();
    private static String filename;

    public static void setFileName(String filename) {
        KeyValueSettingsUtilities.filename = filename;
    }

    public static String getValue(String key)  {

        sanityChecks(key);

        String value = null;
        try (FileInputStream input = new FileInputStream(filename)) {
            // Load the properties from the file
            properties.load(input);
            // Access the properties
            value = properties.getProperty(key);
        } catch (Exception e) {
            String msg = String.format("Error retrieving property %s from file %s: %s", key, filename, e.getMessage());
            return msg;
        }
        return value;
    }

    private static String sanityChecks(String key)  {
        if(filename == null || filename.isEmpty()) {
            return "Filename was not set.  Please set it first before getting or setting key/value pairs.";
        }
        if(!new File(filename).exists()) {
            String msg = String.format("File %s does not exist", filename);
            return msg;
        }
        if(key == null || key.isEmpty()) {
            return "Key is empty or null.";
        }
        return "";
    }

    public static String setValue(String key, String value) {
        sanityChecks(key);
        if(value == null || value.isEmpty()) {
            String msg = String.format("You cannot set the value of key %s to null or empty.", key);
            return msg;
        }
        properties.setProperty(key, value);

        // Save the properties to a file
        try (FileOutputStream output = new FileOutputStream(filename)) {
            // Store properties to the file
            properties.store(output, "Configuration File");
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "Unable to write to file " + filename;
        }
    }

    public static boolean contains(String key) {
        return properties.containsKey(key);
    }
}
