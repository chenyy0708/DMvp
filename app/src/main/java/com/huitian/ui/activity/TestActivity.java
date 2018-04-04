package com.huitian.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.huitian.bean.Meizhi;
import com.huitian.chen.R;
import com.huitian.di.component.DaggerTestComponent;
import com.huitian.di.module.TestModule;
import com.huitian.mvp.TestContract;
import com.huitian.mvp.TestPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.View {
    @Inject
    BaseQuickAdapter mAdapter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.bt)
    Button bt;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        bt.setVisibility(View.VISIBLE);
        mPresenter.getData(10);
        // test
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTestComponent.builder()
                .appComponent(appComponent)
                .testModule(new TestModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showErrorTip(String msg) {
        showShortToast(msg);
    }

    @Override
    public void setData(List<Meizhi> mData) {
    }

    public void onClickFragment(View view) {
        startActivity(TestFragmentActivity.class);
    }

    @Override
    public Activity getVActivity() {
        return this;
    }

}
