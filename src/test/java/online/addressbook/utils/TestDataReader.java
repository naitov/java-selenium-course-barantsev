package online.addressbook.utils;

import java.util.ResourceBundle;

public class TestDataReader {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String read(String key) {
        return BUNDLE.getString(key);
    }
}
