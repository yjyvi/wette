package com.lengzhuo.xybh.utils.pay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.lengzhuo.xybh.CommonConstant;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/06/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PayUtils {

    public static final int HANDLER_WHAT_ALI_PAY = 111;

    public static final String EVENT_WX_CHAT_PAY_SUCCESS = "wx_chat_pay_success";

    public static final String EVENT_WX_CHAT_PAY_FAILURE = "wx_chat_pay_failure";

    public static final String EVENT_WX_CHAT_PAY_CANCEL = "wX_chat_pay_cancel";

    public  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_WHAT_ALI_PAY:
                    PayResult payResult = new PayResult((String) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        isPaySuccess = true;
                        mPayCallBack.aliPaySuccess();
                    } else {
                        isPaySuccess = false;
                        mPayCallBack.aliPayFailure();
                    }
                    break;
                default:
                    break;

            }

        }
    };

    public Context mContext;

    private boolean isPaySuccess = false;

    private PayCallBack mPayCallBack;

    public PayUtils(Activity context) {
        mContext = context;
    }


    /**
     * 微信支付
     */
    public void wechatPay(String weChatPayParams) {
        if (!TextUtils.isEmpty(weChatPayParams)) {
            JSONObject wxParams = JSON.parseObject(weChatPayParams);

            String packageValue = wxParams.getString("package");
            String appId = wxParams.getString("appid");
            String sign = wxParams.getString("sign");
            String partnerId = wxParams.getString("partnerid");
            String prepayId = wxParams.getString("prepayid");
            String nonceStr = wxParams.getString("noncestr");
            String timeStamp = wxParams.getString("timestamp");

            IWXAPI api = WXAPIFactory.createWXAPI(mContext, CommonConstant.Common.WX_APP_ID);
            api.registerApp(CommonConstant.Common.WX_APP_ID);
            PayReq req = new PayReq();
            req.appId = appId;
            req.partnerId = partnerId;
            req.prepayId = prepayId;
            req.packageValue = packageValue;
            req.nonceStr = nonceStr;
            req.timeStamp = timeStamp;
            req.sign = sign;
            api.sendReq(req);
        }
    }

    /**
     * 支付宝支付
     */
    public void aliPay(final String sign) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                String result = alipay.pay(sign, true);
                Message msg = new Message();
                msg.what = HANDLER_WHAT_ALI_PAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    public boolean isPaySuccess() {
        return isPaySuccess;
    }

    public PayUtils setPaySuccess(boolean paySuccess) {
        isPaySuccess = paySuccess;
        return this;
    }


    public void setPayCallBack(PayCallBack payCallBack) {
        this.mPayCallBack = payCallBack;
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onWechatPayEvent(String event) {
        if (event.equals(EVENT_WX_CHAT_PAY_SUCCESS)) {
            isPaySuccess = true;
            mPayCallBack.wechatPaySuccess();
        } else if (event.equals(EVENT_WX_CHAT_PAY_FAILURE)) {
            isPaySuccess = false;
            mPayCallBack.wechatPayFailure();
        } else if (event.equals(EVENT_WX_CHAT_PAY_CANCEL)) {
            isPaySuccess = false;
            mPayCallBack.wechatPayCancel();
        }
    }

    /**
     * 支付回调
     */
    public interface PayCallBack {
        //支付宝支付成功回调
        void aliPaySuccess();

        //支付宝支付失败回调
        void aliPayFailure();

        //微信支付成功回调
        void wechatPaySuccess();

        //微信支付失败回调
        void wechatPayFailure();

        //微信支付取消回调
        void wechatPayCancel();
    }


}
