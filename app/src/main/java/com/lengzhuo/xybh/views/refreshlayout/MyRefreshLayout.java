package com.lengzhuo.xybh.views.refreshlayout;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者:linzheng 日期:2017/1/4 功能:
 */

public class MyRefreshLayout extends BGARefreshLayout {

    /**
     * 接口解耦
     */
    MyRefreshLayoutListener mListener;

    boolean isLoadingMore = true;

    public MyRefreshLayout(Context context) {
        super(context);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public void onFinishInflate() {
        super.onFinishInflate();

        MyRefreshViewHolder refreshViewHolder = new MyRefreshViewHolder(getContext(), true);
        setRefreshViewHolder(refreshViewHolder);
        setDelegate(new BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

                if (mListener != null) {
                    setIsLoadingMoreEnabled(true);
                    mListener.onRefresh(refreshLayout);
                }
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {


                // 在这里加载最新数据
                if (!isLoadingMore) {
                    return false;
                } else {
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            // 加载完毕后在 UI 线程结束下拉刷新
                            if (mListener != null) {
                                mListener.onLoadMore(refreshLayout);
                            }
                        }
                    }.execute();
                }
                return true;
            }
        });
    }

    public void setMyRefreshLayoutListener(MyRefreshLayoutListener listener) {
        this.mListener = listener;
    }

    /**
     * 刷新完成
     */
    public void refreshComplete() {
        endRefreshing();
    }

    /**
     * 加载完成
     */
    public void loadMoreComplete() {
        endLoadingMore();
    }

    /**
     * 设置是否可以使用上拉加载
     *
     * @param isLoadingMoreEnabled false 为不能上拉加载
     */
    public void setIsLoadingMoreEnabled(boolean isLoadingMoreEnabled) {
        isLoadingMore = isLoadingMoreEnabled;
    }

}
