package com.exper.nova.fragment;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class EmuiFirstItemFragment extends EmuiItemFragment {

    public EmuiFirstItemFragment(int drawableId,int videoId){
        super(drawableId,videoId);
    }

    @Override
    protected void updateViews() {
        prepare(true);
    }
}
