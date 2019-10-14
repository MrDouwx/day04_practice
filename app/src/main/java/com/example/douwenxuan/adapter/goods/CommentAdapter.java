package com.example.douwenxuan.adapter.goods;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.base.adapter.BaseAdapter;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.model.bean.goods.IssueBean;

import java.util.List;

public class CommentAdapter extends BaseAdapter {

    public CommentAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_comment_list;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        IssueBean issue = (IssueBean) mDatas.get(position);
        ImageView header = (ImageView) holder.getView(R.id.iv_header_comment);
        TextView name = (TextView) holder.getView(R.id.tv_name_comment);
        TextView time = (TextView) holder.getView(R.id.tv_time_comment);
        TextView content = (TextView) holder.getView(R.id.tv_content_comment);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }
}
