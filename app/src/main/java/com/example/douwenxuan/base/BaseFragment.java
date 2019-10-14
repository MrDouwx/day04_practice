package com.example.douwenxuan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.douwenxuan.interfaces.IBaseView;
import com.example.douwenxuan.interfaces.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends IBaseView,P extends IPresenter> extends Fragment implements IBaseView {

    protected Context context;
    protected P presenter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),null);
        context = this.getContext();
        unbinder = ButterKnife.bind(this,view);
        initView(view);
        presenter = createPresenter();
        presenter.attchView(this);
        initData();
        return view;
    }

    //获取布局
    protected abstract int getLayout();

    //初始化布局
    protected abstract void initView(View view);

    //初始化数据
    protected abstract void initData();

    //创建P
    protected abstract P createPresenter();

    @Override
    public void onResume() {
        super.onResume();
        if(presenter != null){
            presenter.attchView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.detachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
