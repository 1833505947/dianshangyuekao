package com.example.lihuanhuan20190120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.lihuanhuan20190120.R;
import com.example.lihuanhuan20190120.entity.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<CartBean.DataBean> list;

    public CartAdapter(Context context, List<CartBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cart_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CartBean.DataBean dataBean = list.get(i);
        viewHolder.checkbox.setChecked(dataBean.isChecked);
        viewHolder.checkbox.setText(dataBean.sellerName);
        viewHolder.rc.setLayoutManager(new LinearLayoutManager(context));
        CartItemAdapter cartItemAdapter = new CartItemAdapter(context,dataBean.list);
        viewHolder.rc.setAdapter(cartItemAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkbox;
        RecyclerView rc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            rc = itemView.findViewById(R.id.rc);
        }
    }

}
