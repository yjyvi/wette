package com.lengzhuo.xybh.views.refreshlayout;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;

import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;


/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyRefreshViewHolder extends BGARefreshViewHolder {

    /**
     * 是否支持上拉加载更多
     */
    private boolean mIsLoadingMoreEnabled;

//    private RotateAnimation mUpAnim;

    private ImageView iv_refresh_header_icon;
    private TextView tv_refresh_header_text;

    private String mPullDownRefreshText = "已经完成刷新";
    private String mReleaseRefreshText = "松开即将刷新";
    private String mRefreshingText = "正在拼命刷新";


    /**
     * @param isLoadingMoreEnabled 上拉加载更多是否可用
     */
    public MyRefreshViewHolder(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
        mIsLoadingMoreEnabled = isLoadingMoreEnabled;
//        mUpAnim = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        mUpAnim.setDuration(150);
//        mUpAnim.setFillAfter(true);
//        mUpAnim.setRepeatCount(ValueAnimator.INFINITE);
//        mUpAnim.setRepeatMode(ValueAnimator.INFINITE);
    }

    @Override
    public View getRefreshHeaderView() {
        if(mRefreshHeaderView == null){
            mRefreshHeaderView = View.inflate(mContext, R.layout.view_refresh_header,null);
        }

        iv_refresh_header_icon = (ImageView) mRefreshHeaderView.findViewById(R.id.iv_refresh_header_icon);
        tv_refresh_header_text = (TextView) mRefreshHeaderView.findViewById(R.id.tv_refresh_header_text);

        return mRefreshHeaderView;
    }

    @Override
    public void handleScale(float scale, int moveYDistance) {
//        System.out.println("handleScale scale = " + scale);
//        iv_refresh_header_icon.setRotation(-360/scale);
    }

    @Override
    public void changeToIdle() {

    }

    @Override
    public void changeToPullDown() {
        tv_refresh_header_text.setText(mPullDownRefreshText);
    }

    @Override
    public void changeToReleaseRefresh() {
        tv_refresh_header_text.setText(mReleaseRefreshText);
    }

    @Override
    public void changeToRefreshing() {
        tv_refresh_header_text.setText(mRefreshingText);
//        iv_refresh_header_icon.startAnimation(mUpAnim);
    }

    @Override
    public void onEndRefreshing() {
        tv_refresh_header_text.setText(mPullDownRefreshText);
//        iv_refresh_header_icon.clearAnimation();
    }

//    @Override
//    public View getLoadMoreFooterView() {
//
////        if(!mIsLoadingMoreEnabled){
//            return null;
////        }
////
////        if (mLoadMoreFooterView == null) {
////            mLoadMoreFooterView = View.inflate(mContext, R.layout.view_refresh_footer,null);
////        }
////
////        return mLoadMoreFooterView;
//    }

    @Override
    public void changeToLoadingMore() {
        super.changeToLoadingMore();
    }
}
