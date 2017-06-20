package com.mazter707.trends;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private List<Album> albumList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView money;
        public ImageView product;

        public ViewHolder(View view) {
            super(view);
            money = (TextView) view.findViewById(R.id.txt_money);
            product = (ImageView) view.findViewById(R.id.image_product);
        }
    }

    public ProductAdapter(Context mContext, List<Album> albumList){
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Album album = albumList.get(position);
        holder.money.setText(album.getMoney());

        Glide.with(mContext).load(album.getProduct()).into(holder.product);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

