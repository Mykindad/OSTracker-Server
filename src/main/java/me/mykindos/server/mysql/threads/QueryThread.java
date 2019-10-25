package me.mykindos.server.mysql.threads;

import me.mykindos.server.mysql.MySQLServer;
import me.mykindos.server.mysql.Query;
import me.mykindos.server.mysql.QueryFactory;

/**
 * Thread to process queries
 * Prevents server blocking
 */
public class QueryThread extends Thread {

    /**
     * Create the thread
     */
    public QueryThread(){
        super();
    }

    /**
     * Grabs the next query in the queue and executes it
     */
    @Override
    public void run(){
        while(!interrupted()){

            Query q = QueryFactory.getInstance().queries.poll();
            if (q != null) {
                q.execute(MySQLServer.getInstance().getConnection());
            }

        }
        System.out.println("Interrupted MySQL Loop");
    }
}
