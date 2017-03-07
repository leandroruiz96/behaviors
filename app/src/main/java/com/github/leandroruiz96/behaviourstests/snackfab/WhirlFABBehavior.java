package com.github.leandroruiz96.behaviourstests.snackfab;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by leandro on 3/3/17.
 */

public class WhirlFABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public WhirlFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {

        if (dependency instanceof Snackbar.SnackbarLayout) {
            child.clearAnimation();
            int snackHeight = dependency.getHeight();
            float offset = ViewCompat.getTranslationY(dependency) - dependency.getHeight();
            float animationPercent = offset / (float) snackHeight;
            child.setRotation(360 * animationPercent);
            child.setTranslationY(offset);
            return true;
        }

        return false;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            for (View v : parent.getDependencies(child)) {
                if (v instanceof Snackbar.SnackbarLayout && v != dependency) return;
            }
            child.animate()
                    .translationY(0)
                    .setDuration(160)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .start();
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }
}
