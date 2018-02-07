package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.risenb.wette.CommonConstant;
import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.PayOrderP;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.pay.PayUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 支付方式
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_payment_method)
public class PaymentMethodActivity extends BaseUI implements PayOrderP.PayOrderListener, PayUtils.PayCallBack {

    @ViewInject(R.id.iv_wx)
    private ImageView iv_wx;

    @ViewInject(R.id.iv_zfb)
    private ImageView iv_zfb;

    private String payChannel = "1";
    public String mOrderId;
    public PayOrderP mPayOrderP;
    public PayUtils mPayUtils;


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("支付方式");
    }

    @Override
    protected void prepareData() {
        iv_wx.setSelected(true);
        mPayUtils = new PayUtils(this);
        mPayUtils.setPayCallBack(this);
        mOrderId = getIntent().getStringExtra("orderId");
        mPayOrderP = new PayOrderP(this);
    }

    @Event(value = {
            R.id.ll_pay_wx,
            R.id.ll_pay_zfb,
            R.id.common_title_back,
            R.id.tv_commit
    }, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_pay_wx:
                iv_wx.setSelected(true);
                iv_zfb.setSelected(false);
                payChannel = CommonConstant.Common.PAY_METHOD_WX;
                break;
            case R.id.ll_pay_zfb:
                iv_wx.setSelected(false);
                iv_zfb.setSelected(true);
                payChannel = CommonConstant.Common.PAY_METHOD_ZFB;
                break;
            case R.id.tv_commit:
                mOrderId="10410";
                mPayOrderP.setPayOrder(mOrderId, payChannel);
                break;

            default:
                break;
        }
    }

    public static void toActivity(Context context, String orderId) {
        Intent intent = new Intent(context, PaymentMethodActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

    @Override
    public void getSignSuccess(String sign) {
        payment(sign);
    }

    private void payment(String sign) {
        switch (payChannel) {
            case CommonConstant.Common.PAY_METHOD_WX:
                mPayUtils.wechatPay(sign);
                break;
            case CommonConstant.Common.PAY_METHOD_ZFB:
                mPayUtils.aliPay(sign);
                break;
            default:
                break;
        }
    }

    @Override
    public void getSignField(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void aliPaySuccess() {
        ToastUtils.showToast("支付成功");
    }

    @Override
    public void aliPayFailure() {
        ToastUtils.showToast("支付失败");
    }

    @Override
    public void wechatPaySuccess() {
        ToastUtils.showToast("支付成功");
    }

    @Override
    public void wechatPayFailure() {
        ToastUtils.showToast("支付失败");
    }

    @Override
    public void wechatPayCancel() {
        ToastUtils.showToast("支付取消");
    }
}
