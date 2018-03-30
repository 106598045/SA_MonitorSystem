package config;

import java.io.FileInputStream;
import java.util.Properties;

public class StorageConf {
    private static String type = null;
    public static String getStorageType(){
        if (type == null) {
            Properties properties = new Properties();
            String path = ""+ StorageConf.class.getClassLoader().getResource("");
            String configFile = path.substring(6)+"storageConf.properties";
            try {
                properties.load(new FileInputStream(configFile));
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
            type = properties.getProperty("storageMethod");
        }
        return type;
    }
}
