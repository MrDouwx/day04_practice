package com.example.douwenxuan.presenter.category;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.category.CategoryContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.category.BrandBean;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {
    /**
     * p层业务逻辑处理
     *
     */
    @Override
    public void category() {
        addSubscribe(HttpManager.getMyApi().getData()
        .compose(RxUtils.<BrandBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandBean>(mView) {
            @Override
            public void onNext(BrandBean brandBean) {
                if (mView!=null) {
                    mView.categoryDataReturn(brandBean.getData().getCategoryList());
                }
            }

        }));
    }

}
