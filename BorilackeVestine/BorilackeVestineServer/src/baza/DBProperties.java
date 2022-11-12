/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author User
 */
public class DBProperties {
    Properties properties;

    public DBProperties() throws FileNotFoundException, IOException {
        properties = new Properties();
        properties.load(new FileInputStream("properties.config"));
    }

    public String vratiDbUrl() {
        return properties.getProperty(DBKonstante.URL);
    }

    public String vratiDbUsername() {
        return properties.getProperty(DBKonstante.USERNAME);
    }
    
    public String vratiDbPassword() {
        return properties.getProperty(DBKonstante.PASSWORD);
    }
    
    public String vratiDbPort() {
        return properties.getProperty(DBKonstante.PORT);
    }
}
