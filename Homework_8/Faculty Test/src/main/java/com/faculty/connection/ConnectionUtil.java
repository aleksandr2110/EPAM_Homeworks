package com.faculty.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static final Logger LOG = LoggerFactory.getLogger(Connection.class);
    public static Connection getConnection()
    {

        Properties props = new Properties();
        try (InputStream in =
                     ConnectionUtil.class.getClassLoader().getResourceAsStream("app.properties"))
        {
            props.load(in);
            String drivers = props.getProperty("driver");
            if (drivers != null) {
                System.setProperty("driver", drivers);
            }
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            return DriverManager.getConnection(url, username, password);
        }
        catch(IOException e)
        {
            LOG.error("IOException! Can't get connection {}", e.getMessage());
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            LOG.error("SQLException! Can't get connection {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
