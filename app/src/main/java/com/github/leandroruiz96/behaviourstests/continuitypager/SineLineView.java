package com.github.leandroruiz96.behaviourstests.continuitypager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leandro on 8/3/17.
 */
@CoordinatorLayout.DefaultBehavior(SineLineBehavior.class)
public class SineLineView extends View {

    private static float STROKE_WIDTH = 15f;

    Paint sinePaint;
    Rect bounds;
    RectF clip;
    RectF clop;


    Path sinePath;
    private int deltaX;

    {
        sinePaint = new Paint();
        sinePaint.setStyle(Paint.Style.STROKE);
        sinePaint.setColor(Color.RED);
        sinePaint.setStrokeWidth(STROKE_WIDTH);
        sinePaint.setAntiAlias(true);

        bounds = new Rect();
        sinePath = new Path();
        clip = new RectF();
        clop = new RectF();
    }

    public SineLineView(Context context) {
        super(context);
    }

    public SineLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SineLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.getClipBounds(bounds);

        bounds.bottom -= STROKE_WIDTH;
        bounds.top += STROKE_WIDTH;

        clip.set(bounds);
        clop.set(bounds);

        clip.right = bounds.centerX();
        clop.left = bounds.centerX();

        sinePath.arcTo(clip,180,180);
        sinePath.arcTo(clop,180,-180);
        clip.offset(bounds.right,0);
        clop.offset(bounds.right,0);
        sinePath.arcTo(clip,180,180);
        sinePath.arcTo(clop,180,-180);

        sinePath.offset(-deltaX,0);

        canvas.drawPath(sinePath,sinePaint);

        sinePath.reset();

        super.onDraw(canvas);
    }

    public void setDeltaX(int deltaX) {
        if (isInEditMode()) return;
        while (deltaX>=getWidth()) deltaX -= getWidth();
        this.deltaX = deltaX;
    }
}
