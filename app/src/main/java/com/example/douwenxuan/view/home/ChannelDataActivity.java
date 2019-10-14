package com.example.douwenxuan.view.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.douwenxuan.base.BaseActivity;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.category.CategoryContract;

import com.example.douwenxuan.model.bean.category.CategoryListBean;
import com.example.douwenxuan.presenter.category.CategoryPresenter;
import com.example.douwenxuan.view.category.FragTabAdapter;
import com.example.douwenxuan.view.home.channel.ChannelListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelDataActivity extends BaseActivity implements CategoryContract.View{

    @BindView(R.id.tab_channel_data_show)
    TabLayout tabChannelDataShow;
    @BindView(R.id.vp_channel)
    ViewPager vpChannel;
    private int id;
    private ArrayList<String> tabName;
    private ArrayList<Fragment> fragments;


    @Override
    protected int getLayout() {
        return R.layout.activity_channel_data;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        fragments = new ArrayList<>();
        tabName = new ArrayList<>();
    }

    @Override
    protected void initData() {
        ((CategoryPresenter)presenter).category();
    }

    @Override
    protected IPresenter initPresenter() {
        return new CategoryPresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void categoryDataReturn(List<CategoryListBean> data) {
        tabChannelDataShow.setupWithViewPager(vpChannel);

        for (int i = 0; i < data.size(); i++) {
            CategoryListBean bean = data.get(i);
            fragments.add(ChannelListFragment.newInstance(bean.getId()+"",bean.getName(),bean.getFront_desc()));
            tabName.add(bean.getName());
        }
        FragTabAdapter fragTabAdapter = new FragTabAdapter(getSupportFragmentManager(), fragments, tabName);
        vpChannel.setAdapter(fragTabAdapter);

        for (int i = 0; i < data.size(); i++) {
            CategoryListBean bean = data.get(i);
            if (id==bean.getId()) {
                //tab设置默认选中某个键
                tabChannelDataShow.getTabAt(i).select();
            }
        }
    }

}
