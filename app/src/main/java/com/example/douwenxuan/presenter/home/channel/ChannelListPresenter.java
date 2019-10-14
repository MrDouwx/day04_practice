package com.example.douwenxuan.presenter.home.channel;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.home.BrandDetial;
import com.example.douwenxuan.model.bean.home.channel.ChannelGoodsList;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class ChannelListPresenter extends BasePresenter<HomeContract.ChannelView> implements HomeContract.ChannelPresenter {

    @Override
    public void channelList(String id, int page, int size) {
        addSubscribe(HttpManager.getMyApi().getChannelData(id,page,size)
                .compose(RxUtils.<ChannelGoodsList>rxScheduler())
                .subscribeWith(new CommonSubscriber<ChannelGoodsList>(mView) {
                    @Override
                    public void onNext(ChannelGoodsList data) {
                        if (mView!=null) {
                            mView.getChannelReturn(data.getData().getData());
                        }
                    }

                }));
    }
}
