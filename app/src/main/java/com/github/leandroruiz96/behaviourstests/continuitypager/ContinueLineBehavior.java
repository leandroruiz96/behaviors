package com.github.leandroruiz96.behaviourstests.continuitypager;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leandro on 7/3/17.
 */

public class ContinueLineBehavior extends CoordinatorLayout.Behavior<ContinueLineView> {

    public ContinueLineBehavior() {
    }

    public ContinueLineBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ContinueLineView child, View dependency) {

        if (dependency instanceof ViewPager) {
            ViewPager pager = (ViewPager) dependency;

            float deltaY = interpolatingFunction(child,pager.getScrollX());

            child.setTranslationY(deltaY);
            child.setDeltaX(pager.getScrollX());
            child.setDeltaY(-deltaY);
            child.invalidate();
        }

        return super.onDependentViewChanged(parent, child, dependency);
    }


    private float interpolatingFunction(ContinueLineView view, int scroll) {
        float pendent = (float) view.getHeight() / (float) view.getWidth();
        return - scroll * pendent;
    }
}
