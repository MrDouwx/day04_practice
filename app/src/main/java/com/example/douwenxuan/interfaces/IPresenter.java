package com.example.douwenxuan.interfaces;

public interface IPresenter<V extends IBaseView> {

    //p层关联V层
    void attchView(V view);

    void detachView();

}
