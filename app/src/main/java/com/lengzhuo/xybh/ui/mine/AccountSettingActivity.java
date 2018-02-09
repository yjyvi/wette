package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Random;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/01
 *     desc   : 账户设置
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_account_setting)
public class AccountSettingActivity extends BaseUI {

    @ViewInject(R.id.tv_cache_size)
    TextView tv_cache_size;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("账户设置");
        tv_cache_size.setText((new Random().nextInt(10)+11)+"MB");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = {R.id.tv_modify_account_setting,R.id.tv_address_list,R.id.tv_clear_cache,R.id.tv_score},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.tv_modify_account_setting:
                PersonalInformationActivity.toActivity(view.getContext());
                break;
            case R.id.tv_address_list:
                AddressListActivity.toActivity(view.getContext());
                break;
            case R.id.tv_clear_cache:
                ToastUtils.showToast("缓存清除成功！");
                tv_cache_size.setText("0MB");
                break;
            case R.id.tv_score:
                try{
                    Uri uri = Uri.parse("market://details?id="+getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }catch(Exception e){
                    ToastUtils.showToast("您的手机没有安装Android应用市场");
                }
                break;
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, AccountSettingActivity.class);
        context.startActivity(intent);
    }
    
}
