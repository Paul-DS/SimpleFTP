package com.paulds.simpleftp.presentation.model;

import android.view.View;

/**
 * Model for displaying a file in a view.
 *
 * @author Paul-DS
 */
public class FileViewModel {
    /**
     * The main view model
     */
    private ListFileViewModel mainViewModel;

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
     * Default constructor.
     * @param mainViewModel The parent view model.
     */
    public FileViewModel(ListFileViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

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
    public void setFilepath(String path) {
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
     * Called when the file item is clicked.
     * @param view The current view.
     */
    public void onItemClick(View view) {
        this.mainViewModel.selectFile(this);
    }
}
