package com.github.leandroruiz96.behaviourstests.chainedfab;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by leandro on 6/3/17.
 */
public class ChainedFabBehavior extends CoordinatorLayout.Behavior<ChainedFab> {

    CoordinatorLayout.LayoutParams mInitialParams;

    public ChainedFabBehavior() {
        super();
    }

    public ChainedFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ChainedFab child, View dependency) {
        return dependency instanceof AppBarLayout || dependency.getId() == child.mLinkedFab;
    }

    @Override
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams params) {
        super.onAttachedToLayoutParams(params);
        mInitialParams = params;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ChainedFab child, View dependency) {

        if (dependency instanceof AppBarLayout) {
            AppBarLayout appbar = (AppBarLayout) dependency;
            float percent = (((float)appbar.getTotalScrollRange()+(float)appbar.getTop())/(float)appbar.getTotalScrollRange());

            ChainedFab first = getFirstLinkedFAB(parent,child);

            if (first != child) {
                //I'm a linked FAB
                float deltaY = child.getTop() - first.getTop() - ((CoordinatorLayout.LayoutParams) first.getLayoutParams()).topMargin;
                child.setTranslationY(deltaY*percent);
                return true;
            } else {
                //I'm the first FAB
            }
        }

        return false;
    }

    protected ChainedFab getFirstLinkedFAB(CoordinatorLayout parent, ChainedFab child) {
        for (View dependent : parent.getDependencies(child)) {
            if (dependent instanceof ChainedFab) {
                return (ChainedFab) dependent;
            }
        }
        return child;
    }


}
