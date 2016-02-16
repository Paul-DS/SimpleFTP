package com.paulds.simpleftp.presentation.model;

import android.databinding.Bindable;
import android.view.View;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FileEntity;

/**
 * Model for displaying a file in a view.
 *
 * @author Paul-DS
 */
public class FileViewModel {
    /**
     * The main view model
     */
    private ExplorerViewModel mainViewModel;

    /**
     * A value indicating whether this file is a directory.
     */
    private boolean isDirectory;

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
     * The icon representing the file.
     */
    private int icon;

    /**
     * Default constructor.
     * @param mainViewModel The parent view model.
     */
    public FileViewModel(ExplorerViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    /**
     * Constructor with initialization.
     * @param mainViewModel The parent view model.
     * @param file The file to display.
     */
    public FileViewModel(ExplorerViewModel mainViewModel, FileEntity file) {
        this(mainViewModel);

        this.name = file.getName();
        this.path = file.getPath();
        this.isDirectory = file.isDirectory();

        if(this.isDirectory) {
            this.icon = R.drawable.ic_folder_48dp;
        }
        else {
            this.size = file.getSize();
            this.icon = R.drawable.ic_file_48dp;
        }
    }

    /**
     * Gets a value indicating whether the file is a directory.
     * @return True if the file is a directory; false otherwise.
     */
    public boolean isDirectory() {
        return this.isDirectory;
    }

    /**
     * Sets a value indicating whether the file is a directory.
     * @param isDirectory True if the file is a directory; false otherwise.
     */
    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
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
     * Gets the icon representing the current file.
     * @return The resource for the drawable of the icon to display.
     */
    public int getIcon() {
        return this.icon;
    }

    /**
     * Gets a value indicating how the size should be displayed.
     * @return A value indicating how the size should be displayed.
     */
    public int getSizeVisible() {
        return this.isDirectory ? View.GONE : View.VISIBLE;
    }

    /**
     * Gets the file size to display.
     * @return The file size to display.
     */
    public String getDisplayedSize() {
        if(size == null) {
            return null;
        }

        if(size > 1024 * 1024 * 1024) {
            return (size / (1024 * 1024 * 1024)) + "Go";
        }
        else if(size > 1024 * 1024) {
            return (size / (1024 * 1024)) + "Mo";
        }
        else if(size > 1024) {
            return (size / (1024)) + "Ko";
        }
        else {
            return size + "o";
        }
    }

    /**
     * Called when the file item is clicked.
     * @param view The current view.
     */
    public void onItemClick(View view) {
        this.mainViewModel.selectFile(this);
    }
}
