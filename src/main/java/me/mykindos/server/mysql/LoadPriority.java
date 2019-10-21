package me.mykindos.server.mysql;

/**
 * The load order priority.
 * Lowest loads first, Highest loads last.
 * Effective in situations where you are dynamically loading data that relies on another set of data to be present
 */
public enum LoadPriority {

    // Lowest = Load first, Highest = Load last
    LOWEST(1), LOW(2), MEDIUM(3), HIGH(4), HIGHEST(5);

    private int priority;

    /**
     * @param priority Priority value
     */
    LoadPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return Priority value
     */
    public int getPriority() {
        return priority;
    }

}
