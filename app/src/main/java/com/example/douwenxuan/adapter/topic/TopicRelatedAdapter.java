package com.example.douwenxuan.adapter.topic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.model.bean.topic.RelatedBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicRelatedAdapter extends RecyclerView.Adapter<TopicRelatedAdapter.ItemHolder> {

    private List<RelatedBean.DataBean> list = new ArrayList<>();

    public TopicRelatedAdapter(List<RelatedBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_topic_item_relate, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int i) {

        Glide.with(holder.itemView.getContext())
                .load(list.get(i).getScene_pic_url())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivNewPic);

        holder.tvName.setText(list.get(i).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick(i, list.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_new_pic)
        ImageView ivNewPic;
        @BindView(R.id.tv_name)
        TextView tvName;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ItemClickListener {
        void onClick(int position, RelatedBean.DataBean data);
    }
}
