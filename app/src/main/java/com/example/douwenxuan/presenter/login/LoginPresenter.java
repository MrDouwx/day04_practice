package com.example.douwenxuan.presenter.login;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.login.LoginContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.LoginInfo;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{
    /**
     * p层业务逻辑处理
     * 登录
     * @param username
     * @param pw
     */
    @Override
    public void login(String username, String pw) {
        addSubscribe(HttpManager.getTestApi().login(username,pw)
        .compose(RxUtils.<LoginInfo>rxScheduler())
        .subscribeWith(new CommonSubscriber<LoginInfo>(mView){

            @Override
            public void onNext(LoginInfo loginInfo) {
                mView.loginReturn(loginInfo);
            }

        }));
    }

}
