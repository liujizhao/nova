package com.exper.nova.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Blank on 2017/6/15.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> mViews;

    public ViewPagerAdapter(List<View> views) {
        mViews = views;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position % mViews.size()));
        return mViews.get(position % mViews.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position % mViews.size()));
    }

}
