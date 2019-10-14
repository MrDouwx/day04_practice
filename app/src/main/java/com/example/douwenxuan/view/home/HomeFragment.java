package com.example.douwenxuan.view.home;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.douwenxuan.adapter.CategoryAdapter;
import com.example.douwenxuan.adapter.HomeBrandAdapter;
import com.example.douwenxuan.adapter.HomeHotAdapter;
import com.example.douwenxuan.adapter.HomeNewAdapter;
import com.example.douwenxuan.adapter.HomeTopicAdapter;
import com.example.douwenxuan.base.BaseFragment;
import com.example.douwenxuan.day04_practice.ItemSpace;
import com.example.douwenxuan.day04_practice.MainActivity;
import com.example.douwenxuan.day04_practice.R;
import com.example.douwenxuan.interfaces.IPresenter;
import com.example.douwenxuan.interfaces.home.HomeContract;
import com.example.douwenxuan.model.bean.home.BannerBean;
import com.example.douwenxuan.model.bean.home.BrandListBean;
import com.example.douwenxuan.model.bean.home.CategoryListBean;
import com.example.douwenxuan.model.bean.home.channel.ChannelBean;
import com.example.douwenxuan.model.bean.home.HomeBean;
import com.example.douwenxuan.model.bean.home.HotGoodsListBean;
import com.example.douwenxuan.model.bean.home.NewGoodsListBean;
import com.example.douwenxuan.model.bean.home.TopicListBean;
import com.example.douwenxuan.presenter.home.HomePresenter;
import com.example.douwenxuan.view.topic.TopicInfoActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, View.OnClickListener {

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.banner_main)
    Banner bannerMain;
    @BindView(R.id.tv_juJia)
    TextView tvJuJia;
    @BindView(R.id.tv_canChu)
    TextView tvCanChu;
    @BindView(R.id.tv_peiJian)
    TextView tvPeiJian;
    @BindView(R.id.tv_fuZhuang)
    TextView tvFuZhuang;
    @BindView(R.id.tv_zhiQu)
    TextView tvZhiQu;

    @BindView(R.id.rv_brand)
    RecyclerView rvBrand;
    @BindView(R.id.rv_new)
    RecyclerView rvNew;
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R.id.rv_topic)
    RecyclerView rvTopic;
    @BindView(R.id.rv_category_home)
    RecyclerView rvCategoryHome;
    @BindView(R.id.tv_brand_title)
    TextView tvBrandTitle;
    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.tv_topic)
    TextView tvTopic;
    Unbinder unbinder;
    private List<BrandListBean> brand;
    private ArrayList<CategoryListBean> category;
    private List<HotGoodsListBean> hotGoodsListBeans;
    private List<NewGoodsListBean> newGoodsListBeans;
    private List<TopicListBean> topicListBeans;
    private HomeBrandAdapter homeBrandAdapter;
    private HomeNewAdapter homeNewAdapter;
    private HomeHotAdapter homeHotAdapter;
    private HomeTopicAdapter homeTopicAdapter;
    private CategoryAdapter categoryAdapter;
    private List<ChannelBean> channel;
    private MainActivity activity;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        brand = new ArrayList<>();
        category = new ArrayList<>();
        hotGoodsListBeans = new ArrayList<>();
        newGoodsListBeans = new ArrayList<>();
        topicListBeans = new ArrayList<>();


        brandListData(brand);

        newListView(newGoodsListBeans);

        hotListData(hotGoodsListBeans);

        topicListData(topicListBeans);

        categoryListData(category);

    }

    @Override
    protected void initData() {
        ((HomePresenter)presenter).home();
    }

    @Override
    protected IPresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showErrMsg(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void homeDataReturn(HomeBean beans) {
        List<BannerBean> banner = beans.getData().getBanner();
        List<BrandListBean> brandList = beans.getData().getBrandList();

        List<CategoryListBean> categoryList = beans.getData().getCategoryList();
        channel = beans.getData().getChannel();

        List<HotGoodsListBean> hotGoodsList = beans.getData().getHotGoodsList();
        List<NewGoodsListBean> newGoodsList = beans.getData().getNewGoodsList();
        List<TopicListBean> topicList = beans.getData().getTopicList();

        bannerData(banner, channel);

        brand.addAll(brandList);
        category.addAll(categoryList);
        hotGoodsListBeans.addAll(hotGoodsList);
        newGoodsListBeans.addAll(newGoodsList);
        topicListBeans.addAll(topicList);

        homeBrandAdapter.notifyDataSetChanged();
        homeNewAdapter.notifyDataSetChanged();
        homeHotAdapter.notifyDataSetChanged();
        homeTopicAdapter.notifyDataSetChanged();
        categoryAdapter.notifyDataSetChanged();

    }

    private void bannerData(List<BannerBean> banner, List<ChannelBean> channel) {
        bannerMain.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerBean pathPic = (BannerBean) path;
                Glide.with(context).load(pathPic.getImage_url()).into(imageView);
            }
        }).setImages(banner)
                .setDelayTime(2000)
                .isAutoPlay(true)
                .start();

        tvJuJia.setText(channel.get(0).getName());
        tvCanChu.setText(channel.get(1).getName());
        tvPeiJian.setText(channel.get(2).getName());
        tvFuZhuang.setText(channel.get(3).getName());
        tvZhiQu.setText(channel.get(4).getName());

        tvJuJia.setOnClickListener(this);
        tvCanChu.setOnClickListener(this);
        tvPeiJian.setOnClickListener(this);
        tvFuZhuang.setOnClickListener(this);
        tvZhiQu.setOnClickListener(this);
    }

    private void brandListData(List<BrandListBean> brandList) {
        rvBrand.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvBrand.addItemDecoration(new ItemSpace(2));
        homeBrandAdapter = new HomeBrandAdapter(brandList);
        rvBrand.setAdapter(homeBrandAdapter);
        tvBrandTitle.setOnClickListener(this);
        homeBrandAdapter.setClickListener(new HomeBrandAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, BrandListBean bean) {
                Intent intent = new Intent(getActivity(), BrandDetailActivity.class);
                intent.putExtra("id",bean.getId()+"");
                startActivity(intent);
            }
        });
    }

    private void newListView(List<NewGoodsListBean> newGoodsList) {
        rvNew.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvNew.addItemDecoration(new ItemSpace(10));
        homeNewAdapter = new HomeNewAdapter(newGoodsList);
        rvNew.setAdapter(homeNewAdapter);
        tvNew.setOnClickListener(this);
        homeNewAdapter.setClickListener(new HomeNewAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, NewGoodsListBean data) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                intent.putExtra("goodId",data.getId()+"");
                getActivity().startActivity(intent);
            }
        });
    }

    private void hotListData(List<HotGoodsListBean> hotGoodsList) {
        rvHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHot.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        homeHotAdapter = new HomeHotAdapter(hotGoodsList);
        rvHot.setAdapter(homeHotAdapter);
        homeHotAdapter.setClickListener(new HomeHotAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, HotGoodsListBean data) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                intent.putExtra("goodId",data.getId()+"");
                getActivity().startActivity(intent);
            }
        });
    }

    private void topicListData(final List<TopicListBean> topicList) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvTopic.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeTopicAdapter = new HomeTopicAdapter(topicList);
        rvTopic.setAdapter(homeTopicAdapter);

        tvTopic.setOnClickListener(this);

        homeTopicAdapter.setClickListener(new HomeTopicAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, TopicListBean data) {
                Intent intent = new Intent(getActivity(), TopicInfoActivity.class);
                intent.putExtra("id",data.getId()+"");
                startActivity(intent);
            }
        });
    }

    private void categoryListData(ArrayList<CategoryListBean> categoryList) {
        rvCategoryHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCategoryHome.addItemDecoration(new ItemSpace(20));
        categoryAdapter = new CategoryAdapter(categoryList);
        rvCategoryHome.setAdapter(categoryAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_juJia:
                showList(tvJuJia);
                break;
            case R.id.tv_canChu:
                showList(tvCanChu);
                break;
            case R.id.tv_peiJian:
                showList(tvPeiJian);
                break;
            case R.id.tv_fuZhuang:
                showList(tvFuZhuang);
                break;
            case R.id.tv_zhiQu:
                showList(tvZhiQu);
                break;
            case R.id.tv_brand_title:

                Intent brand = new Intent(getActivity(), BrandListActivity.class);
                getActivity().startActivity(brand);

                break;
            case R.id.tv_new:

                Intent newList = new Intent(getActivity(), HotShowActivity.class);
                getActivity().startActivity(newList);

                break;
            case R.id.tv_topic:

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                activity = (MainActivity) getActivity();
                transaction.replace(R.id.fl_main, activity.getTopicFragment()).commit();
                activity.getNavigation().setSelectedItemId(R.id.navigation_topic);

                break;
            default:
                break;
        }
    }

    private void showList(TextView view) {
        String name = view.getText().toString().trim();
        for (int i = 0; i < channel.size(); i++) {
            if (name.equals(channel.get(i).getName())) {
                Intent intent = new Intent(getActivity(), ChannelDataActivity.class);
                intent.putExtra("id",channel.get(i).getCategoryid());
                startActivity(intent);
            }
        }
    }

}
