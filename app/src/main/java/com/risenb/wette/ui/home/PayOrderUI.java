package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 *
 * @author yjyvi
 * @date 2018/2/1
 * 预览订单界面
 */
@ContentView(R.layout.activity_pay_order)
public class PayOrderUI extends BaseUI implements View.OnClickListener {

    @ViewInject(R.id.common_title_back)
    private RelativeLayout common_title_back;

    @ViewInject(R.id.ll_selected_address)
    private LinearLayout ll_selected_address;

    @ViewInject(R.id.rl_right)
    private RelativeLayout rl_right;

    @ViewInject(R.id.bt_pay)
    private Button bt_pay;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        leftVisible(R.mipmap.back);
        setImgTitle(R.mipmap.home_logo);
        rightVisible(R.mipmap.home_cart);

        bt_pay.setOnClickListener(this);

        ll_selected_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转地址界面
            }
        });

        rl_right.setOnClickListener(this);
        common_title_back.setOnClickListener(this);
    }

    @Override
    protected void prepareData() {

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PayOrderUI.class);
        context.startActivity(starter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_back:
                back();
                break;
            case R.id.ll_selected_address:
                //选择地址
                break;
            case R.id.rl_right:
                //购物车
                break;
            case R.id.bt_pay:
                CommentOderUI.start(view.getContext());
                //支付
                break;
            default:
                break;
        }
    }
}
