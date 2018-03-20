package by.kastsiuchenka.onlinetraining.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConnectorDB {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String DB_PROPERTY_FILE = "db";

    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final String DRIVER;
    private final int POOL_SIZE;

    ConnectorDB() {   //only package available
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DB_PROPERTY_FILE);
            URL = resource.getString("url");
            USER = resource.getString("user");
            PASSWORD = resource.getString("password");
            DRIVER = resource.getString("driver");
            POOL_SIZE = Integer.parseInt(resource.getString("poolsize"));
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName(DRIVER);
        } catch (MissingResourceException e) {
            LOGGER.fatal("Bundle is not initialized. Error in property-file. " + DB_PROPERTY_FILE, e);
            throw new RuntimeException("Bundle is not initialized. Error in property-file. " + DB_PROPERTY_FILE, e);
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("Driver is not registered. No suitable driver found. ", e);
            throw new RuntimeException("Driver is not registered. No suitable driver found. ", e);
        }
    }

    int getPoolSize() {
        return POOL_SIZE;
    }

    ProxyConnection createConnection() throws DbConnectionException {
        try {
            return new ProxyConnection(DriverManager.getConnection(URL, USER, PASSWORD));
        } catch (SQLException e) {
            throw new DbConnectionException(" Connection can not be created. ", e);
        }
    }
}
