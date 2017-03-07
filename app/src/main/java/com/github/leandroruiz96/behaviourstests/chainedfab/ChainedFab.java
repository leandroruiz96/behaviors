package com.github.leandroruiz96.behaviourstests.chainedfab;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DimenRes;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;

import com.github.leandroruiz96.behaviourstests.R;

/**
 * Created by leandro on 6/3/17.
 */
@CoordinatorLayout.DefaultBehavior(ChainedFabBehavior.class)
public class ChainedFab extends FloatingActionButton {

    @IdRes int mLinkedFab;
    int mChainedMargin;

    public ChainedFab(Context context) {
        super(context);
    }

    public ChainedFab(Context context, AttributeSet attrs) {
        super(context, attrs);
        retrieveValues(context, attrs,0);
    }

    public ChainedFab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        retrieveValues(context, attrs,defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        super.onLayout(changed, left, top, right, bottom);

        if (getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) getLayoutParams();
            ChainedFabBehavior behavior = (ChainedFabBehavior) params.getBehavior();

            if (getParent() instanceof CoordinatorLayout && behavior!=null) {
                ChainedFab linked = this.getLinkedFab();
                if (this!=linked) {
                    this.setX(linked.getX());
                    this.setY(linked.getY() - this.getHeight() - mChainedMargin);
                }
            }
        }

    }

    protected ChainedFab getLinkedFab() {
        if (getParent() instanceof CoordinatorLayout) {
            if (mLinkedFab != -1) {
                ChainedFab linked = (ChainedFab) ((CoordinatorLayout) getParent()).findViewById(mLinkedFab);
                return linked;
            }
            else return this;
        }
        return null;
    }


    private void retrieveValues(Context context, AttributeSet attrs, int defStyleAttr) {
        mLinkedFab = -1;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ChainedFab,
                defStyleAttr, 0);
        try {
            mLinkedFab = a.getResourceId(R.styleable.ChainedFab_chain_order, -1);
            mChainedMargin = a.getDimensionPixelSize(R.styleable.ChainedFab_chain_margin,0);
        } finally {
            a.recycle();
        }
    }

}
