package com.exper.nova.fragment;

import android.view.View;

import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;

import butterknife.OnClick;

/**
 * 参数页面
 * Created by Blank on 2017/6/15.
 */

public class ParamFragment extends BaseFragment {

    @Override
    protected int attachLayoutRes() {
        return R.layout.param_fragment;
    }

    @OnClick(R.id.close_btn)
    public void onClick(View view){
        getActivity().onBackPressed();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {

    }
}
