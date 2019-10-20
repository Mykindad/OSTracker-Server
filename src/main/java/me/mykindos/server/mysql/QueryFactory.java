package me.mykindos.server.mysql;


import me.mykindos.server.mysql.repositories.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueryFactory  {

    private static ConcurrentLinkedQueue<Query> queries = new ConcurrentLinkedQueue<>();
    private static List<Repository> repositories = new ArrayList<>();

    /**
     * Handles database, tables, and all created queries
     */
    public QueryFactory() {

        new Thread(() -> {
            while(MySQLServer.getInstance().isConnected()){
                Query q = queries.poll();
                if (q != null) {
                    q.execute(MySQLServer.getInstance().getConnection());
                }
            }
        }).start();

        repositories.add(new UserRepository());
        repositories.add(new ItemsRepository());
        repositories.add(new SkillsRepository());
        repositories.add(new RunTimeRepository());
        repositories.add(new ScriptItemsRepository());
        repositories.add(new ItemStatusRepository());
        repositories.add(new ExperienceGainedRepository());

    }

    /**
     *
     * @param query Runs a query statement
     */
    public static void runQuery(String query) {
        System.out.println(query);
        queries.add(new Query(query));
    }

    /**
     * Loads all Repository objects in order of priority.
     * Load order is LOWEST to HIGHEST
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void createRepositories(String databaseName) {
        repositories.sort(Comparator.comparingInt(r2 -> r2.getLoadPriority().getPriority()));
        repositories.forEach(r -> r.initialize(databaseName));
    }

}
