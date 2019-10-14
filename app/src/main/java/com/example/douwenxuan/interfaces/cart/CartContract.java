package com.example.douwenxuan.interfaces.cart;


import com.example.douwenxuan.interfaces.IBaseView;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.model.bean.category.CategoryListBean;
import com.example.douwenxuan.model.bean.goods.CartBean;

import java.util.List;

public interface CartContract {

    //主页的接口定义
    interface View extends IBaseView {
        void cartListReturn(CartBean bean);
        //void cartList(CartBean bean);

    }

    //主页的p层接口
    interface Presenter extends IPresenter<View> {
        void cartList(int id);
        void delGoods(int uid,String productId);
        void updateGoods(int uid,int goodsId,int productId,int id,int num);

    }


}
