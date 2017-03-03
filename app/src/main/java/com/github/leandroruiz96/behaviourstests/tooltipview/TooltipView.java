package com.github.leandroruiz96.behaviourstests.tooltipview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ScrollingView;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by leandro on 3/3/17.
 */
@CoordinatorLayout.DefaultBehavior(TooltipBehaviour.class)
public class TooltipView extends TextView {

    public TooltipView(Context context) {
        super(context);
    }

    public TooltipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TooltipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
