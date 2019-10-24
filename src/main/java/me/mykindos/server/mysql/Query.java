package me.mykindos.server.mysql;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A mysql query that is passed into the QueryFactory
 */
public class Query {

    private final String stmt;

    /**
     * @param stmt Query statement
     */
    public Query(String stmt) {
        this.stmt = stmt;
    }

    /**
     * @return The query to be executed
     */
    public String getStatement() {
        return stmt;
    }

    /**
     * Executes the query on the provided connection
     * @param connection MySQL Connection to execute the query on
     */
    public void execute(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getStatement());
            System.out.println("QUERY " + preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (CommunicationsException ce) {
            ce.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("MEME2");

    }

}
