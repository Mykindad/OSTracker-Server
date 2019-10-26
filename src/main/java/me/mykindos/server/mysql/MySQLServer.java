package me.mykindos.server.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The managing object for the MySQL Connection
 */
public class MySQLServer {

    private static MySQLServer mysqlServer;
    private static int sqlPort = 3306;
    private Connection connection;
    private String sqlServer;
    private String sqlUsername;
    private String sqlPassword;
    private String sqlDataBaseName;
    private String url;
    private Thread connectionChecker;

    private String mysqlCreateUsername;
    private String mysqlCreatePasssword;

    /**
     * Ensure only one instance can be created
     */
    private MySQLServer() {
    }

    /**
     * MySQLServer Instance
     *
     * @return MySQL Server
     */
    public static MySQLServer getInstance() {
        if (mysqlServer == null) {
            mysqlServer = new MySQLServer();
        }

        return mysqlServer;
    }

    /**
     * Create a connection with a specific database
     */
    public void establishConnection() {
        try {

            // If a connection already exists, do not create a new one
            if (connection != null && !connection.isClosed()) {
                return;
            }


            url = "jdbc:mysql://" + sqlServer + "?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";

            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);

            // Monitors the MySQL connection in case it drops
            if(connectionChecker != null){
                connectionChecker.interrupt();
            }
            connectionChecker = new Thread(() -> {
                while (!Thread.interrupted()) {
                    try {

                        if (!isTrulyConnected()) {
                            System.out.println("Attempting to reestablish connection with MySQL");
                            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
                        }

                        Thread.sleep(5000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            connectionChecker.start();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set MySQL Settings without opening a connection
     *
     * @param host     Host IP
     * @param username MySQL username
     * @param password MySQL Password
     */
    public void setMySQLCredentials(String host, String username, String password) {
        sqlServer = host;
        sqlUsername = username;
        sqlPassword = password;
        url = "jdbc:mysql://" + sqlServer + "?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";

    }

    public void setMySQLUserCredentials(String username, String password){
        this.mysqlCreateUsername = username;
        this.mysqlCreatePasssword = password;
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
     * Check if the MySQL connection is still active
     * @return True if still connected to MySQL
     */
    public boolean isTrulyConnected() {
        try {
            return connection.isValid(500);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Checks if a database connection is open
     *
     * @return True if a connection is currently established
     */
    public boolean isConnected() {
        try {
            if (connection == null) {
                return false;
            }
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
        if(url == null) return null;
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
            connectionChecker.interrupt();
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getMysqlCreateUserUsername() {
        return mysqlCreateUsername;
    }

    public String getMysqlCreateUserPasssword() {
        return mysqlCreatePasssword;
    }
}
