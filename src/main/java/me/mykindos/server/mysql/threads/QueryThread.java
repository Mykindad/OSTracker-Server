package me.mykindos.server.mysql.threads;

import me.mykindos.server.mysql.MySQLServer;
import me.mykindos.server.mysql.Query;
import me.mykindos.server.mysql.QueryFactory;

public class QueryThread extends Thread {

    public QueryThread(){
        super();
    }

    @Override
    public void run(){
        while(MySQLServer.getInstance().isConnected() && !interrupted()){
            Query q = QueryFactory.getInstance().queries.poll();
            if (q != null) {
                q.execute(MySQLServer.getInstance().getConnection());
            }
        }
    }
}
