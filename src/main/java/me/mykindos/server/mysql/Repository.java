package me.mykindos.server.mysql;

/**
 * Represents a Table at a code level
 * Includes structure and default values
 */
public interface Repository {

    /**
     * Name of the table
     * @return Name of the table
     */
    String getTableName(String database);

    /**
     * Query to create the table
     * @param database Database name to execute the query against
     * @return The query to create the table
     */
    String getCreateTableQuery(String database);

    /**
     * Creates the table if it doesnt exist
     */
    void initialize(String database);



    /**
     * Load priority of the Repository
     * @return this Repositories load priority
     */
    LoadPriority getLoadPriority();



}
