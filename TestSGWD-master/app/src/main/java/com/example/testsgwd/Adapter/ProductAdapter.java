package com.example.testsgwd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testsgwd.R;
import com.example.testsgwd.model.Products;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    String urlGetImage = "http://192.168.1.19:8080/test/Image/";

    private Context context;
    private int layout;
    private List<Products> productsList;

    public ProductAdapter(Context context, int layout, List<Products> productsList) {
        this.context = context;
        this.layout = layout;
        this.productsList = productsList;
    }

    @Override
    public int getCount() {
        return productsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imageViewPro;
        TextView tv_namePro, tv_pricePro;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imageViewPro = (ImageView)view.findViewById(R.id.imageViewPro);
            holder.tv_namePro   = (TextView)view.findViewById(R.id.tv_namePro);
            holder.tv_pricePro  = (TextView)view.findViewById(R.id.tv_pricePro);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Products products = productsList.get(i);

        Glide.with(context).load(urlGetImage+productsList.get(i).getmProImg()).into(holder.imageViewPro);
        holder.tv_namePro.setText(products.getmProName());
        holder.tv_pricePro.setText(products.getmProPrice()+" VNĐ");

        return view;
    }
}
