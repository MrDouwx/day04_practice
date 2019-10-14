package com.example.douwenxuan.presenter.topic;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.topic.TopicContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.topic.TopicBean;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class TopicPresenter extends BasePresenter<TopicContract.View> implements TopicContract.Presenter{
    /**
     * p层业务逻辑处理
     *
     */
    @Override
    public void topic() {
        addSubscribe(HttpManager.getMyApi().getTopic()
        .compose(RxUtils.<TopicBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicBean>(mView){

            @Override
            public void onNext(TopicBean topicBean) {
                mView.topicDataReturn(topicBean);
            }

        }));
    }

    @Override
    public void topic(int page) {
        addSubscribe(HttpManager.getMyApi().getTopic(page)
                .compose(RxUtils.<TopicBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(mView){

                    @Override
                    public void onNext(TopicBean topicBean) {
                        mView.topicDataReturn(topicBean);
                    }

                }));
    }


}
