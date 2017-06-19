package com.exper.nova.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.exper.nova.MainActivity;
import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.util.ToastUtils;
import com.exper.nova.util.Tools;
import com.exper.nova.util.ViewPagerHandlerUtils;
import com.exper.nova.viewpager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 高清美拍
 * Created by Blank on 2017/6/15.
 */

public class HDSelfFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.indicatorLayout)
    LinearLayout mIndicatorLayout;

    private ViewPagerAdapter mViewPagerAdapter;
    private List<View> mViews = new ArrayList<>();
    private int[] mImageIds = new int[]{
            R.drawable.zhao_4,R.drawable.zhao_2,R.drawable.zhao_3,R.drawable.zhao_5,R.drawable.zhao_1,R.drawable.zhao_6
    };
    private ImageView[] mTips;

    private int mCurrentPosition;

    private ViewPagerHandlerUtils mHandlerUtils;

    @Override
    protected int attachLayoutRes() {
        return R.layout.self_hd_fragment;
    }

    @Override
    protected void initViews() {
        mTips = new ImageView[mImageIds.length];
        mIndicatorLayout.removeAllViews();
        for(int i = 0; i< mTips.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10,10));
            mTips[i] = imageView;
            if(i == 0){
                mTips[i].setBackgroundResource(R.drawable.selected);
            }else{
                mTips[i].setBackgroundResource(R.drawable.normal);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            mIndicatorLayout.addView(imageView, layoutParams);
        }

        initImageViews();
        mViewPagerAdapter = new ViewPagerAdapter(mViews);

        mHandlerUtils = new ViewPagerHandlerUtils(mViewPager);
    }

    private void initImageViews() {
        mViews.clear();
        for (int ids : mImageIds){
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setBackgroundResource(ids);
            mViews.add(imageView);
        }
    }

    @Override
    protected void updateViews() {
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mCurrentPosition = mViews.size() * 100;
        mViewPager.setCurrentItem(mCurrentPosition);
        mHandlerUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandlerUtils.sendEmptyMessageDelayed(ViewPagerHandlerUtils.MSG_UPDATE, ViewPagerHandlerUtils.MSG_DELAY);
            }
        },100);
    }

    private void setImageBackground(int selectItems){
        for(int i = 0; i< mTips.length; i++){
            if(i == selectItems){
                mTips[i].setBackgroundResource(R.drawable.selected);
            }else{
                mTips[i].setBackgroundResource(R.drawable.normal);
            }
        }
    }

    @OnClick({R.id.to_camera,R.id.learn_more,R.id.btn_back,R.id.btn_menu})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.to_camera:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                            1);
                }else{
                    Tools.launchCamera(getActivity(), Camera.CameraInfo.CAMERA_FACING_FRONT);
                }
                break;
            case R.id.learn_more:
                ((MainActivity)getActivity()).replaceFragment(R.id.content_frame,new LearnMoreFragment(),"learn_more");
                break;
            case R.id.btn_back:
                getActivity().onBackPressed();
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
        setImageBackground(mCurrentPosition % mViews.size());
        mHandlerUtils.sendMessage(Message.obtain(mHandlerUtils, ViewPagerHandlerUtils.MSG_PAGE, mCurrentPosition, 0));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                mHandlerUtils.sendEmptyMessage(ViewPagerHandlerUtils.MSG_KEEP);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                mHandlerUtils.sendEmptyMessageDelayed(ViewPagerHandlerUtils.MSG_UPDATE,
                        ViewPagerHandlerUtils.MSG_DELAY);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                Tools.launchCamera(getActivity(),Camera.CameraInfo.CAMERA_FACING_FRONT);
            } else {
                // Permission Denied
                ToastUtils.showToast("请在应用管理中打开“相机”访问权限！");
            }
        }
    }

}
