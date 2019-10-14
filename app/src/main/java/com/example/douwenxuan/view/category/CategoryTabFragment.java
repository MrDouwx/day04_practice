package com.example.douwenxuan.view.category;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.base.BaseFragment;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.category.CategoryTabContract;
import com.example.douwenxuan.model.bean.category.SortBean;
import com.example.douwenxuan.presenter.category.CategoryTabPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryTabFragment extends BaseFragment implements CategoryTabContract.View, SortAdapter.ItemClickListener {

    @BindView(R.id.iv_sort_banner)
    ImageView ivSortBanner;
    @BindView(R.id.tv_sort_banner_desc)
    TextView tvSortBannerDesc;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.rv_sort)
    RecyclerView rvSort;
    Unbinder unbinder;
    private String name;
    private String id;

    @SuppressLint("ValidFragment")
    public CategoryTabFragment(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public CategoryTabFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_category_tab;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        ((CategoryTabPresenter) presenter).categoryTab(id);
    }

    @Override
    protected IPresenter createPresenter() {
        return new CategoryTabPresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void tabDataReturn(SortBean msg) {
        final SortBean.DataBean.CurrentCategoryBean category = msg.getData()
                .getCurrentCategory();
        Glide.with(getActivity()).load(category.getBanner_url()).into(ivSortBanner);
        tvSortBannerDesc.setText(category.getFront_desc());
        tvSort.setText("—— " + category.getName() + "分类 ——");
        rvSort.setLayoutManager(new GridLayoutManager(getActivity(),3));
        SortAdapter sortAdapter = new SortAdapter(category.getSubCategoryList());
        rvSort.setAdapter(sortAdapter);
        sortAdapter.setClickListener(this);
        sortAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position, SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean data) {
        Intent intent = new Intent(getActivity(), CategoryBListActivity.class);
        intent.putExtra("id",data.getId());
        startActivity(intent);
    }
}
