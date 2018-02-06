package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;

import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 * 评价商品
 */

public class CommentOrderP extends PresenterBase {

    public CommentOrderP(FragmentActivity fragmentActivity){
        setActivity(fragmentActivity);
    }


    public void setCommentOrder(String goodsId,  String orderGid,String content){
        NetworkUtils.getNetworkUtils().goodComment(goodsId, orderGid, content, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                ToastUtils.showToast(e.getMessage());
            }

            @Override
            public void requestSuccess(String result) {
//                JSON.parseObject(result)
            }
        });
    }

    public interface CommentOrderListener{
        void commentSuccess();
    }
}
