package com.example.admin.viewshared.customes;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.viewshared.R;

public class MyView extends View{

    int height,width,fillColor;
    Paint paint;


    public MyView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }



    public void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defstyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MyCostumFigure,defStyleAttr,defstyleRes);
        height = typedArray.getInt(R.styleable.MyCostumFigure_heigh, 40);
        width = typedArray.getInt(R.styleable.MyCostumFigure_with, 60);
        fillColor = typedArray.getColor(R.styleable.MyCostumFigure_fillColor, getResources().getColor(android.R.color.black));
         paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int minDim = Math.min(getHeight(), getWidth());
        canvas.drawRect(minDim, minDim, minDim, minDim, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int desireHeight = 150;
        int desireWidth = 150;

        //get mode and value
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //Measure width
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desireWidth, widthSize);
        }else{
            //Whatever we want
            width = desireWidth;
        }

        //Measure height
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desireHeight, heightSize);
        }else{
            //Whatever we want
            height = desireHeight;
        }

        setMeasuredDimension(width, height);

    }
}
