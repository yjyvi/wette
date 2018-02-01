package com.risenb.wette.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.risenb.wette.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * 作者:linzheng
 * 日期:2016/12/29
 * 功能: 实现本APP风格的ViewPager指示器
 * 实现 1.无限轮播的指示器和自动播放
 * 2.普通ViewPager的指示器
 */

public class MyViewPagerIndicator extends AutoLinearLayout {

    private static final int MESSAGE_WHAT_NEXT_ITEM = 0x003;

    private ViewPager mViewPager;

    /**
     * 点与点之间的margint
     */
    private int mDotImageViewLeftMargin;

    /**
     * 点图片选中状态的图片资源ID
     */
    private int mDotImageSelectedResId = R.mipmap.home_page_banner_dot_selected;

    /**
     * 点图片未选中状态的图片资源ID
     */
    private int mDotImageUnSelectedResId = R.mipmap.home_page_banner_dot_unselected;

    private int delayed = 3000;

    /**
     * 是否绑定的是 BannerViewPager
     */
    private boolean isBindBannerViewPager = false;

    /**
     * 当前轮播图位置
     */
    private CurrentPositionListener currentPositionListener;


    public void setCurrentPositionListener(CurrentPositionListener currentPositionListener) {
        this.currentPositionListener = currentPositionListener;
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_WHAT_NEXT_ITEM) {
                if (isBindBannerViewPager) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                } else {
                    if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) {
                        mViewPager.setCurrentItem(0, false);
                    } else {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    }
                }
                if (currentPositionListener != null) {
                    currentPositionListener.currentPosition(mViewPager.getCurrentItem());
                }
                mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_NEXT_ITEM, delayed);
            }
        }
    };

    public interface CurrentPositionListener {
        void currentPosition(int position);
    }

    private ViewPager.OnPageChangeListener mListener;
    public int mItem;

    public MyViewPagerIndicator(Context context) {
        super(context);
        initial();
    }

    public MyViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial();
    }

    public MyViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initial();
    }

    /**
     * 初始化
     */
    private void initial() {
        //强制设置
        setOrientation(HORIZONTAL);

        mDotImageViewLeftMargin = AutoUtils.getPercentWidthSize(10);
        mListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setViewPagerIndicator(MyViewPagerIndicator.this, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    /**
     * 设置无限轮播图
     *
     * @param viewPager ViewPager 对象
     * @param count     一轮Item的个数
     */
    public void bindBannerViewPager(final ViewPager viewPager, final int count) {
        if (viewPager == null) return;
        if (viewPager.getAdapter() == null) return;
        if (count == 1) {
            bindViewPager(viewPager);
            return;
        }
        isBindBannerViewPager = true;
        removeAllViews();
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int newPosition = position % count;
                setViewPagerIndicator(MyViewPagerIndicator.this, newPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addDotView(count);
        mItem = 500 - (500 % count);
        viewPager.setCurrentItem(mItem);
        mHandler.removeMessages(MESSAGE_WHAT_NEXT_ITEM);
        mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_NEXT_ITEM, delayed);
    }

    public int getItem() {
        return mItem;
    }

    /**
     * 绑定 ViewPager 有自动滚动
     * 此方法必须在viewpager setAdapter 之后调用
     *
     * @param viewPager        ViewPager 对象
     * @param isShouldAutoRoll 是否需要自动滚动
     */
    public void bindViewPager(ViewPager viewPager, boolean isShouldAutoRoll) {
        bindViewPager(viewPager, 0);
        if (mViewPager.getAdapter().getCount() > 1 && isShouldAutoRoll) {
        }
    }

    /**
     * 绑定 ViewPager 没有自动滚动和无限轮播
     * 此方法必须在viewpager setAdapter 之后调用
     *
     * @param viewPager ViewPager对象
     */
    public void bindViewPager(ViewPager viewPager) {
        bindViewPager(viewPager, 0);
    }

    /**
     * 绑定 ViewPager 没有自动滚动和无限轮播
     * 此方法必须在viewpager setAdapter 之后调用
     *
     * @param index 默认选中的postion
     */
    public void bindViewPager(ViewPager viewPager, int index) {
        if (viewPager == null) return;
        if (viewPager.getAdapter() == null) return;
        isBindBannerViewPager = false;
        removeAllViews();
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(mListener);
        int count = viewPager.getAdapter().getCount();
        addDotView(count);
        viewPager.setCurrentItem(index);
    }

    /**
     * 设置viewpager 的指示器
     */
    private void setViewPagerIndicator(LinearLayout indicatorLayout, int position) {
        if (indicatorLayout.getChildCount() == 0) return;
        for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
            if (i == position) {
                ((ImageView) indicatorLayout.getChildAt(i)).setImageResource(mDotImageSelectedResId);
            } else {
                ((ImageView) indicatorLayout.getChildAt(i)).setImageResource(mDotImageUnSelectedResId);
            }
        }
    }

    /**
     * 添加 点 View
     */
    public void addDotView(int count) {
        removeAllViews();
        ImageView dotImageView;
        LayoutParams layoutParams;
        for (int i = 0; i < count; i++) {
            dotImageView = new ImageView(getContext());
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                layoutParams.setMargins(mDotImageViewLeftMargin, 0, 0, 0);
                dotImageView.setImageResource(mDotImageUnSelectedResId);
            } else {
                dotImageView.setImageResource(mDotImageSelectedResId);
            }
            dotImageView.setLayoutParams(layoutParams);
            addView(dotImageView);
        }
    }

    /**
     * 下一个 在无限轮播ViwePager中会有问题
     */
    public void next() {
        if (mViewPager == null || mViewPager.getAdapter() == null) return;
        if (mViewPager.getCurrentItem() <= mViewPager.getAdapter().getCount()) {
            int index = mViewPager.getCurrentItem() + 1;
            mViewPager.setCurrentItem(index);
        }
    }

    /**
     * 上一个 在无限轮播ViewPager中会有问题
     */
    public void last() {
        if (mViewPager == null || mViewPager.getAdapter() == null) return;
        if (mViewPager.getCurrentItem() >= 1) {
            int index = mViewPager.getCurrentItem() - 1;
            mViewPager.setCurrentItem(index);
        }
    }

}
