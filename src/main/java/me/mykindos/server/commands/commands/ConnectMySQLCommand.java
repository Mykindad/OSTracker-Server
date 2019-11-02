package me.mykindos.server.commands.commands;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.MySQLServer;
import me.mykindos.server.mysql.QueryFactory;

/**
 * Command that establishes the connection to the MySQL Server
 */
public class ConnectMySQLCommand implements ICommand {
    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("ConnectMySQL");
    }

    @Override
    public void execute(String... args) {
        try {
            MySQLServer.getInstance().establishConnection();
            // MySQL 8 requires mysql_native_password to login our way
            if(MySQLServer.getInstance().getMysqlCreateUserUsername() != null) {
                QueryFactory.getInstance().runQuery("CREATE USER IF NOT EXISTS `" + MySQLServer.getInstance().getMysqlCreateUserUsername()
                        + "`@`localhost` IDENTIFIED WITH mysql_native_password BY '" + MySQLServer.getInstance().getMysqlCreateUserUsername() + "';");
                // Only grant privileges to Script related databases, anything else is not our business
                QueryFactory.getInstance().runQuery("GRANT ALL PRIVILEGES ON `osbot-%`.* to `" + MySQLServer.getInstance().getMysqlCreateUserUsername() + "`@`localhost`;");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
