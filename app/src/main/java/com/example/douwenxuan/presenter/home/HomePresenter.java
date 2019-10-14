package com.example.douwenxuan.presenter.home;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.home.HomeBean;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    /**
     * p层业务逻辑处理
     *
     */
    @Override
    public void home() {
        addSubscribe(HttpManager.getMyApi().getIndexData()
        .compose(RxUtils.<HomeBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<HomeBean>(mView) {
            @Override
            public void onNext(HomeBean homeBean) {
                if (mView!=null) {
                    mView.homeDataReturn(homeBean);
                }
            }

        }));
    }

}
