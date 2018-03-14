package com.lengzhuo.xybh.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lengzhuo.xybh.CommonConstant;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.mine.BindPhoneNumberActivity;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/3/13.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWXAPI api = WXAPIFactory.createWXAPI(this, CommonConstant.Common.WX_APP_ID, false);
        api.handleIntent(getIntent(), this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    SendAuth.Resp resp = (SendAuth.Resp) baseResp;
                    String code = resp.code;
                    getAccessToken(code);
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    break;
                default:
                    break;
            }
        } else {
            String result;
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    result = "分享成功";
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    result = null;
                    break;
                default:
                    result = "分享失败";
                    break;
            }
            if (result != null) {
                ToastUtils.showToast(result);
            }
        }
    }


    /**
     * 获取Token
     *
     * @param code
     */
    private void getAccessToken(String code) {
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                .concat(CommonConstant.Common.WX_APP_ID)
                .concat("&secret=")
                .concat(CommonConstant.Common.WX_SECRET)
                .concat("&code=")
                .concat(code)
                .concat("&grant_type=authorization_code");

        OKHttpManager.getAsync(accessTokenUrl, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {

            }

            @Override
            public void requestSuccess(String result) {
                Log.e("WXEntryActivity", "getAccessToken=" + result);
                JSONObject jsonObject = JSON.parseObject(result);
                String openid = jsonObject.getString("openid");
                String accessToken = jsonObject.getString("access_token");
                getUserInfo(openid, accessToken);
            }
        });
    }


    /**
     * 授权后获取微信端的用户信息
     *
     * @param openid
     * @param accessToken
     */
    private void getUserInfo(String openid, String accessToken) {
        if (TextUtils.isEmpty(openid)) {
            return;
        }

        if (TextUtils.isEmpty(accessToken)) {
            return;
        }

        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                .concat(accessToken)
                .concat("&openid=")
                .concat(openid);

        OKHttpManager.getAsync(path, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {

            }

            @Override
            public void requestSuccess(String result) {
                Log.e("WXEntryActivity", "getUserInfo=" + result);
                JSONObject jsonObject = JSON.parseObject(result);
                String openid1 = jsonObject.getString("openid");
                String nickname = jsonObject.getString("nickname");
                String headimgurl = jsonObject.getString("headimgurl");

                BindPhoneNumberActivity.toActivity(WXEntryActivity.this);

            }
        });
    }
}
