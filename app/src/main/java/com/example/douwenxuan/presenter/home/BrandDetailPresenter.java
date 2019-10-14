package com.example.douwenxuan.presenter.home;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.home.BrandDetial;
import com.example.douwenxuan.model.bean.home.BrandList;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class BrandDetailPresenter extends BasePresenter<HomeContract.BrandDetailView> implements HomeContract.BDPresenter {
    /**
     * p层业务逻辑处理
     *
     */
    @Override
    public void brandDetail(String id) {
        addSubscribe(HttpManager.getMyApi().getBrandDetail(id)
        .compose(RxUtils.<BrandDetial>rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandDetial>(mView) {
            @Override
            public void onNext(BrandDetial data) {
                if (mView!=null) {
                    mView.getBrandDetailReturn(data);
                }
            }

        }));
    }

}
