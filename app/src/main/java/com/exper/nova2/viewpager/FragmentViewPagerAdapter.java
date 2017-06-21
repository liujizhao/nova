package com.exper.nova2.viewpager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.exper.nova2.fragment.EmuiItemFragment;

import java.util.List;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<EmuiItemFragment> mFragmentList;

    public FragmentViewPagerAdapter(FragmentManager fm,List<EmuiItemFragment> fragments)
    {
        super(fm);
        mFragmentList = fragments;
    }

    @Override
    public EmuiItemFragment getItem(int position) {
        return mFragmentList.get(position % mFragmentList.size());
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
