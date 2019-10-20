package me.mykindos.server.mysql.repositories;

import me.mykindos.server.mysql.LoadPriority;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.mysql.Repository;

public class ItemStatusRepository implements Repository {

    @Override
    public String getTableName(String database) {
        return database + ".itemstatus";
    }

    @Override
    public String getCreateTableQuery(String database) {
        return "CREATE TABLE IF NOT EXISTS `" + getTableName(database) + "` ("
                + "`id` int PRIMARY KEY,"
                + "`status` varchar(255) NOT NULL"
                + ") Engine=InnoDB;";
    }

    @Override
    public void initialize(String database) {
        QueryFactory.runQuery(getCreateTableQuery(database));
        QueryFactory.runQuery("INSERT INTO `" + getTableName(database) + "`(itemName) VALUES ('Received'), ('Lost');");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.MEDIUM;
    }
}
