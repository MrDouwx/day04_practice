package com.example.douwenxuan.presenter.topic;

import com.example.douwenxuan.component.CommonSubscriber;
import com.example.douwenxuan.interfaces.topic.TopicContract;
import com.example.douwenxuan.model.HttpManager;
import com.example.douwenxuan.model.bean.topic.DetailBean;
import com.example.douwenxuan.model.bean.topic.RelatedBean;
import com.example.douwenxuan.model.bean.topic.TopicBean;
import com.example.douwenxuan.presenter.BasePresenter;
import com.example.douwenxuan.utils.RxUtils;

public class TopicDetailPresenter extends BasePresenter<TopicContract.InfoView> implements TopicContract.InfoPresenter{
    /**
     * p层业务逻辑处理
     *
     */
    @Override
    public void topicDetail(String id) {
        addSubscribe(HttpManager.getMyApi().getTopicData(id)
        .compose(RxUtils.<DetailBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<DetailBean>(mView){

            @Override
            public void onNext(DetailBean bean) {
                mView.topicDetailReturn(bean);
            }

        }));
    }

    @Override
    public void topicRelated(String id) {
        addSubscribe(HttpManager.getMyApi().getTopicRelated(id)
                .compose(RxUtils.<RelatedBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RelatedBean>(mView){

                    @Override
                    public void onNext(RelatedBean bean) {
                        RelatedBean bean1 = bean;
                        mView.topicRelatedReturn(bean1);
                    }

                }));
    }


}
