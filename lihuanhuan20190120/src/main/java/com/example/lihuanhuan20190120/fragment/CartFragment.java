package com.example.lihuanhuan20190120.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lihuanhuan20190120.R;
import com.example.lihuanhuan20190120.adapter.CartAdapter;
import com.example.lihuanhuan20190120.contract.Contract;
import com.example.lihuanhuan20190120.entity.CartBean;
import com.example.lihuanhuan20190120.persenter.CartPersenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

public class CartFragment extends Fragment implements Contract.ICartView {

    private CartPersenter cartPersenter;
    private XRecyclerView rc;
    private CheckBox checkbox;
    private List<CartBean.DataBean> cart;
    private CartAdapter cartAdapter;
    private TextView he;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cartfragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        rc = view.findViewById(R.id.rc);
        checkbox = view.findViewById(R.id.checkbox);
        he = view.findViewById(R.id.he);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (CartBean.DataBean dataBean : cart) {
                        dataBean.isChecked = true;
                        for (CartBean.DataBean.Product product : dataBean.list) {
                            product.isProductChecked = true;
                        }
                    }
                }else {
                    for (CartBean.DataBean dataBean : cart) {
                        dataBean.isChecked = false;
                        for (CartBean.DataBean.Product product : dataBean.list) {
                            product.isProductChecked = false;
                        }
                    }
                }
                cartAdapter.notifyDataSetChanged();
                getTatalprice();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cart = new ArrayList<>();
        initData();
    }

    private void initData() {

        cartPersenter = new CartPersenter(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","71");
        cartPersenter.getList(map);
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onSuccess(List<CartBean.DataBean> list) {
        cart = list;
        rc.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartAdapter = new CartAdapter(getActivity(),list);
        rc.setAdapter(cartAdapter);
    }
    public void getTatalprice(){
        double tatalprice = 0;
        for (CartBean.DataBean dataBean : cart) {
            for (CartBean.DataBean.Product product : dataBean.list) {
                if (product.isProductChecked){
                    tatalprice += product.price*product.num;
                }
            }
        }
        he.setText("合计：￥"+tatalprice);
    }

}
