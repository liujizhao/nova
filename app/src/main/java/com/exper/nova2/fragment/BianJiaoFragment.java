package com.exper.nova2.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.exper.nova2.MainActivity;
import com.exper.nova2.base.BaseFragment;
import com.exper.nova2.util.ToastUtils;
import com.exper.nova2.util.Tools;
import com.huawei.experience.nova2.R;

import butterknife.OnClick;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class BianJiaoFragment extends BaseFragment {

    @Override
    protected int attachLayoutRes() {
        return R.layout.double_first_fragment;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {

    }

    public BianJiaoFragment(){

    }

    @OnClick({R.id.to_camera,R.id.to_see,R.id.btn_back,R.id.btn_menu})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.to_camera:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                            1);
                }else{
                    Tools.launchCamera(getActivity(), Camera.CameraInfo.CAMERA_FACING_BACK);
                }
                break;
            case R.id.to_see:
                ((MainActivity)getActivity()).replaceFragment(R.id.content_frame,new BianJiaoSeeFragment(),"see");
                break;
            case R.id.btn_back:
                ((MainActivity)getActivity()).clearAllFragment();
                break;
            case R.id.btn_menu:
                ((MainActivity)getActivity()).openDrawer();
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
                Tools.launchCamera(getActivity(),Camera.CameraInfo.CAMERA_FACING_BACK);
            } else {
                // Permission Denied
                ToastUtils.showToast("请在应用管理中打开“相机”访问权限！");
            }
        }
    }
}
