package com.lengzhuo.xybh.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/06
 *     desc   : 倒计时 Handler
 *     version: 1.0
 * </pre>
 */
public class ValidateHandler extends Handler {

    private TextView mTextView;

    private int mTime = 60;

    public ValidateHandler(TextView textView){
        this.mTextView = textView;
    }

    @Override
    public void handleMessage(Message msg) {
        mTextView.setClickable(false);
        mTextView.setText(--mTime+"S后重试");
        if(mTime == 0){
            mTime = 60;
            mTextView.setText("发送验证码");
            mTextView.setClickable(true);
            return;
        }
        sendEmptyMessageDelayed(1,1000);
    }

    public void startCountdown(){
        sendEmptyMessage(1);
    }

    public void onDestroy(){
        removeMessages(1);
    }

}
