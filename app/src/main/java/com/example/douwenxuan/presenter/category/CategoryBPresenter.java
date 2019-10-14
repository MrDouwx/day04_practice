package com.example.douwenxuan.presenter.category;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.category.CategoryContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.category.BrandBean;
import com.example.douwenxuan.model.bean.category.GoodsSort;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class CategoryBPresenter extends BasePresenter<CategoryContract.GoodsView> implements CategoryContract.GPresenter {
    /**
     * p层业务逻辑处理
     *
     */

    @Override
    public void categoryBList(String id) {
        addSubscribe(HttpManager.getMyApi().getGoodsSort(id)
                .compose(RxUtils.<GoodsSort>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodsSort>(mView) {
                    @Override
                    public void onNext(GoodsSort bean) {
                        if (mView!=null) {
                            mView.categoryBList(bean);
                        }
                    }

                }));
    }
}
