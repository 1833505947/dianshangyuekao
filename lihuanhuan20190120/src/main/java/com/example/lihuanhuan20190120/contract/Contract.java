package com.example.lihuanhuan20190120.contract;

import android.view.View;

import com.example.lihuanhuan20190120.entity.CartBean;
import com.example.lihuanhuan20190120.net.RequestCallback;

import java.util.HashMap;
import java.util.List;

public interface Contract {
    public abstract class ICartPersenter{
        public abstract void getList(HashMap<String,String> map);
    }
    interface ICartModel{
        void getList(HashMap<String,String> map, RequestCallback requestCallback);
    }
    interface ICartView{
        void onFail(String msg);
        void onSuccess(List<CartBean.DataBean> list);
    }
}
