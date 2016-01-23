package com.paulds.simpleftp.presentation;

import android.app.Application;

import com.paulds.simpleftp.data.repository.ApplicationRepository;

/**
 * Created by Paul on 13/01/2016.
 */
public class AndroidApplication extends Application {
    /**
     * The application repository.
     */
    private static ApplicationRepository repository;

    /**
     * Called at application creation.
     */
    @Override public void onCreate() {
        super.onCreate();
        repository = new ApplicationRepository(this.getApplicationContext());
    }

    /**
     * Gets the application repository.
     * @return The application repository.
     */
    public static ApplicationRepository getRepository() {
        return repository;
    }
}
