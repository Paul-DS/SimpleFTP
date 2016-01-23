package com.paulds.simpleftp.repository;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provide an access to local files.
 *
 * @author Paul-DS
 */
public class FileRepository {

    /**
     * Lists the files present at the specified path.
     * @param path The specified path.
     * @return The list of files.
     */
    public File[] listFiles(String path) {
        String fullPath = path;

        File folder = new File(fullPath);

        return folder.listFiles();
    }


    /**
     * Create a folder at the specified path.
     * @param path The specified path.
     * @param name The name of the folder to create.
     */
    public void createFolder(String path, String name) {
        String fullPath = Environment.getExternalStorageDirectory().toString() + path + "/" + name;

        File folder = new File(fullPath);

        if(!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Indicates whether the storage is writable.
     * @return A value indicating whether the storage is writable.
     */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
