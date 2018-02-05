package com.risenb.wette.utils;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Utils {

    public static boolean isEmpty(String [] hints, EditText... editTexts) {
        boolean result = true;
        for (int i = 0; i < editTexts.length; i++) {
            if(TextUtils.isEmpty(editTexts[i].getText().toString().trim())){
                result = false;
                ToastUtils.showToast(hints[i]);
                break;
            }
        }
        return result;
    }

}
