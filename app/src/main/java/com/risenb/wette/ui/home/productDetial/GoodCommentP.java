package com.risenb.wette.ui.home.productDetial;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.GoodCommentBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 *
 * @author yjyvi
 * @date 2018/2/5
 */

public class GoodCommentP extends PresenterBase {

    private GoodCommentListener mGoodCommentListener;

    public GoodCommentP(FragmentActivity fragmentActivity, GoodCommentListener goodCommentListener) {
        setActivity(fragmentActivity);
        this.mGoodCommentListener = goodCommentListener;
    }


    public void setGoodComment(String goodId, int page, int limit) {
        NetworkUtils.getNetworkUtils().getGoodEvaluate(goodId, String.valueOf(page), String.valueOf(limit), new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                ToastUtils.showToast(e.getMessage());
            }

            @Override
            public void requestSuccess(String result) {
                GoodCommentBean goodCommentBean = JSON.parseObject(result, GoodCommentBean.class);
                if (REQUEST_SUCCESS.equals(goodCommentBean.getStatus())) {
                    mGoodCommentListener.goodCommentData(goodCommentBean.getData());
                } else {
                    ToastUtils.showToast(goodCommentBean.getMsg());
                }
            }
        });
    }

    public interface GoodCommentListener {
        void goodCommentData(GoodCommentBean.DataBean dataBean);
    }
}
