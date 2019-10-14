package com.example.douwenxuan.adapter.goods;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.model.bean.goods.GoodsListBean;
import com.example.douwenxuan.model.bean.home.NewGoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsRelateAdapter extends RecyclerView.Adapter<GoodsRelateAdapter.ItemHolder> {
    private List<GoodsListBean> list=new ArrayList<>();

    public GoodsRelateAdapter(List<GoodsListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_goods_relate_item, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int i) {
        Glide.with(holder.itemView.getContext()).load(list.get(i).getList_pic_url()).into(holder.iv);
        holder.name.setText(list.get(i).getName());
        holder.price.setText("￥"+list.get(i).getRetail_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener!=null) {
                    clickListener.onClick(i,list.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView name;
        TextView price;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);

                iv = itemView.findViewById(R.id.iv_new_pic);
                name = itemView.findViewById(R.id.tv_name);
                price = itemView.findViewById(R.id.tv_price);

        }
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ItemClickListener{
        void onClick(int position, GoodsListBean data);
    }
}
