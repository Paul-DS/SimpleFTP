package com.paulds.simpleftp.presentation.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.view.View;

import com.paulds.simpleftp.presentation.activities.ListServerActivity;

/**
 * The view model for the main activity.
 *
 * @author Paul-DS
 */
public class MainViewModel extends BaseObservable {
    /**
     * The activity context.
     */
    private Context context;

    /**
     * Default constructor.
     * @param context The context of the current activity.
     */
    public MainViewModel(Context context) {
        this.context = context;
    }

    /**
     * Open the server list activity.
     * @param view The current view
     */
    public void openParameters(View view) {
        Intent intent = new Intent(context, ListServerActivity.class);
        context.startActivity(intent);
    }
}
