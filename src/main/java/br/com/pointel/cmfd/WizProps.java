package br.com.pointel.cmfd;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class WizProps {

    private static final Properties PROPERTIES = new Properties();

    public static void load(String name) throws Exception {
        File file = new File(name + ".props");
        if (file.exists()) {
            try ( FileReader input = new FileReader(file)) {
                PROPERTIES.load(input);
            }
        }
    }

    public static void save(String name) throws Exception {
        File file = new File(name + ".props");
        try (FileWriter output = new FileWriter(file)) {
            PROPERTIES.store(output, name + " Props");
        }
    }

    public static String get(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }

    public static void set(String key, String value) {
        PROPERTIES.setProperty(key, value);
    }

    public static Integer get(String key, Integer defaultValue) {
        return Integer.valueOf(get(key, defaultValue.toString()));
    }

    public static void set(String key, Integer value) {
        set(key, value.toString());
    }

}
