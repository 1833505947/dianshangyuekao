package com.example.lihuanhuan20190120.entity;

import java.util.List;

public class CartBean {



    public String msg;
    public String code;
    public List<DataBean> data;


    public static class DataBean {
        public boolean isChecked;
        public String sellerName;
        public String sellerid;
        public List<Product> list;
        public static class Product{
            public boolean isProductChecked;
            public String images;
            public int num;
            public int pid;
            public double price;
            public String  title;
            public int pos;
        }

    }
}
