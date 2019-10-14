package com.example.douwenxuan.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.base.BaseActivity;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.bean.home.BrandDetial;
import com.example.douwenxuan.presenter.home.BrandDetailPresenter;

import butterknife.BindView;

public class BrandDetailActivity extends BaseActivity implements HomeContract.BrandDetailView {

    @BindView(R.id.iv_brand_detail)
    ImageView ivBrandDetail;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.tv_desc_brand)
    TextView tvDescBrand;
    @BindView(R.id.rv_brand_detail)
    RecyclerView rvBrandDetail;
    private String id;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected void initData() {
        ((BrandDetailPresenter)presenter).brandDetail(id);
    }

    @Override
    protected IPresenter initPresenter() {
        return new BrandDetailPresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getBrandDetailReturn(BrandDetial data) {
        BrandDetial.DataBean.BrandBean brand = data.getData().getBrand();
        Glide.with(this).load(brand.getList_pic_url()).into(ivBrandDetail);
        tvBrandName.setText(brand.getName());
        tvDescBrand.setText(brand.getSimple_desc());
    }
}
