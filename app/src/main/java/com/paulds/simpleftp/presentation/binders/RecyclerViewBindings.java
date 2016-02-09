package com.paulds.simpleftp.presentation.binders;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.paulds.simpleftp.presentation.adapters.BindingRecyclerViewAdapter;

import java.util.Collection;

/**
 * Class used to defined bindings used for recycler views.
 */
public class RecyclerViewBindings {
    /**
     * Keys use to store the item list in th recycler view.
     */
    private static final int KEY_ITEMS = -123;

    /**
     * Sets the recycler view items.
     * @param recyclerView The recycler view widget.
     * @param items The list of items to display.
     * @param <T> The type of the items to display.
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, Collection<T> items) {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setItems(items);
        } else {
            recyclerView.setTag(KEY_ITEMS, items);
        }
    }

    /**
     * Sets the binder used for recycler view item.
     * @param recyclerView The recycler view widget.
     * @param itemViewMapper The mapper used to bind items.
     * @param <T> The type of the items to display.
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter("itemViewBinder")
    public static <T> void setItemViewBinder(RecyclerView recyclerView, ItemBinder<T> itemViewMapper) {
        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>(itemViewMapper, items);
        recyclerView.setAdapter(adapter);
    }
}