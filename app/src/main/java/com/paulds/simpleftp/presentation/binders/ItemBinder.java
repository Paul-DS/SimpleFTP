package com.paulds.simpleftp.presentation.binders;

/**
 * Class used for binding recycler view item.
 * @param <T> The type of object displayed in the recycler view.
 *
 * @author Paul-DS
 */
public class ItemBinder<T>
{
    /**
     * The id of the binding variable used in recycler view item layout.
     */
    protected final int bindingVariable;

    /**
     * The id of the layout used for displaying recycler view item.
     */
    protected final int layoutId;

    /**
     * Default constructor.
     * @param bindingVariable The id of the binding variable.
     * @param layoutId The id of the layout used for displaying recycler view item.
     */
    public ItemBinder(int bindingVariable, int layoutId)
    {
        this.bindingVariable = bindingVariable;
        this.layoutId = layoutId;
    }

    /**
     * Gets the id of the layout used for displaying recycler view item.
     * @return The layout id.
     */
    public int getLayoutId()
    {
        return layoutId;
    }

    /**
     * Gets the binding variable used in recycler view item layout.
     * @return The binding variable.
     */
    public int getBindingVariable()
    {
        return bindingVariable;
    }
}