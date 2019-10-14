package com.example.douwenxuan.view.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.douwenxuan.base.BaseActivity;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;

public class HotShowActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_hot_show;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
