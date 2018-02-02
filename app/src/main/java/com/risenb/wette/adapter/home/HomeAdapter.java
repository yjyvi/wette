package com.risenb.wette.adapter.home;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.risenb.wette.R;
import com.risenb.wette.beans.BannerBean;
import com.risenb.wette.beans.GoodsListBean;
import com.risenb.wette.beans.HomeBean;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.ui.home.ProductDetailsUI;
import com.risenb.wette.utils.GlideImgUtils;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.views.MyViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;


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
    private List<BannerBean.ResultdataBean> mResultBannerBean;
    private HomeBean homeDataBean;
    private GoodsListBean goodsListBean;


    public void setGoodsListBean(GoodsListBean goodsListBean) {
        this.goodsListBean = goodsListBean;
    }

    public void setHomeDataBean(HomeBean homeDataBean) {
        this.homeDataBean = homeDataBean;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case BANNER:
//                initTestData();
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

    private void initTestData() {
        mResultBannerBean = new ArrayList<>();
        BannerBean.ResultdataBean e = new BannerBean.ResultdataBean();
        BannerBean.ResultdataBean e2 = new BannerBean.ResultdataBean();
        BannerBean.ResultdataBean e3 = new BannerBean.ResultdataBean();
        e.setBanner_Url("http://img5.imgtn.bdimg.com/it/u=104961686,3757525983&fm=27&gp=0.jpg");
        e2.setBanner_Url("http://pic21.photophoto.cn/20111106/0020032891433708_b.jpg");
        e3.setBanner_Url("http://img0.imgtn.bdimg.com/it/u=442310530,2243332126&fm=214&gp=0.jpg");
        mResultBannerBean.add(e);
        mResultBannerBean.add(e2);
        mResultBannerBean.add(e3);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                if (holder instanceof HomeBannerViewHolder) {
                    HomeBannerViewHolder bannerViewHolder = (HomeBannerViewHolder) holder;
                    if (homeDataBean != null && homeDataBean.getRecommendGoods().size() > 0) {
                        if (homeDataBean.getRecommendGoods().size() == 1) {
                            bannerViewHolder.iv_item_banner.setVisibility(View.VISIBLE);
                            GlideImgUtils.loadImg(bannerViewHolder.itemView.getContext(),homeDataBean.getRecommendGoods().get(0).getCover(), bannerViewHolder.iv_item_banner);
                            bannerViewHolder.iv_item_banner.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //跳转事件
                                    if (homeDataBean.getRecommendGoods().get(0).getCover() != null && !TextUtils.isEmpty(homeDataBean.getRecommendGoods().get(0).getCover())) {
                                        ToastUtils.showToast(homeDataBean.getRecommendGoods().get(0).getCover());
                                    }
                                }
                            });
                        } else {
                            bannerViewHolder.vp_item_banner.setAdapter(new BannerViewPagerAdapter<HomeBean.RecommendGoodsBean>(homeDataBean.getRecommendGoods()) {
                                @Override
                                public String getImageUrl(int position) {
                                    return mResultBannerBean.get(position).getBanner_Url();
                                }

                                @Override
                                public void onItemClickListener(HomeBean.RecommendGoodsBean data, View view) {
                                    //跳转事件
                                    if (data.getCover() != null && !TextUtils.isEmpty(data.getCover().trim())) {
                                        ToastUtils.showToast(data.getCover());
                                    }
                                }
                            });
                            bannerViewHolder.vpi_item_banner_indicator.bindBannerViewPager(bannerViewHolder.vp_item_banner, homeDataBean.getRecommendGoods().size());
                        }
                    }
                }
                break;
            case HOT:
                if (holder instanceof HomeHotViewHolder) {
                    HomeHotViewHolder hotViewHolder = (HomeHotViewHolder) holder;

                }
                break;
            case NEW_LIST:
                if (holder instanceof HomeNewListViewHolder) {
                    HomeNewListViewHolder newListViewHolder = (HomeNewListViewHolder) holder;
                    newListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ProductDetailsUI.start(view.getContext());
                        }
                    });
                }
                break;
            default:
                break;
        }
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
        return goodsListBean == null ? 2 : goodsListBean.getData().size() + 2;
    }

    class HomeBannerViewHolder extends BaseViewHolder {

        ViewPager vp_item_banner;
        MyViewPagerIndicator vpi_item_banner_indicator;
        ImageView iv_item_banner;

        public HomeBannerViewHolder(View view) {
            super(view);

            vp_item_banner = (ViewPager) itemView.findViewById(R.id.vp_item_banner);
            vpi_item_banner_indicator = (MyViewPagerIndicator) itemView.findViewById(R.id.vpi_item_banner_indicator);
            iv_item_banner = (ImageView) itemView.findViewById(R.id.iv_item_banner);
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
