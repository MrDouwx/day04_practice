package com.example.douwenxuan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.douwenxuan.interfaces.IBaseView;
import com.example.douwenxuan.interfaces.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V extends IBaseView,P extends IPresenter> extends AppCompatActivity implements IBaseView {

    //获取布局文件
    protected abstract int getLayout();
    //初始化view
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();
    //初始化p层
    protected abstract P initPresenter();

    protected P presenter;

    Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        presenter = initPresenter();
        if (presenter!=null) {
            presenter.attchView(this);
        }
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null){
            presenter.attchView(this);
        }
    }

    /**
     * 登录失败返回
     * @param
    @Override
    public void showErrMsg(String err) {

    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
        if(presenter != null){
            presenter.detachView();
        }

    }
}
