package com.lengzhuo.xybh.views.arl;

/**
 Created by Teacher on 2016/7/19.
 */
public class RollItem implements IRollItem {
    String rollItemPicUrl;
    String rollItemTitle;


    public RollItem(String rollItemPicUrl, String rollItemTitle) {

        this.rollItemPicUrl = rollItemPicUrl;
        this.rollItemTitle = rollItemTitle;
    }


    @Override
    public String getRollItemPicUrl() {
        return rollItemPicUrl;
    }

    @Override
    public String getRollItemTitle() {
        return rollItemTitle;
    }
}
