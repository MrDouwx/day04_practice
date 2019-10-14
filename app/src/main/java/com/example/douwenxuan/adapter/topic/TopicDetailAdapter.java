package com.example.douwenxuan.adapter.topic;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.base.adapter.BaseAdapter;
import com.example.douwenxuan.day04_practice.R;

import java.util.List;

public class TopicDetailAdapter extends BaseAdapter {

    public TopicDetailAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_topic_info_pic;
    }

    @Override
    protected void bindData(BaseAdapter.BaseViewHolder holder, int position, Object o) {
        String url = (String) mDatas.get(position);
        ImageView iv = (ImageView) holder.getView(R.id.iv_topic_pic);
        Glide.with(mContext).load("https:"+url).placeholder(R.mipmap.ic_launcher).into(iv);
    }

}
