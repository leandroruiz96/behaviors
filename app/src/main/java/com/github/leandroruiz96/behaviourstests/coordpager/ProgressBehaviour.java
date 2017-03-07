package com.github.leandroruiz96.behaviourstests.coordpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by leandro on 6/3/17.
 */

public class ProgressBehaviour extends CoordinatorLayout.Behavior<ProgressBar> {

    public ProgressBehaviour() {
    }

    public ProgressBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ProgressBar child, View dependency) {

        if (dependency instanceof ViewPager) {
            ViewPager pager = (ViewPager) dependency;
            checkOrSetMax(child,pager);
            child.setProgress(pager.getScrollX());
        }

        return super.onDependentViewChanged(parent, child, dependency);
    }

    private void checkOrSetMax(ProgressBar child, ViewPager pager) {
        int pages = pager.getAdapter().getCount();
        int pageWidth = pager.getWidth();
        int maxProgress = pageWidth * (pages - 1);

        if (child.getMax() != maxProgress) child.setMax(maxProgress);
    }
}
