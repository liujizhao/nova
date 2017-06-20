package com.exper.nova.fragment;

import android.net.Uri;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.widget.ImageView;

import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.widget.TextureVideoView;

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

    public EmuiItemFragment(int drawableId,int videoId){
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
        mVideoView.setDataSource(getActivity(),Uri.parse(getVideoPath()));
        mHolderView.setBackgroundResource(mDrawableId);
    }

    public void prepare(){
        mVideoView.setListener(new TextureVideoView.MediaPlayerListener() {
            @Override
            public void onVideoPrepared() {

            }

            @Override
            public void onVideoEnd() {
                mVideoView.play();
            }
        });
    }

    public void startVideo(boolean isVisibleToUser){
        if(mVideoView != null) {
            if(isVisibleToUser){
//                prepare();
                mVideoView.play();
            }else{
//                mVideoView.setState(TextureVideoView.State.PLAY);
                mVideoView.stop();
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
        if(isVisible() && getUserVisibleHint()) {
            if (mVideoView != null) {
//                mVideoView.setState(TextureVideoView.State.PLAY);
                mVideoView.stop();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mVideoView != null) {
//            mVideoView.setState(TextureVideoView.State.PLAY);
            mVideoView.stop();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        startVideo(isVisibleToUser);
    }
}
