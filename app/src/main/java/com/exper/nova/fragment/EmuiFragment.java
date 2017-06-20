package com.exper.nova.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.exper.nova.MainActivity;
import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.viewpager.FragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class EmuiFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.indicatorView)
    ImageView mIndicatorView;

    private List<EmuiItemFragment> mFragmentList = new ArrayList<>();
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;

    private int mCurrentPosition;

    @Override
    protected int attachLayoutRes() {
        return R.layout.emui_fragment;
    }

    @Override
    protected void initViews() {
        mFragmentList.add(new EmuiItemFragment(R.drawable.emui_item_holder_1,R.raw.emui_tese1));
        mFragmentList.add(new EmuiItemFragment(R.drawable.emui_item_holder_2,R.raw.emui_tese2));
        mFragmentList.add(new EmuiItemFragment(R.drawable.emui_item_holder_3,R.raw.emui_tese3));
        mFragmentList.add(new EmuiItemFragment(R.drawable.emui_item_holder_4,R.raw.emui_tese4));
        mFragmentList.add(new EmuiItemFragment(R.drawable.emui_item_holder_5,R.raw.emui_tese5));
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getChildFragmentManager(),mFragmentList);
    }

    @Override
    protected void updateViews() {
        mViewPager.setAdapter(fragmentViewPagerAdapter);
        mCurrentPosition = mFragmentList.size() * 100;
        mViewPager.setCurrentItem(mCurrentPosition);
        mViewPager.addOnPageChangeListener(this);
    }

    @OnClick({R.id.btn_back,R.id.btn_menu})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                ((MainActivity)getActivity()).clearAllFragment();
                break;
            case R.id.btn_menu:
                ((MainActivity)getActivity()).openDrawer();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        int current = mCurrentPosition % mFragmentList.size();

        if(current == 0){
            mIndicatorView.setImageResource(R.drawable.emui_t1);
        }else if(current == 1){
            mIndicatorView.setImageResource(R.drawable.emui_t2);
        }else if(current == 2){
            mIndicatorView.setImageResource(R.drawable.emui_t3);
        }else if(current == 3){
            mIndicatorView.setImageResource(R.drawable.emui_t4);
        }else if(current == 4){
            mIndicatorView.setImageResource(R.drawable.emui_t5);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentList.clear();
        mFragmentList = null;
    }
}
