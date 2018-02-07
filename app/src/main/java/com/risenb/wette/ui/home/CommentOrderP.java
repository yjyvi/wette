package com.risenb.wette.ui.home;

import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;

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
                mCommentOrderListener.commentSuccess();
            }
        });
    }

    public interface CommentOrderListener {
        void commentSuccess();

        void commentField();
    }
}
