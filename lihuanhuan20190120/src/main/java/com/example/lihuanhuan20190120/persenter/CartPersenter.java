package com.example.lihuanhuan20190120.persenter;

import com.example.lihuanhuan20190120.contract.Contract;
import com.example.lihuanhuan20190120.entity.CartBean;
import com.example.lihuanhuan20190120.model.CartModel;
import com.example.lihuanhuan20190120.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class CartPersenter extends Contract.ICartPersenter {
    private CartModel cartModel;
    private Contract.ICartView iCartView;

    public CartPersenter(Contract.ICartView iCartView) {
        this.cartModel = new CartModel();
        this.iCartView = iCartView;
    }

    @Override
    public void getList(HashMap<String, String> map) {
        if (cartModel!=null){
            cartModel.getList(map, new RequestCallback() {
                @Override
                public void onFailreq(String msg) {
                    if (iCartView!=null){
                        iCartView.onFail(msg);
                    }
                }

                @Override
                public void onSuccessreq(String result) {
                    CartBean cartBean = new Gson().fromJson(result, CartBean.class);
                    List<CartBean.DataBean> data = cartBean.data;
                    if (iCartView!=null){
                        iCartView.onSuccess(data);
                    }
                }
            });
        }
    }
}
