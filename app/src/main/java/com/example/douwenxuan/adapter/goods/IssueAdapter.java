package com.example.douwenxuan.adapter.goods;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.douwenxuan.base.adapter.BaseAdapter;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.model.bean.goods.IssueBean;

import java.util.List;

public class IssueAdapter extends BaseAdapter {

    public IssueAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_issue_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        IssueBean bean = (IssueBean) mDatas.get(position);
        TextView question = (TextView) holder.getView(R.id.tv_question_issue);
        TextView answer = (TextView) holder.getView(R.id.tv_answer_issue);
        question.setText(bean.getQuestion());
        answer.setText(bean.getAnswer());
    }

}
