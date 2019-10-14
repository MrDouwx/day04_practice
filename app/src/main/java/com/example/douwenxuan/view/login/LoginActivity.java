package com.example.douwenxuan.view.login;

import android.widget.TextView;

import com.example.douwenxuan.base.BaseActivity;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.login.LoginContract;
import com.example.douwenxuan.model.bean.LoginInfo;
import com.example.douwenxuan.presenter.login.LoginPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.txt_username)
    TextView txtUsername;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        ((LoginPresenter)presenter).login("zq1","123456");
    }

    @Override
    protected IPresenter initPresenter() {
        return new LoginPresenter();
    }

    /**
     * 登录成功返回
     * @param loginInfo
     */
    @Override
    public void loginReturn(LoginInfo loginInfo) {

    }

    /**
     * 登录失败返回
     * @param err
     */
    @Override
    public void showErrMsg(String err) {

    }
}
