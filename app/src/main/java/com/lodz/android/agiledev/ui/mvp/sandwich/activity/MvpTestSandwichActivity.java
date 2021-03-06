package com.lodz.android.agiledev.ui.mvp.sandwich.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lodz.android.agiledev.R;
import com.lodz.android.agiledev.ui.mvp.sandwich.MvpTestSandwichPresenter;
import com.lodz.android.agiledev.ui.mvp.sandwich.MvpTestSandwichViewContract;
import com.lodz.android.component.mvp.base.activity.MvpBaseSandwichActivity;
import com.lodz.android.component.widget.base.TitleBarLayout;
import com.lodz.android.core.utils.ToastUtils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MVP带基础状态控件、中部刷新控件和顶部/底部扩展Activity
 * Created by zhouL on 2018/4/17.
 */
public class MvpTestSandwichActivity extends MvpBaseSandwichActivity<MvpTestSandwichPresenter, MvpTestSandwichViewContract> implements MvpTestSandwichViewContract{

    public static void start(Context context) {
        Intent starter = new Intent(context, MvpTestSandwichActivity.class);
        context.startActivity(starter);
    }

    /** 结果 */
    @BindView(R.id.result)
    TextView mResult;
    /** 获取成功数据按钮 */
    @BindView(R.id.get_success_reuslt_btn)
    Button mGetSuccessResultBtn;
    /** 获取失败数据按钮 */
    @BindView(R.id.get_fail_reuslt_btn)
    Button mGetFailResultBtn;

    /** 顶部标题栏 */
    @BindView(R.id.title_bar_layout)
    TitleBarLayout mTitleBarLayout;

    @Override
    protected MvpTestSandwichPresenter createMainPresenter() {
        return new MvpTestSandwichPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp_test_layout;
    }

    @Override
    protected int getTopLayoutId() {
        return R.layout.view_mvc_test_top_layout;
    }

    @Override
    protected int getBottomLayoutId() {
        return R.layout.view_mvc_test_bottom_layout;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mTitleBarLayout.setTitleName(R.string.mvp_demo_sandwich_title);
        setSwipeRefreshEnabled(true);
    }

    @Override
    protected void clickReload() {
        super.clickReload();
        showStatusLoading();
        getPresenterContract().getResult(true);
    }

    @Override
    protected void onDataRefresh() {
        getPresenterContract().getRefreshData(new Random().nextInt(9) % 2 == 0);
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        mTitleBarLayout.setOnBackBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mGetSuccessResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatusLoading();
                getPresenterContract().getResult(true);
            }
        });

        mGetFailResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatusLoading();
                getPresenterContract().getResult(false);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        showStatusNoData();
    }

    @Override
    public void setResult(String result) {
        mResult.setText(result);
    }

    @Override
    public void refreshFail(String tips) {
        ToastUtils.showShort(getContext(), tips);
    }

    @Override
    public void showResult() {
        mResult.setVisibility(View.VISIBLE);
    }

}
