package com.paulds.simpleftp.presentation.binders;

import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Class used to defined binding for a swipe refresh layout.
 *
 * @author Paul-DS
 */
public class RefreshBinding {

    /**
     * Sets the refresh listener.
     * @param view The current swipe refresh layout.
     * @param listener The listener which will call for refresh.
     */
    @BindingAdapter("onRefresh")
    public static void setListener(SwipeRefreshLayout view, SwipeRefreshLayout.OnRefreshListener listener) {
        view.setOnRefreshListener(listener);
    }

    /**
     * Sets the new refresh state.
     * @param view The current swipe refresh layout.
     * @param value The value indicating whether the refresh animation should be activated.
     */
    @BindingAdapter("isRefreshing")
    public static void setIsRefreshing(SwipeRefreshLayout view, boolean value) {
        view.setRefreshing(value);
    }
}
