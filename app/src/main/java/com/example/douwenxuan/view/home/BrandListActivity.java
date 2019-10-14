package com.example.douwenxuan.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.douwenxuan.adapter.HomeBrandListAdapter;
import com.example.douwenxuan.base.BaseActivity;
import com.example.douwenxuan.day04_practice.ItemSpace;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.bean.home.BrandList;
import com.example.douwenxuan.presenter.home.BrandPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandListActivity extends BaseActivity implements HomeContract.BrandView,
        OnRefreshLoadMoreListener, HomeBrandListAdapter.ItemClickListener {

    @BindView(R.id.rv_brand_list)
    RecyclerView rvBrandList;
    @BindView(R.id.srl_brand)
    SmartRefreshLayout srlBrand;
    private List<BrandList.DataBeanX.DataBean> list;
    private HomeBrandListAdapter homeBrandListAdapter;
    private int page = 1;
    @Override
    protected int getLayout() {
        return R.layout.activity_brand_list;
    }

    @Override
    protected void initView() {
        rvBrandList.setLayoutManager(new LinearLayoutManager(this));
        rvBrandList.addItemDecoration(new ItemSpace(5));
        list = new ArrayList<>();
        homeBrandListAdapter = new HomeBrandListAdapter(list);
        rvBrandList.setAdapter(homeBrandListAdapter);
        srlBrand.setOnRefreshLoadMoreListener(this);
        homeBrandListAdapter.setClickListener(this);

    }

    @Override
    protected void initData() {
        if (page==1) {
            ((BrandPresenter) presenter).brandList();
        }
        ((BrandPresenter) presenter).brandList(page);

    }

    @Override
    protected IPresenter initPresenter() {
        return new BrandPresenter();
    }

    @Override
    public void getBrandListReturn(BrandList data) {
        BrandList.DataBeanX beanX = data.getData();
        if (page==1) {
            list.clear();
        }
        list.addAll(beanX.getData());
        homeBrandListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        initData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=1;
        initData();
    }

    @Override
    public void onClick(int position, BrandList.DataBeanX.DataBean bean) {
        Intent intent = new Intent(this, BrandDetailActivity.class);
        intent.putExtra("id",bean.getId()+"");
        startActivity(intent);
    }
}
