package com.paulds.simpleftp.data.repository;

import android.os.Environment;

import com.paulds.simpleftp.data.entities.FileEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    public List<FileEntity> listFiles(String path) {
        String fullPath = path;

        File folder = new File(fullPath);

        File[] files = folder.listFiles();

        List<FileEntity> results = new ArrayList<FileEntity>();

        if(files != null) {
            for (File file : files) {
                results.add(this.fileToEntity(file));
            }
        }

        Collections.sort(results);

        return results;
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
     * Delete a list of files and directories.
     * @param elements The specified list.
     */
    public void deleteFiles(List<FileEntity> elements) {
        for (FileEntity element: elements) {
            File file = new File(element.getPath());

            if(file.exists()) {
                file.delete();
            }
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

    /**
     * Convert a file to a file entity.
     * @param file The fil to convert.
     * @return The converted file entity.
     */
    private FileEntity fileToEntity(File file)
    {
        FileEntity entity = new FileEntity();
        entity.setPath(file.getPath());
        entity.setName(file.getName());
        entity.setSize(file.length());
        entity.setIsDirectory(file.isDirectory());
        return entity;
    }
}
