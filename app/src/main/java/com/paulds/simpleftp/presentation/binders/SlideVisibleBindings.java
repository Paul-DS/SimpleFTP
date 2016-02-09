package com.paulds.simpleftp.presentation.binders;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.paulds.simpleftp.R;

/**
 * Class used to defined binding for display and hide elements with a slide animation.
 *
 * @author Paul-DS
 */
public class SlideVisibleBindings {

    /**
     * Sets the new visibility state.
     * @param view The current view.
     * @param visible A value indicating whether the element should be displayed.
     */
    @BindingAdapter("slideVisible")
    public static void setSlideVisible(final View view, boolean visible) {
        if (view.getTag() == null) {
            view.setTag(true);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            view.setTranslationY(visible ? 0 : 1000);
        } else {
            view.animate().cancel();

            if (visible) {
                view.setVisibility(View.VISIBLE);
                view.animate().setDuration(500).translationYBy(view.getHeight() == 0 ? 1000 : view.getHeight()).translationY(0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                });
            } else {
                view.animate().setDuration(500).translationYBy(0).translationY(view.getHeight()).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
            }
        }
    }
}
