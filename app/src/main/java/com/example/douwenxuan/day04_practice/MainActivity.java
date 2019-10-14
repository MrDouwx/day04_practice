package com.example.douwenxuan.day04_practice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.douwenxuan.base.BaseActivity;
import com.example.douwenxuan.view.category.CategoryFragment;
import com.example.douwenxuan.view.home.HomeFragment;
import com.example.douwenxuan.view.main.MainFragment;
import com.example.douwenxuan.view.shop.ShoppingFragment;
import com.example.douwenxuan.view.topic.TopicFragment;
import com.example.douwenxuan.interfaces.IPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_main)
    FrameLayout flMain;

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            //切换页面，是另外一个事物，不能跟初始化共同使用一个事物
            FragmentTransaction transaction = manager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.fl_main,homeFragment).commit();

                    return true;
                case R.id.navigation_topic:
                    transaction.replace(R.id.fl_main,topicFragment).commit();

                    return true;
                case R.id.navigation_category:
                    transaction.replace(R.id.fl_main,categoryFragment).commitNow();

                    return true;
                case R.id.navigation_shopping:
                    transaction.replace(R.id.fl_main,shoppingFragment).commit();

                    return true;
                case R.id.navigation_main:
                    transaction.replace(R.id.fl_main,mainFragment).commit();

                    return true;
                    default:
                        break;
            }
            return false;
        }
    };
    private HomeFragment homeFragment;
    private TopicFragment topicFragment;
    private CategoryFragment categoryFragment;
    private ShoppingFragment shoppingFragment;
    private MainFragment mainFragment;
    private FragmentManager manager;
    private BottomNavigationView navigation;

    public BottomNavigationView getNavigation() {
        return navigation;
    }

    public TopicFragment getTopicFragment() {
        return topicFragment;
    }

    public ShoppingFragment getShoppingFragment() {
        return shoppingFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        homeFragment = new HomeFragment();
        topicFragment = new TopicFragment();
        categoryFragment = new CategoryFragment();
        shoppingFragment = new ShoppingFragment();
        mainFragment = new MainFragment();

        manager = getSupportFragmentManager();
        //初始化显示主页，是一个事物
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main,homeFragment).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String name = intent.getStringExtra("dou");
        if (name!=null) {
            if (name.equals("wen")) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_main,shoppingFragment).commit();
                getNavigation().setSelectedItemId(R.id.navigation_shopping);
            }
        }
    }
}
