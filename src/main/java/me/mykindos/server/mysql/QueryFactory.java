package me.mykindos.server.mysql;


import me.mykindos.server.mysql.repositories.*;
import me.mykindos.server.mysql.threads.QueryThread;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Handles queries all MySQL queries
 */
public class QueryFactory {

    public static ConcurrentLinkedQueue<Query> queries = new ConcurrentLinkedQueue<>();
    private static List<Repository> repositories = new ArrayList<>();
    private static QueryFactory queryFactory;
    private static Thread queryThread;

    /**
     * We only want one instance of the query factory running
     * Starts the query factory, which will process any new queries received from the client
     */
    private QueryFactory() {
        try {

            queryThread = new QueryThread();
            queryThread.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query Runs a query statement
     */
    public void runQuery(String query) {
        System.out.println("Query: " + query);
        queries.add(new Query(query));
    }

    /**
     * Loads all Repository objects in order of priority.
     * Load order is LOWEST to HIGHEST
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void createRepositories(String databaseName) {
        if(repositories.isEmpty()){
            repositories.add(new UserRepository());
            repositories.add(new ItemsRepository());
            repositories.add(new SkillsRepository());
            repositories.add(new RunTimeRepository());
            repositories.add(new ScriptItemsRepository());
            repositories.add(new ItemStatusRepository());
            repositories.add(new ExperienceGainedRepository());
        }
        repositories.sort(Comparator.comparingInt(r2 -> r2.getLoadPriority().getPriority()));
        repositories.forEach(r -> r.initialize(databaseName));
    }

    /**
     * Instance of QueryFactory
     *
     * @return QueryFactory
     */
    public static QueryFactory getInstance() {
        if (queryFactory == null) {
            queryFactory = new QueryFactory();
        }

        return queryFactory;
    }

}
