package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.R;
import com.risenb.wette.adapter.home.BannerViewPagerAdapter;
import com.risenb.wette.beans.AddressBean;
import com.risenb.wette.beans.BannerBean;
import com.risenb.wette.beans.CreateOrderGoodsBean;
import com.risenb.wette.beans.GoodDetailsBean;
import com.risenb.wette.pop.PopUtils;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.ui.home.CreateOrderUI;
import com.risenb.wette.ui.mine.AddressListActivity;
import com.risenb.wette.ui.mine.LoginActivity;
import com.risenb.wette.utils.GlideImgUtils;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.UserManager;
import com.risenb.wette.utils.evntBusBean.GoodDetailsEvent;
import com.risenb.wette.views.MyViewPagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodFragment extends LazyLoadFragment {


    //样式选择
    @ViewInject(R.id.iv_selected_style)
    private ImageView iv_selected_style;

    //选择地址
    @ViewInject(R.id.ll_selected_address)
    private LinearLayout ll_selected_address;

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
    private ViewPager vp_item_banner;

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
    protected void prepareData() {
        initTestData();
        bannerInit();


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


    private void bannerInit() {
        if (mResultBannerBean != null && mResultBannerBean.size() > 0) {
            if (mResultBannerBean.size() == 1) {
                iv_item_banner.setVisibility(View.VISIBLE);
                GlideImgUtils.loadImg(getActivity(), mResultBannerBean.get(0).getBanner_Url(), iv_item_banner);
                iv_item_banner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转事件
                        if (mResultBannerBean.get(0).getBanner_Url() != null && !TextUtils.isEmpty(mResultBannerBean.get(0).getBanner_Url())) {
                            ToastUtils.showToast(mResultBannerBean.get(0).getBanner_Url());
                        }
                    }
                });
            } else {
                vp_item_banner.setAdapter(new BannerViewPagerAdapter<BannerBean.ResultdataBean>(mResultBannerBean) {
                    @Override
                    public String getImageUrl(int position) {
                        return mResultBannerBean.get(position).getBanner_Url();
                    }

                    @Override
                    public void onItemClickListener(BannerBean.ResultdataBean data, View view) {
                        //跳转事件
                        if (data.getBanner_LinkUrl() != null && !TextUtils.isEmpty(data.getBanner_LinkUrl().trim())) {
                            ToastUtils.showToast(data.getBanner_LinkUrl());
                        }
                    }
                });
                vpi_item_banner_indicator.bindBannerViewPager(vp_item_banner, mResultBannerBean.size());
                tv_total_page.setText(String.valueOf("/" + mResultBannerBean.size()));
                vpi_item_banner_indicator.setCurrentPositionListener(new MyViewPagerIndicator.CurrentPositionListener() {
                    @Override
                    public void currentPosition(int position) {
                        tv_current_page.setText(String.valueOf(position));
                    }
                });
            }
        }
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
                PopUtils.showGoodsStyle(getActivity(), iv_selected_style, mColorContent, mSizeContent, new PopUtils.GoodsSelectedStyleListener() {
                    @Override
                    public void selectedResult(String reslut) {
                        if (UserManager.isLogin()) {
                            CreateOrderGoodsBean createOrderGoodsBean = new CreateOrderGoodsBean();
                            createOrderGoodsBean.setGoodsId(String.valueOf(mDataBean.getGoodsId()));
                            createOrderGoodsBean.setGoodsAmount(tv_goods_num.getText().toString().trim());
                            createOrderGoodsBean.setShopId(String.valueOf(mDataBean.getShopId()));
                            createOrderGoodsBean.setSkuId("2");
                            CreateOrderUI.start(view.getContext(), mAddressBean, "["+JSON.toJSONString(createOrderGoodsBean)+"]");
                        } else {
                            ToastUtils.showToast("未登录，请求登录");
                            LoginActivity.toActivity(view.getContext());
                        }
                    }
                });
                break;

            case R.id.ll_selected_address:
                //选择地址
                AddressListActivity.toActivity(view.getContext());
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
                    StringBuilder colors = new StringBuilder();
                    StringBuilder sizes = new StringBuilder();
                    List<GoodDetailsBean.DataBean.AttrListBeanX> attrList = mDataBean.getAttrList();
                    for (int i = 0; i < attrList.size(); i++) {
                        if (attrList.get(i) != null && "颜色".equals(attrList.get(i).getAttrName())) {
                            List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList1 = attrList.get(i).getAttrList();
                            for (int j = 0; j < attrList1.size(); j++) {
                                colors.append(attrList1.get(j).getAttrName() + ",");
                            }
                        }

                        if (attrList.get(i) != null && "尺码".equals(attrList.get(i).getAttrName())) {
                            List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList1 = attrList.get(i).getAttrList();
                            for (int j = 0; j < attrList1.size(); j++) {
                                sizes.append(attrList1.get(j).getAttrName() + ",");
                            }
                        }
                    }

                    mColorContent = colors.toString();
                    mSizeContent = sizes.toString();


                    tv_good_name.setText(mDataBean.getGoodsName());
                    tv_good_price.setText("¥" + mDataBean.getPrice());
                }
                break;
            case GoodDetailsEvent.DEFAULT_ADDRESS:
                mAddressBean = (AddressBean) goodDetailsEvent.getData();
                if (mAddressBean != null) {
                    tv_name.setText(String.format(getResources().getString(R.string.default_name), mAddressBean.getAddressee()));
                    tv_tel.setText(String.format(getResources().getString(R.string.default_tel), mAddressBean.getTelephone()));
                    tv_address.setText(String.format(getResources().getString(R.string.default_address), mAddressBean.getProvinceName() + mAddressBean.getCityName() + mAddressBean.getAreaName() + mAddressBean.getAddress()));
                }
                break;
            default:
                break;
        }

    }
}
