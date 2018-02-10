package com.lengzhuo.xybh.adapter.home;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.GoodsListBean;
import com.lengzhuo.xybh.beans.HomeBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.home.GoodDetailsUI;
import com.lengzhuo.xybh.utils.GlideImgUtils;
import com.lengzhuo.xybh.views.MyViewPagerIndicator;

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


    private HomeBean.DataBean homeDataBean;
    private List<GoodsListBean.DataBean> goodsListBean;


    public void setGoodsListBean(List<GoodsListBean.DataBean> goodsListBean) {
        this.goodsListBean = goodsListBean;
    }

    public void setHomeDataBean(HomeBean.DataBean homeDataBean) {
        this.homeDataBean = homeDataBean;
    }

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
        switch (getItemViewType(position)) {
            case BANNER:
                if (holder instanceof HomeBannerViewHolder) {
                    HomeBannerViewHolder bannerViewHolder = (HomeBannerViewHolder) holder;
                    if (homeDataBean != null && homeDataBean.getRecommendGoods().size() > 0) {
                        if (homeDataBean.getRecommendGoods().size() == 1) {
                            bannerViewHolder.iv_item_banner.setVisibility(View.VISIBLE);
                            GlideImgUtils.loadImg(bannerViewHolder.itemView.getContext(), homeDataBean.getRecommendGoods().get(0).getCover(), bannerViewHolder.iv_item_banner);
                            bannerViewHolder.iv_item_banner.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //跳转事件
                                    if (homeDataBean.getRecommendGoods().get(0).getCover() != null && !TextUtils.isEmpty(homeDataBean.getRecommendGoods().get(0).getCover())) {
//                                        ToastUtils.showToast(homeDataBean.getRecommendGoods().get(0).getCover());
                                    }
                                }
                            });
                        } else {
                            bannerViewHolder.vp_item_banner.setAdapter(new BannerViewPagerAdapter<HomeBean.DataBean.RecommendGoodsBean>(homeDataBean.getRecommendGoods()) {
                                @Override
                                public String getImageUrl(int position) {
                                    return homeDataBean.getRecommendGoods().get(position).getCover();
                                }

                                @Override
                                public void onItemClickListener(HomeBean.DataBean.RecommendGoodsBean data, View view) {
                                    //跳转事件
                                    if (data.getCover() != null && !TextUtils.isEmpty(data.getCover().trim())) {
//                                        ToastUtils.showToast(data.getCover());
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
                    if (homeDataBean != null) {
                        GlideImgUtils.loadImg(hotViewHolder.itemView.getContext(), homeDataBean.getSpecial().getCover(), hotViewHolder.iv_hot_left);
                        GlideImgUtils.loadImg(hotViewHolder.itemView.getContext(), homeDataBean.getSales().getCover(), hotViewHolder.iv_hot_right);
                        GlideImgUtils.loadImg(hotViewHolder.itemView.getContext(), homeDataBean.getActity().getCover(), hotViewHolder.iv_new_client_share);
                        hotViewHolder.tv_activity.setText(homeDataBean.getActity().getTitle());
                    }

                }
                break;
            case NEW_LIST:
                if (holder instanceof HomeNewListViewHolder) {
                    HomeNewListViewHolder newListViewHolder = (HomeNewListViewHolder) holder;
                    if (goodsListBean != null && goodsListBean.size() > 0) {
                        final GoodsListBean.DataBean goods = goodsListBean.get(position - 2);
                        if (goods != null) {
                            newListViewHolder.tv_title.setText(goods.getGoodsName());
                            newListViewHolder.tv_introduce.setText(goods.getGoodsIntroduce());
                            newListViewHolder.tv_price.setText("¥" + goods.getPrice());
                            GlideImgUtils.loadImg(newListViewHolder.itemView.getContext(), goods.getLogo(), newListViewHolder.iv_user_icon);
                            GlideImgUtils.loadImg(newListViewHolder.itemView.getContext(), goods.getCover(), newListViewHolder.iv_img);

                            newListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    GoodDetailsUI.start(view.getContext(), String.valueOf(goods.getGoodsId()), String.valueOf(goods.getShopId()));
                                }
                            });
                        }
                    }


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
        return goodsListBean == null ? 2 : goodsListBean.size() + 2;
    }


    /**
     * 轮播图的
     */
    class HomeBannerViewHolder extends BaseViewHolder {

        private ViewPager vp_item_banner;
        private MyViewPagerIndicator vpi_item_banner_indicator;
        private ImageView iv_item_banner;

        public HomeBannerViewHolder(View view) {
            super(view);

            vp_item_banner = (ViewPager) itemView.findViewById(R.id.vp_item_banner);
            vpi_item_banner_indicator = (MyViewPagerIndicator) itemView.findViewById(R.id.vpi_item_banner_indicator);
            iv_item_banner = (ImageView) itemView.findViewById(R.id.iv_item_banner);
        }
    }


    /**
     * 中间热门
     */
    class HomeHotViewHolder extends BaseViewHolder {

        private TextView tv_activity;
        private ImageView iv_new_client_share;
        private ImageView iv_hot_left;
        private ImageView iv_hot_right;

        public HomeHotViewHolder(View view) {
            super(view);

            tv_activity = ((TextView) view.findViewById(R.id.tv_activity));
            iv_new_client_share = ((ImageView) view.findViewById(R.id.iv_new_client_share));
            iv_hot_left = ((ImageView) view.findViewById(R.id.iv_hot_left));
            iv_hot_right = ((ImageView) view.findViewById(R.id.iv_hot_right));
        }
    }


    /**
     * 最新列表
     */
    class HomeNewListViewHolder extends BaseViewHolder {

        private ImageView iv_user_icon;
        private ImageView iv_img;
        private TextView tv_price;
        private TextView tv_introduce;
        private TextView tv_title;

        public HomeNewListViewHolder(View view) {
            super(view);
            tv_price = ((TextView) view.findViewById(R.id.tv_price));
            tv_introduce = ((TextView) view.findViewById(R.id.tv_introduce));
            tv_title = ((TextView) view.findViewById(R.id.tv_title));
            tv_price = ((TextView) view.findViewById(R.id.tv_price));
            iv_user_icon = ((ImageView) view.findViewById(R.id.iv_user_icon));
            iv_img = ((ImageView) view.findViewById(R.id.iv_img));
        }
    }
}
