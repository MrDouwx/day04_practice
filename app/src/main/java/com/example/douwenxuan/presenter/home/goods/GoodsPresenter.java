package com.example.douwenxuan.presenter.home.goods;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.goods.CartBean;
import com.example.douwenxuan.model.bean.goods.GoodsDetail;
import com.example.douwenxuan.model.bean.goods.GoodsRelated;
import com.example.douwenxuan.model.bean.home.BrandList;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class GoodsPresenter extends BasePresenter<HomeContract.GoodsView> implements HomeContract.GoodPresenter {
    /**
     * p层业务逻辑处理
     *
     */

    @Override
    public void getGoodsDetail(String id) {
        addSubscribe(HttpManager.getMyApi().getGoodsDetail(id)
                .compose(RxUtils.<GoodsDetail>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodsDetail>(mView) {
                    @Override
                    public void onNext(GoodsDetail data) {
                        if (mView!=null) {
                            mView.getGoodsDetailReturn(data);
                        }
                    }

                }));
    }

    @Override
    public void getGoodsRelated(String id) {
        addSubscribe(HttpManager.getMyApi().getGoodsRelated(id)
                .compose(RxUtils.<GoodsRelated>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodsRelated>(mView) {
                    @Override
                    public void onNext(GoodsRelated data) {
                        if (mView!=null) {
                            mView.getGoodsRelatedReturn(data);
                        }
                    }

                }));
    }

    @Override
    public void goodsToCart(int uid, int goodsId, int productId, int num) {
        addSubscribe(HttpManager.getMyApi().postGoodsToCart(uid,goodsId,productId,num)
                .compose(RxUtils.<CartBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartBean>(mView) {
                    @Override
                    public void onNext(CartBean data) {
                        if (mView!=null) {
                            mView.postGoodsToCart(data);
                        }
                    }

                }));
    }
}
