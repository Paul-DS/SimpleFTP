package com.paulds.simpleftp.presentation.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulds.simpleftp.presentation.binders.ItemBinder;

import java.lang.ref.WeakReference;
import java.util.Collection;

/**
 * Adapter for data binding usage of recycler view.
 * @param <T> The type of object to display in the recycler view.
 */
public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.ViewHolder>
{
    /**
     * Key used to store the item model.
     */
    private static final int ITEM_MODEL = -124;

    /**
     * The callback triggered when the list of items change.
     */
    private final WeakReferenceOnListChangedCallback onListChangedCallback;

    /**
     * The binder used for items display.
     */
    private final ItemBinder<T> itemBinder;

    /**
     * The list of items to display.
     */
    private ObservableList<T> items;

    /**
     * The current layout inflater.
     */
    private LayoutInflater inflater;

    /**
     * Default constructor.
     * @param itemBinder The binder used to display items.
     * @param items The list of items to display.
     */
    public BindingRecyclerViewAdapter(ItemBinder<T> itemBinder, @Nullable Collection<T> items)
    {
        this.itemBinder = itemBinder;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);
        setItems(items);
    }

    /**
     * Gets the list of items displayed.
     * @return The list of items.
     */
    public ObservableList<T> getItems()
    {
        return items;
    }

    /**
     * Sets the list of items to display.
     * @param items The list of items.
     */
    public void setItems(@Nullable Collection<T> items)
    {
        if (this.items == items)
        {
            return;
        }

        if (this.items != null)
        {
            this.items.removeOnListChangedCallback(onListChangedCallback);
            notifyItemRangeRemoved(0, this.items.size());
        }

        if (items instanceof ObservableList)
        {
            this.items = (ObservableList<T>) items;
            notifyItemRangeInserted(0, this.items.size());
            this.items.addOnListChangedCallback(onListChangedCallback);
        }
        else if (items != null)
        {
            this.items = new ObservableArrayList<>();
            this.items.addOnListChangedCallback(onListChangedCallback);
            this.items.addAll(items);
        }
        else
        {
            this.items = null;
        }
    }

    /**
     * Called when an item is detached from the recycler view.
     * @param recyclerView The recycler view widget.
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView)
    {
        if (items != null)
        {
            items.removeOnListChangedCallback(onListChangedCallback);
        }
    }

    /**
     * Called when the view holder is created.
     * @param viewGroup The view group.
     * @param layoutId The id of the layout used to display items.
     * @return The built view holder.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int layoutId)
    {
        if (inflater == null)
        {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, viewGroup, false);
        return new ViewHolder(binding);
    }

    /**
     * Called when the view holder is bound.
     * @param viewHolder The current view holder.
     * @param position The item position.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        final T item = items.get(position);
        viewHolder.binding.setVariable(itemBinder.getBindingVariable(), item);
        viewHolder.binding.getRoot().setTag(ITEM_MODEL, item);
        viewHolder.binding.executePendingBindings();
    }

    /**
     * Gets the layout id of a specified item.
     * @param position The item position.
     * @return The id of the layout used to display the item.
     */
    @Override
    public int getItemViewType(int position)
    {
        return itemBinder.getLayoutId();
    }

    /**
     * Gets the number of items displayed in the recycler view.
     * @return The total of items displayed.
     */
    @Override
    public int getItemCount()
    {
        return items == null ? 0 : items.size();
    }

    /**
     * Class used to store the recycler view item binding.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        /**
         * The item binding.
         */
        final ViewDataBinding binding;

        /**
         * Default constructor
         * @param binding The item binding.
         */
        ViewHolder(ViewDataBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    /**
     * Class used to manage the recycler view list events.
     * @param <T> The type of elements displayed.
     */
    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback
    {
        /**
         * The adapter reference.
         */
        private final WeakReference<BindingRecyclerViewAdapter<T>> adapterReference;

        /**
         * Default constructor.
         * @param bindingRecyclerViewAdapter The recycler view adapter.
         */
        public WeakReferenceOnListChangedCallback(BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter)
        {
            this.adapterReference = new WeakReference<>(bindingRecyclerViewAdapter);
        }

        /**
         * Called when the list changed
         * @param sender The current list.
         */
        @Override
        public void onChanged(ObservableList sender)
        {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyDataSetChanged();
            }
        }

        /**
         * Called when an item range changed in the list.
         * @param sender The current list.
         * @param positionStart The position of the first item modified.
         * @param itemCount The number of items modified.
         */
        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount)
        {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }

        /**
         * Called when an item range is inserted in the list.
         * @param sender The current list.
         * @param positionStart The position of the first item inserted.
         * @param itemCount The number of items inserted.
         */
        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount)
        {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }

        /**
         * Called when an item range is moved in the list.
         * @param sender The current list.
         * @param fromPosition The old position of the first item modified.
         * @param toPosition The new position of the first item modified.
         * @param itemCount The number of items modified.
         */
        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount)
        {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }
        }

        /**
         * Called when an item range is removed from the list.
         * @param sender The current list.
         * @param positionStart The position of the first item removed.
         * @param itemCount The number of items removed.
         */
        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount)
        {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        }
    }
}