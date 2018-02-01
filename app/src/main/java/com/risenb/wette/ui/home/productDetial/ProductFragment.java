package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.risenb.wette.R;
import com.risenb.wette.pop.PopUtils;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.ui.home.PayOrderUI;
import com.risenb.wette.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ProductFragment extends LazyLoadFragment implements View.OnClickListener {


    @ViewInject(R.id.iv_selected_style)
    private ImageView iv_selected_style;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    protected void setControlBasis() {
        iv_selected_style.setOnClickListener(this);
    }

    @Override
    protected void prepareData() {

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
                String colorContent = "红色,绿色,蓝色";
                String sizeContent = "M,L,XL,XXL,XXXL";
                PopUtils.showGoodsStyle(getActivity(), iv_selected_style, colorContent, sizeContent, new PopUtils.GoodsSelectedStyleListener() {
                    @Override
                    public void selectedResult(String reslut) {
                        PayOrderUI.start(view.getContext());
                        ToastUtils.showToast(reslut);
                    }
                });
                break;
            default:
                break;
        }
    }
}
