package com.paulds.simpleftp.presentation.model;

/**
 * Model for displaying a file in a view.
 *
 * @author Paul-DS
 */
public class FileViewModel {

    /**
     * The file name.
     */
    private String filename;

    /**
     * The file path.
     */
    private String filepath;

    /**
     * The file size.
     */
    private Long size;

    /**
    * Gets the file name.
    * @return The file name.
    */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the file name.
     * @param filename The file name.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Gets the file path.
     * @return The file path.
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Sets the file path.
     * @param filepath The file path.
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
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
}
