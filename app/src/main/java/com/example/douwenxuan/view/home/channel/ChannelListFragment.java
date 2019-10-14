package com.example.douwenxuan.view.home.channel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.douwenxuan.adapter.channel.HomeChannelListAdapter;
import com.example.douwenxuan.base.BaseFragment;
import com.example.douwenxuan.day04_practice.ItemSpace;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.bean.home.channel.DataBean;
import com.example.douwenxuan.model.bean.home.channel.GoodsListBean;
import com.example.douwenxuan.presenter.home.channel.ChannelListPresenter;
import com.example.douwenxuan.view.home.ShoppingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelListFragment extends BaseFragment implements HomeContract.ChannelView {

    @BindView(R.id.tv_tab)
    TextView tvTab;
    @BindView(R.id.tv_tab_info)
    TextView tvTabInfo;
    Unbinder unbinder;
    private String id;
    private String name;
    private String desc;
    private int page = 1;
    private int size = 50;
    private List<DataBean> list;
    private HomeChannelListAdapter channelListAdapter;

    public static ChannelListFragment newInstance(String id,String name,String desc) {
        ChannelListFragment fragment = new ChannelListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("categoryId", id);
        bundle.putString("name",name);
        bundle.putString("desc",desc);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.rv_channel_show)
    RecyclerView rvChannelShow;

    @Override
    protected int getLayout() {
        return R.layout.fragment_channel_list;
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = getArguments();
        id = bundle.getString("categoryId");
        name = bundle.getString("name");
        desc = bundle.getString("desc");
        tvTab.setText(name);
        tvTabInfo.setText(desc);
        list = new ArrayList<>();
        rvChannelShow.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvChannelShow.addItemDecoration(new ItemSpace(5));
        channelListAdapter = new HomeChannelListAdapter(list);
        rvChannelShow.setAdapter(channelListAdapter);
        channelListAdapter.setClickListener(new HomeChannelListAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, DataBean data) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                intent.putExtra("goodId",data.getId()+"");
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        ((ChannelListPresenter) presenter).channelList(id, page, size);
    }

    @Override
    protected IPresenter createPresenter() {
        return new ChannelListPresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getChannelReturn(List<DataBean> data) {
        list.addAll(data);
        channelListAdapter.notifyDataSetChanged();
    }

}
