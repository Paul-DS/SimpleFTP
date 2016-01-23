package com.paulds.simpleftp.data.repository;

import android.content.Context;

/**
 * This class provide an access to all application repositories.
 *
 * @author Paul-DS
 */
public class ApplicationRepository {

    /**
     * The context used for repositories initialization.
     */
    private Context context;

    /**
     * The server repository.
     */
    private ServerRepository serverRepository;

    /**
     * The file repository.
     */
    private FileRepository fileRepository;

    /**
     * Initialize a new instance of ApplicationRepository
     * @param context The context used for repositories initialization.
     */
    public ApplicationRepository(Context context) {
        this.context = context;
    }

    /**
     * Gets the server repository.
     * @return The server repository.
     */
    public ServerRepository getServerRepository() {
        if(this.serverRepository == null) {
            this.serverRepository = new ServerRepository(context.getApplicationContext());
        }

        return this.serverRepository;
    }

    /**
     * Gets the file repository.
     * @return The file repository.
     */
    public FileRepository getFileRepository() {
        if(this.fileRepository == null) {
            this.fileRepository = new FileRepository();
        }

        return this.fileRepository;
    }
}
