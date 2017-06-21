package com.exper.nova2.fragment;

import android.annotation.SuppressLint;

/**
 * Created by liujizhao on 2017/6/18.
 */

@SuppressLint("ValidFragment")
public class EmuiFirstItemFragment extends EmuiItemFragment {

    @SuppressLint("ValidFragment")
    public EmuiFirstItemFragment(int drawableId, int videoId){
        super(drawableId, videoId);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mVideoView != null){
            mVideoView.start();
        }
    }
}
