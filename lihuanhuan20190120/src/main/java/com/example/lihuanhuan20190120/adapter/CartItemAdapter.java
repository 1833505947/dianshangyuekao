package com.example.lihuanhuan20190120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lihuanhuan20190120.R;
import com.example.lihuanhuan20190120.entity.CartBean;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private Context context;
    private List<CartBean.DataBean.Product> list;

    public CartItemAdapter(Context context, List<CartBean.DataBean.Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cart_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CartBean.DataBean.Product product = list.get(i);
        viewHolder.checkbox.setChecked(product.isProductChecked);
        viewHolder.tv_name.setText(product.title);
        viewHolder.tv_price.setText(product.price+"");
        String[] split = product.images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.iv_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkbox;
        ImageView iv_img;
        TextView tv_name,tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);


        }
    }
}
