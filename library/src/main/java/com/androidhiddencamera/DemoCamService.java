/*
 * Copyright 2016 Keval Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidhiddencamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.androidhiddencamera.config.CameraFacing;
import com.androidhiddencamera.config.CameraFocus;
import com.androidhiddencamera.config.CameraImageFormat;
import com.androidhiddencamera.config.CameraResolution;
import com.androidhiddencamera.util.DateUtils;

import java.io.File;

/**
 * Created by Keval on 11-Nov-16.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class DemoCamService extends HiddenCameraService {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //模块名称
        String name=intent.getStringExtra("name");
        //连拍张数
        int number=intent.getIntExtra("number",0);
        //存储路径
        String path=intent.getStringExtra("path");



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            if (HiddenCameraUtils.canOverDrawOtherApps(this)) {
                File file=new File(path);
                if(!file.exists())
                {
                    file.mkdirs();
                }
                File[] filearray=null;
                if(number!=0)
                {
                    filearray=new File[number];
                    for (int i=0;i<number;i++)
                    {
                        filearray[i]=new File(file.getPath()+"/"+(name+DateUtils.longTimeToDate(System.currentTimeMillis(),"yyyyMMddHHmmss"))+i+".jpeg");
                    }
                }
                else{
                    filearray=new File[1];
                    filearray[0]=new File(file.getPath()+"/"+(name+DateUtils.longTimeToDate(System.currentTimeMillis(),"yyyyMMddHHmmss"))+".jpeg");
                }



                final CameraConfig cameraConfig = new CameraConfig()
                        .getBuilder(this)
                        .setCameraFacing(CameraFacing.FRONT_FACING_CAMERA)
                        .setCameraResolution(CameraResolution.MEDIUM_RESOLUTION)
                        .setImageFormat(CameraImageFormat.FORMAT_JPEG)
                        .setCameraFocus(CameraFocus.AUTO)
                        //.setImageFile(new File(file.getPath()+"/"+(name+DateUtils.longTimeToDate(System.currentTimeMillis(),"yyyyMMdd"))+".jpeg"))
                        //.setImageFile(new File(Environment.getExternalStorageDirectory(),"a.jpeg"))
                        .setImageFiles(filearray)
                        .build();

                startCamera(cameraConfig);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        takePicture(cameraConfig);
                    }
                }, 2000L);
            } else {

                //Open settings to grant permission for "Draw other apps".
                HiddenCameraUtils.openDrawOverPermissionSetting(this);
            }
        } else {

            //TODO Ask your parent activity for providing runtime permission
           // Toast.makeText(this, "Camera permission not available", Toast.LENGTH_SHORT).show();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onImageCapture(@NonNull File imageFile) {
//        Toast.makeText(this,
//                "Captured image size is : " + imageFile.length(),
//                Toast.LENGTH_SHORT)
//                .show();
        stopSelf();
    }

    @Override
    public void onCameraError(@CameraError.CameraErrorCodes int errorCode) {
        switch (errorCode) {
            case CameraError.ERROR_CAMERA_OPEN_FAILED:
                //Camera open failed. Probably because another application
                //is using the camera
                //Toast.makeText(this, R.string.error_cannot_open, Toast.LENGTH_LONG).show();
                Log.e("TAG","无法连接相机");
                break;
            case CameraError.ERROR_IMAGE_WRITE_FAILED:
                //Image write failed. Please check if you have provided WRITE_EXTERNAL_STORAGE permission
                //Toast.makeText(this, R.string.error_cannot_write, Toast.LENGTH_LONG).show();
                Log.e("TAG","ERROR_IMAGE_WRITE_FAILED");
                break;
            case CameraError.ERROR_CAMERA_PERMISSION_NOT_AVAILABLE:
                //camera permission is not available
                //Ask for the camera permission before initializing it.
                //Toast.makeText(this, R.string.error_cannot_get_permission, Toast.LENGTH_LONG).show();
                Log.e("TAG","ERROR_CAMERA_PERMISSION_NOT_AVAILABLE");
                break;
            case CameraError.ERROR_DOES_NOT_HAVE_OVERDRAW_PERMISSION:
                //Display information dialog to the user with steps to grant "Draw over other app"
                //permission for the app.
                Log.e("TAG","ERROR_DOES_NOT_HAVE_OVERDRAW_PERMISSION");
                HiddenCameraUtils.openDrawOverPermissionSetting(this);
                break;
            case CameraError.ERROR_DOES_NOT_HAVE_FRONT_CAMERA:
                //Toast.makeText(this, R.string.error_not_having_camera, Toast.LENGTH_LONG).show();
                Log.e("TAG","ERROR_DOES_NOT_HAVE_FRONT_CAMERA");
                break;
        }

        stopSelf();
    }
}
