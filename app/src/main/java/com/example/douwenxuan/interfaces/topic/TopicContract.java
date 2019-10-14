package com.example.douwenxuan.interfaces.topic;


import com.example.douwenxuan.interfaces.IBaseView;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.model.bean.topic.DetailBean;
import com.example.douwenxuan.model.bean.topic.RelatedBean;
import com.example.douwenxuan.model.bean.topic.TopicBean;

public interface TopicContract {

    //主页的接口定义
    interface View extends IBaseView {
        void topicDataReturn(TopicBean msg);
    }

    //主页的p层接口
    interface Presenter extends IPresenter<View> {
        void topic();
        void topic(int page);
    }

    interface InfoView extends IBaseView {
        void topicDetailReturn(DetailBean bean);
        void topicRelatedReturn(RelatedBean bean);

    }
    interface InfoPresenter extends IPresenter<InfoView> {
        void topicDetail(String id);
        void topicRelated(String id);
    }

}
