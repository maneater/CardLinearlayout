package com.maneater.widget.cardlinearlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by macbook on 16/8/22.
 */
public class CardLinearlayout extends LinearLayout {

    private int cornerSize = 10;
    private int lineSize = 4;
    private int lineColor = 0xfff7f7f7;
    private int cicleColor = 0xfff7f7f7;
    private int dashSize = 12;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private PathEffect pathEffect = new DashPathEffect(new float[]{dashSize, dashSize}, 0);
    private Path path = new Path();


    public CardLinearlayout(Context context) {
        super(context);
        init();
    }


    public CardLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.reset();
        path.moveTo(0, 0);
        path.lineTo(0, getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.save();
        int childCount = getChildCount();
        if (childCount >= 2) {
            for (int i = 0; i < childCount - 1; i++) {
                canvas.translate(getChildAt(i).getWidth(), 0);


                mPaint.setColor(lineColor);
                mPaint.setStrokeWidth(lineSize);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setPathEffect(pathEffect);
                canvas.drawPath(path, mPaint);

                mPaint.setColor(cicleColor);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(0, 0, cornerSize, mPaint);
                canvas.drawCircle(0, getHeight(), cornerSize, mPaint);
            }
        }
        canvas.restore();
    }
}
