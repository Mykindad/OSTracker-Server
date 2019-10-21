package me.mykindos.server.mysql.repositories;

import me.mykindos.server.mysql.LoadPriority;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.mysql.Repository;

public class ItemsRepository implements Repository {

    @Override
    public String getTableName(String database) {
        return database + ".items";
    }

    @Override
    public String getCreateTableQuery(String database) {
        return "CREATE TABLE IF NOT EXISTS `" + getTableName(database) + "` ("
                + "`id` int PRIMARY KEY AUTO_INCREMENT,"
                + "`itemName` varchar(255) NOT NULL UNIQUE"
                + ") ENGINE=InnoDB;";
    }

    @Override
    public void initialize(String database) {
        QueryFactory.getInstance().runQuery(getCreateTableQuery(database));
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.MEDIUM;
    }
}
