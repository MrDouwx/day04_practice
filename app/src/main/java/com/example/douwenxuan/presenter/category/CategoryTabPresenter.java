package com.example.douwenxuan.presenter.category;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.category.CategoryTabContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.category.SortBean;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class CategoryTabPresenter extends BasePresenter<CategoryTabContract.View> implements CategoryTabContract.Presenter {
    /**
     * p层业务逻辑处理
     *
     */
    @Override
    public void categoryTab(String id) {
        addSubscribe(HttpManager.getMyApi().getTabData(id)
        .compose(RxUtils.<SortBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<SortBean>(mView) {
            @Override
            public void onNext(SortBean sortBean) {
                if (mView!=null) {
                    mView.tabDataReturn(sortBean);
                }
            }

        }));
    }

}
