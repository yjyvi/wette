package com.risenb.wette.ui.classify;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.ClassifyBean;
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
 * @date 2018/2/3
 * 获取分类
 */

public class ClassifyP extends PresenterBase {
    private ClassifyListener mClassifyListener;

    public ClassifyP(FragmentActivity fragmentActivity, ClassifyListener classifyListener){
        setActivity(fragmentActivity);
        this.mClassifyListener = classifyListener;
    }


    public void setClassifyData(){
        NetworkUtils.getNetworkUtils().getGoodCategory(new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                ToastUtils.showToast(e.getMessage());
            }

            @Override
            public void requestSuccess(String result) {
                ClassifyBean classifyBean = JSON.parseObject(result,ClassifyBean.class);
                    mClassifyListener.classifyData(classifyBean.getData());
            }
        });
    }


    public interface ClassifyListener{
        void classifyData(List<ClassifyBean.DataBean> dataBean);
    }

}
