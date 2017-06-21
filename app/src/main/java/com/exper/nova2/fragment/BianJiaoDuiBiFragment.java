package com.exper.nova2.fragment;

import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.exper.nova2.MainActivity;
import com.exper.nova2.base.BaseFragment;
import com.huawei.experience.nova2.R;
import com.sprylab.android.widget.TextureVideoView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class BianJiaoDuiBiFragment extends BaseFragment {

    @BindView(R.id.videoView)
    TextureVideoView mVideoView;

    @BindView(R.id.flag)
    ImageView mFlagView;

    private float x1 = 0;
    private float x2 = 0;
    private int mCurrentPosition;

    @Override
    protected int attachLayoutRes() {
        return R.layout.bianjiao_duibi_fragment;
    }

    @Override
    protected void initViews() {
        mVideoView.setVideoURI(Uri.parse(getVideoPath1()));
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //继承了Activity的onTouchEvent方法，直接监听点击事件
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = motionEvent.getX();
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = motionEvent.getX();
                    if(x2 - x1 > 100 && mCurrentPosition == 1) {
                        mCurrentPosition = 0;
                        mFlagView.setImageResource(R.drawable.flag_bg1);
                        mVideoView.setVideoURI(Uri.parse(getVideoPath1()));
                        mVideoView.start();
                    }else if (x2 - x1 < 100 && mCurrentPosition == 0) {
                        mCurrentPosition = 1;
                        mFlagView.setImageResource(R.drawable.flag_bg2);
                        mVideoView.setVideoURI(Uri.parse(getVideoPath2()));
                        mVideoView.start();
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void updateViews() {
        mVideoView.start();
    }

    @OnClick({R.id.btn_back,R.id.btn_menu,R.id.back_home})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_home:
            case R.id.btn_back:
                ((MainActivity)getActivity()).clearAllFragment();
                break;
            case R.id.btn_menu:
                ((MainActivity)getActivity()).openDrawer();
                break;
        }
    }

    private String getVideoPath1() {
        return "android.resource://" + getActivity().getPackageName() + "/" + R.raw.guangxue1;
    }

    private String getVideoPath2() {
        return "android.resource://" + getActivity().getPackageName() + "/" + R.raw.guangxue2;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mVideoView != null){
            mVideoView.suspend();
        }
    }
}
