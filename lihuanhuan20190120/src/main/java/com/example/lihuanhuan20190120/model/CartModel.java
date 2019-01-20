package com.example.lihuanhuan20190120.model;

import android.os.Handler;

import com.example.lihuanhuan20190120.api.CartApi;
import com.example.lihuanhuan20190120.contract.Contract;
import com.example.lihuanhuan20190120.net.OkhttpCallback;
import com.example.lihuanhuan20190120.net.RequestCallback;
import com.example.lihuanhuan20190120.util.OkhttpUtil;

import java.util.HashMap;

public class CartModel implements Contract.ICartModel {
    Handler handler = new Handler();
    @Override
    public void getList(HashMap<String, String> map, final RequestCallback requestCallback) {
        OkhttpUtil.getInstance().Post(CartApi.CART_URL, map, new OkhttpCallback() {
            @Override
            public void onFailok(final String msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestCallback.onFailreq(msg);
                    }
                });
            }

            @Override
            public void onSuccessok(final String result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestCallback.onSuccessreq(result);
                    }
                });
            }
        });
    }
}
