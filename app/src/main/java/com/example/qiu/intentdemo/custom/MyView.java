package com.example.qiu.intentdemo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/2/25.
 */

public class MyView extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10;//sp
    private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACH = 0XFFD3D6DA;
    private static final int DEFAULT_HEIGHT_UNREACH = 2;
    private static final int DEFAULT_COLOR_REACH = 0XFFD3D6DA;
    private static final int DEFAULT_HEIGHT_REACH = 2;//dp
    private static final int DEFAULT_TEXT_OFFSET = 10;//dp
    private int mTextSize = sp2sp(DEFAULT_TEXT_SIZE);
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private int mUnReachColor = DEFAULT_COLOR_UNREACH;
    private int mUnReachHeight = dp2px(DEFAULT_HEIGHT_UNREACH);
    private int mReacherColor = DEFAULT_COLOR_REACH;
    private int mReachHeight = dp2px(DEFAULT_HEIGHT_REACH);
    private int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);
    private Paint mpaint = new Paint();
    private int mRealWidth;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }
    public MyView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        obtainStyedAttrs(attrs);
    }

    private void obtainStyedAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyView);
        mTextSize = (int) ta.getDimension(R.styleable.MyView_progress_text_size, mTextSize);
        mTextColor = ta.getColor(R.styleable.MyView_progress_text_color, mTextColor);
        mUnReachColor = ta.getColor(R.styleable.MyView_progress_reach_color, mReacherColor);
        mUnReachHeight = (int) ta.getDimension(R.styleable.MyView_progress_unreach_height, mUnReachHeight);
        mReacherColor = ta.getColor(R.styleable.MyView_progress_reach_color, mReacherColor);
        mReachHeight = (int) ta.getDimension(R.styleable.MyView_progress_reach_height, mReachHeight);
        mTextOffset = (int) ta.getDimension(R.styleable.MyView_progress_text_offset, mTextOffset);
        mpaint.setTextSize(mTextSize);
        ta.recycle();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthval = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(widthval, height);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            int textheight = (int) (mpaint.descent() - mpaint.ascent());
            result = getPaddingTop() + getPaddingBottom() + Math.max((Math.max(mReachHeight, mUnReachColor)), Math.abs(textheight));
        }
        if (mode == MeasureSpec.AT_MOST) {
            result = Math.min(result, size);
        }
        return result;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);
        boolean noNeedUnrech = false;
        int a=getProgress();

        float radio = getProgress() * 1.0f / getMax();
        String text = a + "%";
        int textWidth = (int) mpaint.measureText(text);
        float progressX = radio * mRealWidth;
        if (progressX + textWidth > mRealWidth) {
            progressX = mRealWidth - textWidth;
            noNeedUnrech = true;
        }

        float endX = progressX - mTextOffset / 2;
        if (endX > 0) {
            mpaint.setColor(mReacherColor);
            mpaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0, 0, endX, 0, mpaint);
        }

        //draw text
        mpaint.setColor(mTextColor);
        int y = (int) -((mpaint.descent() + mpaint.ascent()) / 2);
        canvas.drawText(text, progressX, y, mpaint);

        //draw unreacherbar
        if (!noNeedUnrech) {
            float start = progressX + mTextOffset / 2 + textWidth;
            mpaint.setColor(mUnReachColor);
            mpaint.setStrokeWidth(mUnReachHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mpaint);
        }

        canvas.restore();
    }

    private int dp2px(int dpval) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpval, getResources().getDisplayMetrics());
    }

    private int sp2sp(int spval) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spval, getResources().getDisplayMetrics());
    }
}
