package me.mykindos.server.mysql.repositories;

import me.mykindos.server.mysql.LoadPriority;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.mysql.Repository;

public class SkillsRepository implements Repository {


    @Override
    public String getTableName(String database) {
        return database + ".skills";
    }

    @Override
    public String getCreateTableQuery(String database) {
        return "CREATE TABLE IF NOT EXISTS `" + getTableName(database) + "` ("
                + "`id` int PRIMARY KEY,"
                + "`skillName` varchar(255) NOT NULL"
                + ") ENGINE=InnoDB;";
    }

    @Override
    public void initialize(String database) {
        QueryFactory.getInstance().runQuery(getCreateTableQuery(database));
        QueryFactory.getInstance().runQuery("INSERT IGNORE INTO `" + getTableName(database) + "` (skillName) VALUES " +
                "('ATTACK')," +
                "('STRENGTH')," +
                "('DEFENCE')," +
                "('HITPOINTS')," +
                "('RANGED')," +
                "('MAGIC')," +
                "('PRAYER')," +
                "('RUNECRAFTING')," +
                "('CRAFTING')," +
                "('MINING')," +
                "('SMITHING')," +
                "('FISHING')," +
                "('COOKING')," +
                "('FIREMAKING')," +
                "('WOODCUTTING')," +
                "('AGILITY')," +
                "('HERBLORE')," +
                "('THIEVING')," +
                "('FLETCHING')," +
                "('SLAYER')," +
                "('FARMING')," +
                "('CONSTRUCTION')," +
                "('HUNTER')");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.MEDIUM;
    }
}
