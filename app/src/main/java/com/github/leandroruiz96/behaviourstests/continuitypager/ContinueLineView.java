package com.github.leandroruiz96.behaviourstests.continuitypager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leandro on 7/3/17.
 */
@CoordinatorLayout.DefaultBehavior(ContinueLineBehavior.class)
public class ContinueLineView extends View {

    private static final float CIRCLE_RADIUS = 45f;

    private Paint paint;
    private Paint circlePaint;
    private Rect bounds;

    float deltaX;
    float deltaY;

    {
        paint  = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30f);
        paint.setAntiAlias(true);

        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(3f);
        circlePaint.setAntiAlias(true);

        bounds = new Rect();
    }

    public ContinueLineView(Context context) {
        super(context);
    }

    public ContinueLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContinueLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.getClipBounds(bounds);

        {
            float left = bounds.left;
            float right = bounds.right;
            float bottom = bounds.bottom - CIRCLE_RADIUS;
            float top = bounds.top + CIRCLE_RADIUS;

            canvas.drawLine(left,bottom,right,top, paint);
        }

        {
            float cx = bounds.right - deltaX;
            float cy = bounds.top + CIRCLE_RADIUS + deltaY;
            canvas.drawCircle(cx,cy,CIRCLE_RADIUS,circlePaint);

            if (cx >= getWidth() - CIRCLE_RADIUS) {
                float cxo = bounds.left - deltaX;
                float cyo = bounds.bottom - CIRCLE_RADIUS + deltaY;
                canvas.drawCircle(cxo,cyo,CIRCLE_RADIUS,circlePaint);
            }

            if (cx <= CIRCLE_RADIUS) {
                float cxo = bounds.right * 2 - deltaX;
                float cyo = (bounds.top + 3*CIRCLE_RADIUS - getHeight()) + deltaY;
                canvas.drawCircle(cxo,cyo,CIRCLE_RADIUS,circlePaint);
            }
        }

        super.onDraw(canvas);
    }

    public void setDeltaX(float deltaX) {
        while (deltaX>=getWidth()) deltaX -= getWidth();
        this.deltaX = deltaX;
    }

    public void setDeltaY(float deltaY) {
        while (deltaY>=getHeight() - 2 * CIRCLE_RADIUS) deltaY -= getHeight() - 2 * CIRCLE_RADIUS;
        this.deltaY = deltaY;
    }

    public float getPendent() {
        return (getHeight() - 2 * CIRCLE_RADIUS) / (float) getWidth();
    }
}
