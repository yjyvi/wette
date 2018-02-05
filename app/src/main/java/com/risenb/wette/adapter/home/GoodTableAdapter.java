package com.risenb.wette.adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodTableAdapter extends FragmentPagerAdapter {
    private  List<Fragment> fragmentLists;

    public GoodTableAdapter(FragmentManager supportLoaderManager, List<Fragment> fragmentLists) {
        super(supportLoaderManager);
        this.fragmentLists=fragmentLists;
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }


}
