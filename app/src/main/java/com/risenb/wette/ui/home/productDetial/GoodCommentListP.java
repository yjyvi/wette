package com.risenb.wette.ui.home.productDetial;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.GoodCommentListBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 *
 * @author yjyvi
 * @date 2018/2/5
 */

public class GoodCommentListP extends PresenterBase {

    private GoodCommentListener mGoodCommentListener;

    public GoodCommentListP(FragmentActivity fragmentActivity, GoodCommentListener goodCommentListener) {
        setActivity(fragmentActivity);
        this.mGoodCommentListener = goodCommentListener;
    }


    public void setGoodComment(String goodId, int page, int limit) {
        NetworkUtils.getNetworkUtils().getGoodEvaluateList(goodId, String.valueOf(page), String.valueOf(limit), new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mGoodCommentListener.getCommentDataField();
            }

            @Override
            public void requestSuccess(String result) {
                GoodCommentListBean goodCommentBean = JSON.parseObject(result, GoodCommentListBean.class);
                if (REQUEST_SUCCESS.equals(goodCommentBean.getStatus())) {
                    mGoodCommentListener.goodCommentData(goodCommentBean.getData());
                } else {
                    ToastUtils.showToast(goodCommentBean.getErrorMsg());
                    mGoodCommentListener.getCommentDataField();
                }
            }
        });
    }

    public interface GoodCommentListener {
        void goodCommentData(List<GoodCommentListBean.DataBean> dataBean);
        void getCommentDataField();
    }
}
