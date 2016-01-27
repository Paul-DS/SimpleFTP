package com.paulds.simpleftp.data.entities;

/**
 * Entity representing a file.
 *
 * @author Paul-DS
 */
public class FileEntity {

    /**
     * The file name.
     */
    private String name;

    /**
     * The file path.
     */
    private String path;

    /**
     * The file size.
     */
    private Long size;

    /**
     * Indicates whether this file is a directory.
     */
    private boolean isDirectory;

    /**
     * Gets the file name.
     * @return The file name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the file name.
     * @param name The file name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the file path.
     * @return The file path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the file path.
     * @param path The file path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the file size.
     * @return The file size.
     */
    public Long getSize() {
        return size;
    }

    /**
     * Sets the file size.
     * @param size The file size.
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * Indicates whether this file is a directory.
     * @return true if the file is a directory; false otherwise
     */
    public boolean isDirectory() {
        return isDirectory;
    }

    /**
     * Set a value indicating whether this file is a directory.
     * @param isDirectory true if the file is a directory; false otherwise
     */
    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }
}