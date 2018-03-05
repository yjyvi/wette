package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.NetBaseBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 * 评价商品
 */

public class CommentOrderP extends PresenterBase {

    private CommentOrderListener mCommentOrderListener;

    public CommentOrderP(CommentOrderListener commentOrderListener) {
        this.mCommentOrderListener = commentOrderListener;
    }


    public void setCommentOrder(String goodsId, String orderGid, String content) {
        NetworkUtils.getNetworkUtils().goodComment(goodsId, orderGid, content, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mCommentOrderListener.commentField();
            }

            @Override
            public void requestSuccess(String result) {
                NetBaseBean netBaseBean = JSON.parseObject(result, NetBaseBean.class);
                if (netBaseBean != null) {
                    if (TextUtils.equals(REQUEST_SUCCESS, netBaseBean.getStatus())) {
                        mCommentOrderListener.commentSuccess();
                    } else {
                        ToastUtils.showToast(netBaseBean.errorMsg);
                        mCommentOrderListener.commentField();
                    }
                }
            }
        });
    }

    public interface CommentOrderListener {
        void commentSuccess();

        void commentField();
    }
}
