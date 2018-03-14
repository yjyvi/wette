package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lengzhuo.xybh.CommonConstant;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.PayOrderP;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.pay.PayUtils;

import org.greenrobot.eventbus.EventBus;
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

    @ViewInject(R.id.ll_pay_wx)
    private LinearLayout ll_pay_wx;

    private String payChannel = "1";
    public String mOrderNo;
    public PayOrderP mPayOrderP;
    public PayUtils mPayUtils;
    public boolean isOrderUI;


    @Override
    protected void back() {
        if (!isOrderUI) {
            OrderActivity.toActivity(this, OrderActivity.ORDER_STATE_WAITING_PAY);
        }
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("支付方式");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPayUtils.unRegisterEventBus();
    }

    @Override
    protected void prepareData() {

        iv_wx.setSelected(true);
        mPayUtils = new PayUtils(this);
        mPayUtils.registerEventBus();
        mPayUtils.setPayCallBack(this);
        mOrderNo = getIntent().getStringExtra("orderNo");
        isOrderUI = getIntent().getBooleanExtra("isOrderUI", false);
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
                mPayOrderP.setPayOrder(mOrderNo, payChannel);
                break;

            default:
                break;
        }
    }

    public static void toActivity(Context context, String orderNo, boolean isOrderUI) {
        Intent intent = new Intent(context, PaymentMethodActivity.class);
        intent.putExtra("orderNo", orderNo);
        intent.putExtra("isOrderUI", isOrderUI);
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
        payHint();
    }

    /**
     * 支付成功的提示和跳转
     */
    private void payHint() {
        ToastUtils.showToast("支付成功");
        EventBus.getDefault().post("paySuccess");
        OrderActivity.toActivity(this, OrderActivity.ORDER_STATE_SENDING_GOODS);
        finish();
    }


    /**
     * 支付失败提示及跳转
     */
    private void payFailureHint() {
        ToastUtils.showToast("支付失败");
        OrderActivity.toActivity(this, OrderActivity.ORDER_STATE_WAITING_PAY);
        finish();
    }

    @Override
    public void aliPayFailure() {
        payFailureHint();
    }
    @Override
    public void wechatPaySuccess() {
        payHint();
    }

    @Override
    public void wechatPayFailure() {
        payFailureHint();
    }

    @Override
    public void wechatPayCancel() {
        payFailureHint();
    }
}
