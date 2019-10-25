package me.mykindos.server.mysql.repositories;

import me.mykindos.server.mysql.LoadPriority;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.mysql.Repository;

/**
 * Repository for tracking each users runtime data.
 * Stores time ran in milliseconds
 */
public class RunTimeRepository implements Repository {

    @Override
    public String getTableName(String database) {
        return database + ".runtimes";
    }

    @Override
    public String getCreateTableQuery(String database) {
        return "CREATE TABLE IF NOT EXISTS `" + getTableName(database) + "` ("
                + "`id` int PRIMARY KEY AUTO_INCREMENT,"
                + "`user` int NOT NULL,"
                + "`duration` bigint NOT NULL,"
                + "`date` datetime NOT NULL DEFAULT (now())"
                + ") ENGINE=InnoDB;";
    }

    @Override
    public void initialize(String database) {

        QueryFactory.getInstance().runQuery(getCreateTableQuery(database));
        QueryFactory.getInstance().runQuery("ALTER TABLE `" + getTableName(database)
                + "` ADD FOREIGN KEY (`user`) REFERENCES `" + database + ".users" + "` (`id`);");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.HIGHEST;
    }
}
