package me.mykindos.server.mysql.repositories;

import me.mykindos.server.mysql.LoadPriority;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.mysql.Repository;

public class ScriptItemsRepository implements Repository {


    @Override
    public String getTableName(String database) {
        return database + ".scriptitems";
    }

    @Override
    public String getCreateTableQuery(String database) {
        return "CREATE TABLE IF NOT EXISTS `" + getTableName(database) + "` ("
                + " `id` int PRIMARY KEY AUTO_INCREMENT,"
                + " `user` int NOT NULL,"
                + " `item` int NOT NULL,"
                + " `amount' int NOT NULL,"
                + " `itemStatus` int NOT NULL,"
                + " `date` datetime NOT NULL DEFAULT (now()) "
                + ") ENGINE=InnoDB;";
    }

    @Override
    public void initialize(String database) {
        QueryFactory.getInstance().runQuery(getCreateTableQuery(database));
        QueryFactory.getInstance().runQuery("ALTER TABLE `" + getTableName(database)
                + "` ADD FOREIGN KEY (`user`) REFERENCES `" + database + ".users" + "` (`id`);");
        QueryFactory.getInstance().runQuery("ALTER TABLE `" + getTableName(database)
                + "` ADD FOREIGN KEY (`item`) REFERENCES `" + database + ".items" + "` (`id`);");
        QueryFactory.getInstance().runQuery("ALTER TABLE `" + getTableName(database)
                + "` ADD FOREIGN KEY (`itemStatus`) REFERENCES `" + database + ".itemstatus" + "` (`id`);");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.HIGHEST;
    }
}
