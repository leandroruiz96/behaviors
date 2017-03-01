package com.github.leandroruiz96.behaviourstests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leandro on 1/3/17.
 */

public class SlideBehaviour extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public SlideBehaviour(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    CoordinatorLayout.LayoutParams mParams;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams params) {
        super.onAttachedToLayoutParams(params);
        mParams = params;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        AppBarLayout appbar = ((AppBarLayout) dependency);


        child.setY(dependency.getBottom()-child.getHeight()/2);
        child.setX(minX(appbar,child));

        float transformation = getTransformationFor(appbar,child);
        child.setScaleX(transformation);
        child.setScaleY(transformation);
        return true;
    }

    private float getTransformationFor(AppBarLayout appbar, FloatingActionButton child) {
        float percent = (((float)appbar.getTotalScrollRange()+(float)appbar.getTop())/(float)appbar.getTotalScrollRange());
        return percent;
    }

    private float minX(AppBarLayout appbar, FloatingActionButton fab) {
        float min = appbar.getWidth() - mParams.rightMargin - fab.getWidth();

        float percent = (((float)appbar.getTotalScrollRange()+(float)appbar.getTop())/(float)appbar.getTotalScrollRange());

        return Math.min(min,min*percent);
    }
}
