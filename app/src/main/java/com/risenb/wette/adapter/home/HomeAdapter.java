package com.risenb.wette.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseViewHolder;


/**
 * Created by yjyvi on 2018/1/31.
 */

public class HomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    /**
     * 轮播图
     */
    public static final int BANNER = 1;

    /**
     * 广告区域
     */
    public static final int HOT = 2;

    /**
     * 最新商品列表
     */
    public static final int NEW_LIST = 3;


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case BANNER:
                baseViewHolder = new HomeAdapter.HomeBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
                break;
            case HOT:
                baseViewHolder = new HomeAdapter.HomeHotViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false));
                break;
            case NEW_LIST:
                baseViewHolder = new HomeAdapter.HomeNewListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_list, parent, false));
                break;
            default:
                break;
        }

        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else if (position == 1) {
            return HOT;
        } else {
            return NEW_LIST;
        }
    }

    @Override
    public int getItemCount() {
        return 13;
    }

    class HomeBannerViewHolder extends BaseViewHolder {
        public HomeBannerViewHolder(View view) {
            super(view);
        }
    }

    class HomeHotViewHolder extends BaseViewHolder {
        public HomeHotViewHolder(View view) {
            super(view);
        }
    }

    class HomeNewListViewHolder extends BaseViewHolder {
        public HomeNewListViewHolder(View view) {
            super(view);
        }
    }
}
