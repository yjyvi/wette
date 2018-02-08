package com.lengzhuo.xybh.adapter.home;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lengzhuo.xybh.utils.GlideImgUtils;

import java.util.List;

/**
 * 作者:linzheng 日期:2017/3/1 功能:
 */

public abstract class BannerViewPagerAdapter<T> extends PagerAdapter {

    private List<T> mBannerImageUrlList;

    @Override
    public int getCount() {
        //关于无限轮播
        //https://github.com/alibaba/UltraViewPager/issues/4
        return 1000;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //拿着position位置 % 集合.size
        final int newPosition = position % mBannerImageUrlList.size();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(lp);
        container.addView(imageView);
        GlideImgUtils.loadImg(container.getContext(),getImageUrl(newPosition),imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener(mBannerImageUrlList.get(newPosition), v);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public BannerViewPagerAdapter(List<T> bannerImageUrlList) {
        mBannerImageUrlList = bannerImageUrlList;
    }

    public abstract String getImageUrl(int position);

    public abstract void onItemClickListener(T data, View view);

}
