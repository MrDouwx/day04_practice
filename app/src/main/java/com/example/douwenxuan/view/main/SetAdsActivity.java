package com.example.douwenxuan.view.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.douwenxuan.day04_practice.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetAdsActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_address_set)
    TextView tvAddressSet;
    @BindView(R.id.et_detail)
    EditText etDetail;
    @BindView(R.id.iv_set_default)
    ImageButton ivSet;
    private CityPickerView mPicker = new CityPickerView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ads);
        ButterKnife.bind(this);
        mPicker.init(this); //必须！  初始化城市数据
    }

    @OnClick({R.id.tv_address_set, R.id.iv_set_default})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address_set:
                setAddress();
                break;
            case R.id.iv_set_default:

                break;
        }
    }

    private void setAddress() {
        //添加默认的配置，可以自己修改
        CityConfig cityConfig = new CityConfig.Builder()
                .province("北京") //设置默认显示省份
                .build();
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份
                if (province != null && city != null && district != null) {
                    tvAddressSet.setText(province.toString() + "  " + city.toString() + "  " +
                            district.toString());
                }
            }
            @Override
            public void onCancel() {
                ToastUtils.showLongToast(SetAdsActivity.this, "已取消");
            }
        });
        //显示
        mPicker.showCityPicker();
    }
}
