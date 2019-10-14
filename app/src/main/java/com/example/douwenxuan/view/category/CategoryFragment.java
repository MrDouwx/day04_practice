package com.example.douwenxuan.view.category;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.example.douwenxuan.base.BaseFragment;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.category.CategoryContract;
import com.example.douwenxuan.model.bean.category.BrandBean;
import com.example.douwenxuan.model.bean.category.CategoryListBean;
import com.example.douwenxuan.presenter.category.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment implements CategoryContract.View {

    @BindView(R.id.vTab)
    VerticalTabLayout vTab;
    @BindView(R.id.vp_sort)
    ViewPager vpSort;
    Unbinder unbinder;


    @Override
    protected int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        ((CategoryPresenter)presenter).category();
    }

    @Override
    protected IPresenter createPresenter() {
        return new CategoryPresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void categoryDataReturn(List<CategoryListBean> categoryList) {
        final ArrayList<String> tabName = new ArrayList<>();
        final ArrayList<Fragment> fragments = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i++) {
            fragments.add(new CategoryTabFragment(categoryList.get(i).getName(), categoryList.get(i).getId() + ""));
            tabName.add(categoryList.get(i).getName());
        }
        FragTabAdapter fragTabAdapter = new FragTabAdapter(getChildFragmentManager(), fragments,
                tabName);
        vpSort.setAdapter(fragTabAdapter);
        vTab.setupWithViewPager(vpSort);
    }

}
