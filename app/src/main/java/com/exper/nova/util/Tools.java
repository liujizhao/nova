package com.exper.nova.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.provider.MediaStore;
import android.util.DisplayMetrics;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Blank on 2017/6/15.
 */

public class Tools {

    /**
     * 获取屏幕宽度  context
     */
    public static int getWindowWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources()
                .getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度 context
     */
    public static int getWindowHeigth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources()
                .getDisplayMetrics();
        return dm.heightPixels;
    }

    //启动相机
    public static void launchCamera(Context context, int flag)
    {
        try{
            //获取相机包名
            Intent infoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ResolveInfo res = context.getPackageManager().
                    resolveActivity(infoIntent, 0);
            if (res != null)
            {
                String packageName = res.activityInfo.packageName;
                if(packageName.equals("android"))
                {
                    infoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                    res = context.getPackageManager().
                            resolveActivity(infoIntent, 0);
                    if (res != null)
                        packageName = res.activityInfo.packageName;
                }
                //启动相机
                startApplicationByPackageName(context,packageName,flag);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    //通过包名启动应用
    private static void startApplicationByPackageName(Context context,String packName,int flag)
    {
        PackageInfo packageInfo = null;
        try{
            packageInfo = context.getPackageManager().getPackageInfo(packName, 0);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(null == packageInfo){
            return;
        }
        Intent resolveIntent = new Intent();
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageInfo.packageName);
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
        if(null == resolveInfoList){
            return;
        }
        Iterator<ResolveInfo> iterator = resolveInfoList.iterator();
        while(iterator.hasNext()){
            ResolveInfo resolveInfo = iterator.next();
            if(null == resolveInfo){
                return;
            }
            String packageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("android.intent.extras.CAMERA_FACING", flag); // 调用前置/后置摄像头
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }
}