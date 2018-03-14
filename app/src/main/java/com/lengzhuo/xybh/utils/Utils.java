package com.lengzhuo.xybh.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

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

    public static boolean isEmpty(String[] hints, EditText... editTexts) {
        boolean result = true;
        for (int i = 0; i < editTexts.length; i++) {
            if (TextUtils.isEmpty(editTexts[i].getText().toString().trim())) {
                result = false;
                ToastUtils.showToast(hints[i]);
                break;
            }
        }
        return result;
    }

    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String getText(TextView textview) {
        return textview.getText().toString().trim();
    }

    public static boolean isShowEmptyLayout(int page, List data, View dataLayout, View emptyLayout) {
        if (page != 1) return data.isEmpty();
        dataLayout.setVisibility(data.isEmpty() ? View.GONE : View.VISIBLE);
        emptyLayout.setVisibility(!data.isEmpty() ? View.GONE : View.VISIBLE);
        return data.isEmpty();
    }

}
