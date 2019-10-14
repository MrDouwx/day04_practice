package com.example.douwenxuan.view.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.model.bean.category.SortBean;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ItemHolder> {


    private List<SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list;

    public SortAdapter(List<SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goods_sort_item,
                viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {

        Glide.with(holder.itemView.getContext()).load(list.get(position).getWap_banner_url())
                .into(holder.iv);
        holder.name.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener!=null) {
                    clickListener.onClick(position,list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView name;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_goodsPic);
            name = itemView.findViewById(R.id.tv_goodsName);

        }
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ItemClickListener{
        void onClick(int position,SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean data);
    }
}
