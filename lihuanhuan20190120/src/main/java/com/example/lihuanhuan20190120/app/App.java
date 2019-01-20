package com.example.lihuanhuan20190120.app;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //5c44310db465f557ca000201
        UMConfigure.init(this, "5c44310db465f557ca000201", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
