package com.lengzhuo.xybh.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.lengzhuo.xybh.CommonConstant;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;


/**
 * @author yjyvi
 * @data 2018/3/13.
 */

public class WxShareUtils {

    private static Context mContext;
    public IWXAPI mApi;

    public static WxShareUtils sWxShareUtils;


    public static WxShareUtils getInstance(Context context) {
        if (sWxShareUtils == null) {
            sWxShareUtils = new WxShareUtils();
        }
        if (sWxShareUtils.mApi != null) {
            sWxShareUtils.mApi.unregisterApp();
        }
        mContext = context;
        sWxShareUtils.regToWx();
        return sWxShareUtils;
    }


    //注册应用id到微信
    private void regToWx() {
        //通过WXAPIFactory工厂，获取IWXAPI的实例
        mApi = WXAPIFactory.createWXAPI(mContext, CommonConstant.Common.WX_APP_ID, true);
        //将应用的appId注册到微信
        mApi.registerApp(CommonConstant.Common.WX_APP_ID);
    }

    /**
     * 分享网页到朋友圈或者好友，视频和音乐的分享和网页大同小异，只是创建的对象不同。
     * 详情参考官方文档：
     *
     * @param url         网页的url
     * @param title       显示分享网页的标题
     * @param description 对网页的描述
     * @param scene       分享方式：好友还是朋友圈
     */
    public boolean shareUrl(String url, String title, Bitmap thumb, String description, int scene) {
        //初试话一个WXWebpageObject对象，填写url
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = url;
        return share(webPage, title, thumb, description, scene);
    }

    private boolean share(WXMediaMessage.IMediaObject mediaObject, Bitmap thumb, int scene) {
        return share(mediaObject, null, thumb, null, scene);
    }

    private boolean share(WXMediaMessage.IMediaObject mediaObject, String description, int scene) {
        return share(mediaObject, null, null, description, scene);
    }

    private boolean share(WXMediaMessage.IMediaObject mediaObject, String title, Bitmap thumb, String description, int scene) {
        //初始化一个WXMediaMessage对象，填写标题、描述
        WXMediaMessage msg = new WXMediaMessage(mediaObject);
        if (title != null) {
            msg.title = title;
        }
        if (description != null) {
            msg.description = description;
        }
        if (thumb != null) {
            msg.thumbData = bmpToByteArray(thumb);
        }
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = scene;
        return mApi.sendReq(req);
    }

    private byte[] bmpToByteArray(final Bitmap bitmap) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > 32 && options != 10) {
            //清空output
            output.reset();
            //这里压缩options%，把压缩后的数据存放到output中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);
            options -= 10;
        }
        return output.toByteArray();
    }
}
