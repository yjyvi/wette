package com.lengzhuo.xybh.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lengzhuo.xybh.CommonConstant;
import com.lengzhuo.xybh.utils.pay.PayUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;



public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, CommonConstant.Common.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
                //支付失败
                EventBus.getDefault().post(PayUtils.EVENT_WX_CHAT_PAY_CANCEL);
            } else if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                //支付成功
                EventBus.getDefault().post(PayUtils.EVENT_WX_CHAT_PAY_SUCCESS);
            } else {
                //支付失败
                EventBus.getDefault().post(PayUtils.EVENT_WX_CHAT_PAY_FAILURE);
            }
        }

        finish();
    }

}