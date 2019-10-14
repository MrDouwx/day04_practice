package com.example.douwenxuan.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.douwenxuan.day04_practice.ItemSpace;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.model.bean.home.CategoryListBean;
import com.example.douwenxuan.view.home.ShoppingActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemHolder> {

    private ArrayList<CategoryListBean> list;

    public CategoryAdapter(ArrayList<CategoryListBean> categoryList) {
        this.list = categoryList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .layout_home_category_item, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int i) {
        holder.title.setText(list.get(i).getName());
        holder.rv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 2));
        holder.rv.addItemDecoration(new ItemSpace(16));
        HomeCategoryAdapter homeCategoryAdapter = new HomeCategoryAdapter(list.get(i).getGoodsList());
        holder.rv.setAdapter(homeCategoryAdapter);
        homeCategoryAdapter.setClickListener(new HomeCategoryAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, CategoryListBean.GoodsListBean data) {
                Intent intent = new Intent(holder.itemView.getContext(), ShoppingActivity.class);
                intent.putExtra("goodId",data.getId()+"");
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        TextView title;
        RecyclerView rv;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_category);
            rv=itemView.findViewById(R.id.rv_category);
        }
    }
}
