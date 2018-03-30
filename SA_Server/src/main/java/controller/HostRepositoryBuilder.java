package controller;

import Storage.HostDB;
import Storage.HostRepository;
import Storage.HostTxt;

import java.io.FileInputStream;
import java.util.Properties;

public class HostRepositoryBuilder {
    public static HostRepository Build() {
        Properties properties = new Properties();
        String path = ""+ HostRepositoryBuilder.class.getClassLoader().getResource("");
        String configFile = path.substring(6) + "storageConf.properties";
        try {
            properties.load(new FileInputStream(configFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String storageType = properties.getProperty("storageMethod");
        System.out.println(storageType);

        if(storageType.equals("TXT")) {
            return new HostTxt();
        } else if(storageType.equals("DB")) {
            return new HostDB();
        } else {
            return null;
        }
    }
}

