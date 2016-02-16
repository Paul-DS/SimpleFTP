package com.paulds.simpleftp.presentation.binders;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

/**
 * Class used to defined binding for display a drawable in an image view.
 *
 * @author Paul-DS
 */
public class DrawableBinding {

    /**
     * Sets the new visibility state.
     * @param view The current image view.
     * @param resourceId The id of the resource to display
     */
    @BindingAdapter("drawable")
    public static void setFadeVisible(ImageView view, int resourceId) {
        view.setImageResource(resourceId);
    }
}
