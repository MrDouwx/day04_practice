package com.example.douwenxuan.view.topic;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.douwenxuan.adapter.topic.TopicDetailAdapter;
import com.example.douwenxuan.adapter.topic.TopicRelatedAdapter;
import com.example.douwenxuan.base.BaseActivity;
import com.example.douwenxuan.day04_practice.ItemSpace;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.topic.TopicContract;
import com.example.douwenxuan.model.bean.topic.DetailBean;
import com.example.douwenxuan.model.bean.topic.RelatedBean;
import com.example.douwenxuan.presenter.topic.TopicDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TopicInfoActivity extends BaseActivity implements TopicContract.InfoView {
    @BindView(R.id.rv_topic_detail)
    RecyclerView rvTopicInfo;
    /*@BindView(R.id.rv_leave_words)
    RecyclerView rvLeaveWords;*/
    @BindView(R.id.rv_topic_relate)
    RecyclerView rvTopicRelate;

    private String id;

    private ArrayList<String> list;
    private TopicDetailAdapter topicDetailAdapter;
    private List<RelatedBean.DataBean> data;
    private TopicRelatedAdapter topicRelatedAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_topic_info;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        getTopicDetail();
        getTopicRelated();
    }

    private void getTopicDetail() {
        list = new ArrayList<>();
        rvTopicInfo.setLayoutManager(new LinearLayoutManager(this));
        //rvTopicInfo.addItemDecoration(new ItemSpace(5));
        topicDetailAdapter = new TopicDetailAdapter(list);
        rvTopicInfo.setAdapter(topicDetailAdapter);
    }

    private void getTopicRelated() {
        data = new ArrayList<>();
        rvTopicRelate.setLayoutManager(new LinearLayoutManager(this));
        topicRelatedAdapter = new TopicRelatedAdapter(data);
        rvTopicRelate.setAdapter(topicRelatedAdapter);

        topicRelatedAdapter.setClickListener(new TopicRelatedAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, RelatedBean.DataBean data) {
                Intent intent = new Intent(TopicInfoActivity.this, TopicInfoActivity.class);
                intent.putExtra("id",data.getId() + "");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        ((TopicDetailPresenter) presenter).topicDetail(id);
        ((TopicDetailPresenter) presenter).topicRelated(id);
    }

    @Override
    protected IPresenter initPresenter() {
        return new TopicDetailPresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void topicDetailReturn(DetailBean bean) {
        String content = bean.getData().getContent();
        String[] split = content.split("\">");
        for (int i = 0; i < split.length; i++) {
            String substring = split[i].substring(split[i].length() - 60, split[i].length());
            list.add(substring);
        }
        topicDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void topicRelatedReturn(RelatedBean bean) {
        data.addAll(bean.getData());
        topicRelatedAdapter.notifyDataSetChanged();
    }

}
