package online.addressbook.utils;

import java.util.ResourceBundle;

public class TestDataReader {
    public static String read(String key) {
        return ResourceBundle.getBundle(System.getProperty("environment")).getString(key);
    }
}
