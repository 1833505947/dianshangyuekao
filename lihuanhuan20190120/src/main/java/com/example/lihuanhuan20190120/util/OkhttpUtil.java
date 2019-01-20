package com.example.lihuanhuan20190120.util;

import android.os.Handler;

import com.example.lihuanhuan20190120.net.OkhttpCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtil {
    private static OkhttpUtil instance;
    private final OkHttpClient okHttpClient;
    Handler handler = new Handler();
    private OkhttpUtil() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public static OkhttpUtil getInstance(){
        if (instance==null){
            synchronized (OkhttpUtil.class){
                if (instance==null){
                    instance = new OkhttpUtil();
                }
            }
        }
        return instance;
    }
    public void Get(String url, HashMap<String,String> map, final OkhttpCallback okhttpCallback){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okhttpCallback.onFailok("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okhttpCallback.onSuccessok(string);
                    }
                });
            }
        });
    }
    public void Post(String url, HashMap<String,String> map, final OkhttpCallback okhttpCallback){
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> m : map.entrySet()) {
            formBody.add(m.getKey(),m.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okhttpCallback.onFailok("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okhttpCallback.onSuccessok(string);
                    }
                });
            }
        });
    }
    public void cancel(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
