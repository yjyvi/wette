package com.risenb.wette.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/08/27
 *     desc   : 分割线 ItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class PaddingItemDecoration extends RecyclerView.ItemDecoration {

    Paint mPaint;

    int mPaddingLeft;

    int mPaddingRight;

    {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#e5e5e5"));
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft() + AutoUtils.getPercentWidthSize(mPaddingLeft);
        final int right = parent.getWidth() - AutoUtils.getPercentWidthSize(mPaddingRight);
        int top, bottom;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            top = child.getBottom();
            bottom = top + AutoUtils.getPercentHeightSize(1);
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, AutoUtils.getPercentHeightSize(2));
    }

    public PaddingItemDecoration setPadding(int paddingLeft, int paddingRight) {
        this.mPaddingLeft = paddingLeft;
        this.mPaddingRight = paddingRight;
        return this;
    }

}
