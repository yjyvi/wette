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

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.BannerViewPagerAdapter;
import com.risenb.wette.beans.BannerBean;
import com.risenb.wette.beans.ProductDetailsBean;
import com.risenb.wette.pop.PopUtils;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.ui.home.PayOrderUI;
import com.risenb.wette.ui.home.ProductDetialP;
import com.risenb.wette.utils.GlideImgUtils;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.views.MyViewPagerIndicator;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ProductFragment extends LazyLoadFragment implements View.OnClickListener, ProductDetialP.ProductDetailsListener {


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


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    protected void setControlBasis() {
        iv_selected_style.setOnClickListener(this);
        ll_selected_address.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_reduce.setOnClickListener(this);
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

        ProductDetialP productDetialP = new ProductDetialP(getActivity(),this);
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
    public static ProductFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);
        return productFragment;
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.iv_selected_style:
                String colorContent = "红色,绿色,蓝色,黑色,紫色";
                String sizeContent = "M,L,XL,XXL,XXXL,XXXXL,XXXXXL";
                PopUtils.showGoodsStyle(getActivity(), iv_selected_style, colorContent, sizeContent, new PopUtils.GoodsSelectedStyleListener() {
                    @Override
                    public void selectedResult(String reslut) {
                        PayOrderUI.start(view.getContext());
                        ToastUtils.showToast(reslut);
                    }
                });
                break;

            case R.id.ll_selected_address:
                //选择地址
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
        } else if (i > 0) {
            i--;
        }
        tv_goods_num.setText(String.valueOf(i));
    }

    @Override
    public void productData(ProductDetailsBean.DataBean dataBean) {

    }
}
