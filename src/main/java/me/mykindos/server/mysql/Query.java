package me.mykindos.server.mysql;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {

    private final String stmt;

    public Query(String stmt) {
        this.stmt = stmt;
    }

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
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (CommunicationsException ce) {
            ce.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
