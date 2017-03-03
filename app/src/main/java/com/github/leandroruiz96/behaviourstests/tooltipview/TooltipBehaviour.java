package com.github.leandroruiz96.behaviourstests.tooltipview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by leandro on 3/3/17.
 */
public class TooltipBehaviour extends CoordinatorLayout.Behavior<TooltipView> {

    private static final int THRESHOLD = 160;
    private static final float PARALLAX_MULTIPLIER = 0.3f;

    public TooltipBehaviour() {
        super();
    }

    public TooltipBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TooltipView child, View dependency) {
        return dependency instanceof ScrollingView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TooltipView child, View dependency) {
        if (dependency instanceof ScrollingView) {
            ScrollingView scroll = (ScrollingView) dependency;
            int scrollY = scroll.computeVerticalScrollOffset();
            float deltaY = Math.max(0,THRESHOLD - scrollY);
            float percent = deltaY / THRESHOLD;

            child.setAlpha(percent);
            child.setTranslationY(scrollY*percent*PARALLAX_MULTIPLIER);
        } else {
            throw new IllegalStateException("TooltipView can't be used with non-scrolling views");
        }

        return true;
    }
}