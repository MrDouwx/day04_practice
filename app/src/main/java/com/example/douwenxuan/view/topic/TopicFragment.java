package com.example.douwenxuan.view.topic;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.example.douwenxuan.apps.MyApp;
import com.example.douwenxuan.base.BaseFragment;
import com.example.douwenxuan.day04_practice.ItemSpace;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.topic.TopicContract;
import com.example.douwenxuan.model.bean.topic.TopicBean;
import com.example.douwenxuan.presenter.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends BaseFragment implements TopicContract.View {

    @BindView(R.id.rv_topic_topic)
    RecyclerView rvTopicTopic;
    @BindView(R.id.btn_previous)
    TextView btnPrevious;
    @BindView(R.id.btn_next)
    TextView btnNext;
    Unbinder unbinder;
    private List<TopicBean.DataBeanX.DataBean> list;
    private TopicAdapter topicAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(View view) {

        //TextView设置字体颜色选择器用select
        btnPrevious.setSelected(true);
        btnNext.setSelected(false);

        list = new ArrayList<>();
        rvTopicTopic.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTopicTopic.addItemDecoration(new ItemSpace(20));
        topicAdapter = new TopicAdapter(getActivity(),list);
        rvTopicTopic.setAdapter(topicAdapter);

        topicAdapter.setClickListener(new TopicAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, TopicBean.DataBeanX.DataBean data) {
                Intent intent = new Intent(getActivity(), TopicInfoActivity.class);
                intent.putExtra("id",data.getId() + "");
                startActivity(intent);
            }
        });

    }

    private int page = 2;
    @Override
    protected void initData() {
        ((TopicPresenter) presenter).topic();

    }

    @Override
    protected IPresenter createPresenter() {
        return new TopicPresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void topicDataReturn(TopicBean msg) {
        list.clear();
        list.addAll(msg.getData().getData());
        topicAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.btn_previous, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_previous:

                ((TopicPresenter) presenter).topic();
                btnPrevious.setSelected(true);
                btnNext.setSelected(false);

                break;
            case R.id.btn_next:

                ((TopicPresenter) presenter).topic(page);
                btnPrevious.setSelected(false);
                btnNext.setSelected(true);

                break;
        }
    }
}
