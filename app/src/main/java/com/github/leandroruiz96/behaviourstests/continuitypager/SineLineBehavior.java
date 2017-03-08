package com.github.leandroruiz96.behaviourstests.continuitypager;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leandro on 7/3/17.
 */

public class SineLineBehavior extends CoordinatorLayout.Behavior<SineLineView> {

    public SineLineBehavior() {
        super();
    }

    public SineLineBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, SineLineView child, View dependency) {

        if (dependency instanceof ViewPager) {
            ViewPager pager = (ViewPager) dependency;

            child.setDeltaX(pager.getScrollX());
            child.invalidate();
        }

        return super.onDependentViewChanged(parent, child, dependency);
    }
}
