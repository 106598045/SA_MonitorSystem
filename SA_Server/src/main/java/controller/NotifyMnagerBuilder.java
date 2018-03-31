package controller;

import NotifyManager.*;

import java.io.FileInputStream;
import java.util.Properties;

public class NotifyMnagerBuilder {
    public static NotifyManager Build() {
        Properties properties = new Properties();
        String path = ""+ HostRepositoryBuilder.class.getClassLoader().getResource("");
        String configFile = path.substring(6) + "storageConf.properties";
        try {
            properties.load(new FileInputStream(configFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String storageType = properties.getProperty("notifyMethod");
        if(storageType.equals("DB")) {
            return new NotifyManagerDB();
        }
        else {
            return null;
        }
    }
}
