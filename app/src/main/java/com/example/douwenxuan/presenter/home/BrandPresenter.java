package com.example.douwenxuan.presenter.home;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.home.BrandList;
import com.example.douwenxuan.model.bean.home.HomeBean;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class BrandPresenter extends BasePresenter<HomeContract.BrandView> implements HomeContract.BPresenter {
    /**
     * p层业务逻辑处理
     *
     */
    @Override
    public void brandList() {
        addSubscribe(HttpManager.getMyApi().getBrandData()
        .compose(RxUtils.<BrandList>rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandList>(mView) {
            @Override
            public void onNext(BrandList data) {
                if (mView!=null) {
                    mView.getBrandListReturn(data);
                }
            }

        }));
    }

    @Override
    public void brandList(int page) {
        addSubscribe(HttpManager.getMyApi().getBrandData(page)
                .compose(RxUtils.<BrandList>rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandList>(mView) {
                    @Override
                    public void onNext(BrandList data) {
                        if (mView!=null) {
                            mView.getBrandListReturn(data);
                        }
                    }

                }));
    }
}
