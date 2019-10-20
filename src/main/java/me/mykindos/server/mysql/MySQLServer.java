package me.mykindos.server.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLServer {

    private static MySQLServer mysqlServer;
    private Connection connection;
    private static int sqlPort = 3306;
    private String sqlServer;
    private String sqlUsername;
    private String sqlPassword;
    private String sqlDataBaseName;
    private String url;


    /**
     * Ensure only one instance can be created
     */
    private MySQLServer() {
    }

    /**
     * Create a connection with a specific database
     * @param host     IP of server MySQL is running on
     * @param username MySQL Username
     * @param password MySQL Password
     */
    public void establishConnection(String host, String username, String password) {
        try {

            // If a connection already exists, do not create a new one
            if (connection != null && !connection.isClosed()) {
                return;
            }

            sqlServer = host;
            sqlUsername = username;
            sqlPassword = password;


            url = "jdbc:mysql://" + sqlServer + "?autoReconnect=true";

            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();

            System.out.println(sqlUsername + ", " + sqlPassword);
        }
    }

    /**
     * Opens the database connection
     */
    public void openConnection() {
        try {
            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks if a database connection is open
     *
     * @return True if a connection is currently established
     */
    public boolean isConnected() {
        try {
            if (!connection.isClosed()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Connection object for MySQL, which can be used for executing queries
     *
     * @return Connection object
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Disconnects from the MySQL Server
     */
    public void disableSQL() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static MySQLServer getInstance() {
        if (mysqlServer == null) {
            mysqlServer = new MySQLServer();
        }

        return mysqlServer;
    }
}
