package com.paulds.simpleftp.data.entities;

/**
 * Represent a database object.
 *
 * @author Paul-DS
 */
public abstract class DatabaseEntity {

    /**
     * The database entity identifier.
     */
    private int id;

    /**
     * Gets the database entity identifier.
     * @return The database entity identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database entity identifier.
     * @param id The new database entity identifier.
     */
    public void setId(int id) {
        this.id = id;
    }
}
