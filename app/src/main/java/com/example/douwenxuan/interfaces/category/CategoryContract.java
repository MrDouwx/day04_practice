package com.example.douwenxuan.interfaces.category;


import com.example.douwenxuan.interfaces.IBaseView;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.model.bean.category.BrandBean;
import com.example.douwenxuan.model.bean.category.CategoryListBean;
import com.example.douwenxuan.model.bean.category.GoodsSort;
import com.example.douwenxuan.model.bean.home.HomeBean;

import java.util.List;

public interface CategoryContract {

    //主页的接口定义
    interface View extends IBaseView {
        void categoryDataReturn(List<CategoryListBean> msg);
        //void showErrMsg(String err);
    }

    //主页的p层接口
    interface Presenter extends IPresenter<View> {
        void category();
    }


    interface GoodsView extends IBaseView{
        void categoryBList(GoodsSort bean);
    }
    interface GPresenter extends IPresenter<GoodsView> {
        void categoryBList(String id);
    }
}
