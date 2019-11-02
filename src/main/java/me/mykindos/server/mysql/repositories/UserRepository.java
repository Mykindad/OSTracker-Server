package me.mykindos.server.mysql.repositories;

import me.mykindos.server.mysql.LoadPriority;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.mysql.Repository;

/**
 * Repository that maps each OSBot user to an ID
 */
public class UserRepository implements Repository {

    @Override
    public String getTableName(String database) {
        return database + "`.`users";
    }

    @Override
    public String getCreateTableQuery(String database) {
        return "CREATE TABLE IF NOT EXISTS `" + getTableName(database) + "` ("
                + "`id` int PRIMARY KEY AUTO_INCREMENT,"
                + "`username` varchar(255) NOT NULL UNIQUE"
                + ") ENGINE=InnoDB;";
    }

    @Override
    public void initialize(String database) {
        QueryFactory.getInstance().runQuery(getCreateTableQuery(database));
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.LOWEST;
    }
}
