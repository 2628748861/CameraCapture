package com.wangxiaobao.cameracapture;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.androidhiddencamera.DemoCamService;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view)
    {
        Intent service=new Intent(MainActivity.this, DemoCamService.class);
        service.putExtra("name","楼盘信息扫码");
        service.putExtra("number",4);
        service.putExtra("path",Environment.getExternalStorageDirectory().getPath()+"/MT2/pics");
        startService(service);
    }
}
