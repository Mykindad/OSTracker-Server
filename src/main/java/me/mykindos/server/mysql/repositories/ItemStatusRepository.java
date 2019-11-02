package me.mykindos.server.mysql.repositories;

import me.mykindos.server.mysql.LoadPriority;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.mysql.Repository;

/**
 * Repository for mapping each ItemStatus to an ID.
 * Item Statuses include Received, Lost, and Spent
 */
public class ItemStatusRepository implements Repository {

    @Override
    public String getTableName(String database) {
        return database + "`.`itemstatus";
    }

    @Override
    public String getCreateTableQuery(String database) {
        return "CREATE TABLE IF NOT EXISTS `" + getTableName(database) + "` ("
                + "`id` int PRIMARY KEY AUTO_INCREMENT,"
                + "`status` varchar(255) NOT NULL UNIQUE"
                + ") Engine=InnoDB;";
    }

    @Override
    public void initialize(String database) {
        QueryFactory.getInstance().runQuery(getCreateTableQuery(database));
        QueryFactory.getInstance().runQuery("INSERT IGNORE INTO `" + getTableName(database) + "` (status) VALUES ('Received'), ('Lost'), ('Spent');");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.MEDIUM;
    }
}
