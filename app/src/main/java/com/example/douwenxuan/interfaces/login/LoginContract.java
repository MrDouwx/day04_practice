package com.example.douwenxuan.interfaces.login;


import com.example.douwenxuan.interfaces.IBaseView;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.model.bean.LoginInfo;

public interface LoginContract {

    //登录返回的接口定义
    interface View extends IBaseView {
        void loginReturn(LoginInfo msg);
    }

    //登录的p层接口
    interface Presenter extends IPresenter<View> {
        void login(String username, String pw);
    }


}
