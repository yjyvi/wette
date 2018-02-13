package com.lengzhuo.xybh.ui.home.productDetial;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.beans.BannerBean;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.lengzhuo.xybh.beans.GoodSkuBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.pop.PopUtils;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.ui.home.AddCartP;
import com.lengzhuo.xybh.ui.home.AddressSelectedUi;
import com.lengzhuo.xybh.ui.home.CreateOrderUI;
import com.lengzhuo.xybh.ui.home.GoodsSkuP;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;
import com.lengzhuo.xybh.utils.evntBusBean.AddressEvent;
import com.lengzhuo.xybh.utils.evntBusBean.GoodDetailsEvent;
import com.lengzhuo.xybh.views.MyViewPagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import yjyvi.lib.arl.AutoRollLayout;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodFragment extends LazyLoadFragment implements GoodsSkuP.GoodsSkuListener, AddCartP.AddCartListener {


    //样式选择
    @ViewInject(R.id.iv_selected_style)
    private ImageView iv_selected_style;

    //选择地址
    @ViewInject(R.id.ll_selected_address)
    private LinearLayout ll_selected_address;
    //选择地址线
    @ViewInject(R.id.iv_address_line)
    private View iv_address_line;

    @ViewInject(R.id.iv_reduce)
    private ImageView iv_reduce;

    @ViewInject(R.id.iv_add)
    private ImageView iv_add;

    @ViewInject(R.id.tv_goods_num)
    private TextView tv_goods_num;

    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.tv_tel)
    private TextView tv_tel;

    @ViewInject(R.id.tv_address)
    private TextView tv_address;

    @ViewInject(R.id.tv_good_name)
    private TextView tv_good_name;

    @ViewInject(R.id.tv_good_price)
    private TextView tv_good_price;

    @ViewInject(R.id.tv_current_page)
    private TextView tv_current_page;

    @ViewInject(R.id.tv_total_page)
    private TextView tv_total_page;

    @ViewInject(R.id.vp_item_banner)
    private AutoRollLayout vp_item_banner;

    @ViewInject(R.id.vpi_item_banner_indicator)
    private MyViewPagerIndicator vpi_item_banner_indicator;

    @ViewInject(R.id.iv_item_banner)
    private ImageView iv_item_banner;

    /**
     * 商品添加最大数量
     */
    private int GOODS_NUM_MAX = 100;
    private ArrayList<BannerBean.ResultdataBean> mResultBannerBean;
    public String mColorContent = "";
    public String mSizeContent = "";
    public AddressBean mAddressBean;
    public GoodDetailsBean.DataBean mDataBean;
    private GoodsSkuP mGoodsSkuP;
    private boolean isAddCart;
    private int mAddressId;
    private AddCartP mAddCartP;
    private static boolean isFirst = false;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    protected void setControlBasis() {
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirst) {
            ll_selected_address.setVisibility(View.VISIBLE);
            iv_address_line.setVisibility(View.VISIBLE);
            getAddressList();
        }

    }

    @Override
    protected void prepareData() {
        mGoodsSkuP = new GoodsSkuP(this);
        mAddCartP = new AddCartP(this);
        getAddressList();
    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static GoodFragment newInstance() {
        Bundle bundle = new Bundle();
        GoodFragment productFragment = new GoodFragment();
        productFragment.setArguments(bundle);
        return productFragment;
    }

    @Event(value = {
            R.id.iv_selected_style,
            R.id.ll_selected_address,
            R.id.iv_reduce,
            R.id.iv_add
    }, type = View.OnClickListener.class)
    private void onClick(final View view) {
        switch (view.getId()) {
            case R.id.iv_selected_style:
                stylePop("立即购买");
                break;
            case R.id.ll_selected_address:
                if (isLoginClick()) return;
                isFirst = false;
                //选择地址
                AddressSelectedUi.start(view.getContext());
                break;
            case R.id.iv_reduce:
                numAddOrReduce(false);
                break;
            case R.id.iv_add:
                numAddOrReduce(true);
                break;
            default:
                break;
        }
    }


    /**
     * 选择商品规格样式
     */
    private void stylePop(String buttonText) {
        PopUtils.showGoodsStyle2(buttonText, getActivity(), iv_selected_style, mDataBean, new PopUtils.GoodsSelectedStyleListener() {
            @Override
            public void selectedResult(String result) {
                mGoodsSkuP.setGoodsSku(String.valueOf(mDataBean.getGoodsId()), result);
            }
        });
    }


    /**
     * 加减
     *
     * @param isAdd
     */
    private void numAddOrReduce(boolean isAdd) {
        String num = tv_goods_num.getText().toString().trim();
        int i = Integer.parseInt(num);
        if (isAdd && i < GOODS_NUM_MAX) {
            i++;
        } else if (i > 1) {
            i--;
        }
        tv_goods_num.setText(String.valueOf(i));

        EventBus.getDefault().post(new GoodDetailsEvent().setEventType(GoodDetailsEvent.GOOD_NUM).setData(tv_goods_num.getText().toString().trim()));

    }


    @Subscribe
    public void goodsEvent(GoodDetailsEvent goodDetailsEvent) {
        switch (goodDetailsEvent.getEventType()) {
            case GoodDetailsEvent.GOOD_DATA:
                mDataBean = (GoodDetailsBean.DataBean) goodDetailsEvent.getData();
                if (mDataBean != null) {
                    tv_good_name.setText(mDataBean.getGoodsName());
                    tv_good_price.setText("¥" + mDataBean.getPrice());
                    initBannerData();
                }
                break;
            case GoodDetailsEvent.SELECTED_STYLE:
                isAddCart = false;
                stylePop("立即购买");
                break;
            case GoodDetailsEvent.SELECTED_STYLE_ADD_CART:
                isAddCart = true;
                stylePop("加入购物车");
                break;
            default:
                break;
        }

    }


    /**
     * 地址列表的EvenBus事件
     *
     * @param addressEvent
     */
    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {
        if (addressEvent.getEventType() == AddressEvent.SELECTED_ADDESS) {
            AddressBean data = (AddressBean) addressEvent.getData();
            initShowAddressText(data);
        }
    }


    /**
     * 转换轮播图数据
     */
    private void initBannerData() {
        String carousel = mDataBean.getCarousel();
        if (!TextUtils.isEmpty(carousel)) {
            mResultBannerBean = new ArrayList<>();
            List<String> banners2 = new ArrayList<>();

            String[] banners = carousel.split(",");

            for (int i = 0; i < banners.length; i++) {
                BannerBean.ResultdataBean banner = new BannerBean.ResultdataBean();
                banner.setBanner_Url(banners[i]);
                mResultBannerBean.add(banner);
                banners2.add(banners[i]);
            }

//            bannerInit();
            vp_item_banner.setAutoRoll(true);
            vp_item_banner.setPhotoPre(true);
            vp_item_banner.setItems(banners2);
            tv_total_page.setText(String.valueOf("/" + banners2.size()));
            vp_item_banner.setCurrentItemListener(new AutoRollLayout.CurrentItemListener() {
                @Override
                public void currentItemPosition(int position) {

                    tv_current_page.setText(String.valueOf(position));
                }
            });

        }
    }


    /**
     * 初始化轮播图
     */
    private void bannerInit() {
//        if (mResultBannerBean != null && mResultBannerBean.size() > 0) {
//            if (mResultBannerBean.size() == 1) {
//                iv_item_banner.setVisibility(View.VISIBLE);
//                GlideImgUtils.loadImg(getActivity(), mResultBannerBean.get(0).getBanner_Url(), iv_item_banner);
//                iv_item_banner.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //跳转事件
//                        if (mResultBannerBean.get(0).getBanner_Url() != null && !TextUtils.isEmpty(mResultBannerBean.get(0).getBanner_Url())) {
//                            ToastUtils.showToast(mResultBannerBean.get(0).getBanner_Url());
//                        }
//                    }
//                });
//            } else {
//                vp_item_banner.setAdapter(new BannerViewPagerAdapter<BannerBean.ResultdataBean>(mResultBannerBean) {
//                    @Override
//                    public String getImageUrl(int position) {
//                        return mResultBannerBean.get(position).getBanner_Url();
//                    }
//
//                    @Override
//                    public void onItemClickListener(BannerBean.ResultdataBean data, View view) {
//                        //跳转事件
//                        if (data.getBanner_LinkUrl() != null && !TextUtils.isEmpty(data.getBanner_LinkUrl().trim())) {
//                            ToastUtils.showToast(data.getBanner_LinkUrl());
//                        }
//                    }
//                });
//                vpi_item_banner_indicator.bindBannerViewPager(vp_item_banner, mResultBannerBean.size());
//                tv_total_page.setText(String.valueOf("/" + mResultBannerBean.size()));
//                vpi_item_banner_indicator.setCurrentPositionListener(new MyViewPagerIndicator.CurrentPositionListener() {
//                    @Override
//                    public void currentPosition(int position) {
//                        tv_current_page.setText(String.valueOf(position & mResultBannerBean.size()));
//                    }
//                });
//            }
//        }
    }


    /**
     * 获取地址列表
     */
    private void getAddressList() {
        isFirst = true;
        if (!UserManager.isLogin()) {
            ll_selected_address.setVisibility(View.GONE);
            iv_address_line.setVisibility(View.GONE);
            return;
        }
        NetworkUtils.getNetworkUtils().getAddressList(new CommonCallBack<List<AddressBean>>() {
            @Override
            protected void onSuccess(List<AddressBean> data) {
                //获取到默认地址
                for (AddressBean datum : data) {
                    if (1 == datum.getIsDefault()) {
                        initShowAddressText(datum);
                    }
                }
            }
        });
    }

    /**
     * 显示地址信息
     *
     * @param datum
     */
    private void initShowAddressText(AddressBean datum) {
        mAddressBean = datum;
        mAddressId = datum.getAddressId();
        tv_name.setText(String.format(getResources().getString(R.string.default_name), datum.getAddressee()));
        tv_tel.setText(String.format(getResources().getString(R.string.default_tel), datum.getTelephone()));
        tv_address.setText(String.format(getResources().getString(R.string.default_address), datum.getProvinceName() + datum.getCityName() + datum.getAreaName() + datum.getAddress()));
    }

    @Override
    public void requestSkuSuccess(GoodSkuBean.DataBean data) {
        if (data == null) {
            return;
        }

        if (isLoginClick()) return;

        if (isAddCart) {
            if (mDataBean != null) {
                mAddCartP.setAddCart(
                        String.valueOf(mDataBean.getShopId()),
                        String.valueOf(mDataBean.getGoodsId()),
                        String.valueOf(data.getSkuId()),
                        String.valueOf(mAddressId),
                        tv_goods_num.getText().toString().trim()
                );
            }
        } else {
            CreateOrderGoodsBean createOrderGoodsBean = new CreateOrderGoodsBean();
            createOrderGoodsBean.setGoodsId(String.valueOf(mDataBean.getGoodsId()));
            createOrderGoodsBean.setGoodsAmount(tv_goods_num.getText().toString().trim());
            createOrderGoodsBean.setShopId(String.valueOf(mDataBean.getShopId()));
            createOrderGoodsBean.setSkuId(String.valueOf(data.getSkuId()));
            CreateOrderUI.start(getActivity(), mAddressBean, "[" + JSON.toJSONString(createOrderGoodsBean) + "]");
        }

    }

    @Override
    public void requestSkuField() {
        ToastUtils.showToast("查询库存失败");
    }

    @Override
    public void addCartSuccess() {
        ToastUtils.showToast("添加成功");
    }

    @Override
    public void addCartField() {
        ToastUtils.showToast("添加购物车失败");
    }
}
