package com.exper.nova.fragment;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.sprylab.android.widget.TextureVideoView;

import butterknife.BindView;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class EmuiItemFragment extends BaseFragment {

    @BindView(R.id.holderView)
    ImageView mHolderView;

    @BindView(R.id.videoView)
    TextureVideoView mVideoView;

    private int mDrawableId;
    private int mVideoId;

    public EmuiItemFragment(){

    }

    @SuppressLint("ValidFragment")
    public EmuiItemFragment(int drawableId, int videoId){
        super();
        mDrawableId = drawableId;
        mVideoId = videoId;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.emui_fragment_item;
    }

    @Override
    protected void initViews() {
        prepare();
        mHolderView.setBackgroundResource(mDrawableId);
    }

    public void prepare(){
        mVideoView.setVideoURI(Uri.parse(getVideoPath()));
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start();
            }
        });
    }

    public void startVideo(){
        if(mVideoView != null) {
            mVideoView.start();
            mVideoView.seekTo(0);
        }
    }

    public void resetVideo(){
        if(mVideoView != null) {
            if(mVideoView.isPlaying()) {
                mVideoView.pause();
            }
        }
    }

    @Override
    protected void updateViews() {
    }

    private String getVideoPath() {
        return "android.resource://" + getActivity().getPackageName() + "/" + mVideoId;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mVideoView != null){
            mVideoView.suspend();
        }
    }

}
