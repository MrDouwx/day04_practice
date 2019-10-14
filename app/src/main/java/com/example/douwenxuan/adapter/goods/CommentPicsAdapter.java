package com.example.douwenxuan.adapter.goods;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.base.adapter.BaseAdapter;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.model.bean.goods.GoodsDetail;
import com.example.douwenxuan.model.bean.goods.IssueBean;

import java.util.List;

public class CommentPicsAdapter extends BaseAdapter {

    public CommentPicsAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_comment_pics_list;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        GoodsDetail.DataBeanX.CommentBean.DataBean.PicListBean pics = (GoodsDetail.DataBeanX.CommentBean.DataBean.PicListBean) mDatas.get(position);
        ImageView header = (ImageView) holder.getView(R.id.iv_pic_comment);
        Glide.with(header.getContext()).load(pics.getPic_url()).into(header);
    }

}
