package com.lodz.android.component.mvp.base.fragment;

import android.support.annotation.NonNull;

import com.lodz.android.component.base.fragment.BaseFragment;
import com.lodz.android.component.mvp.contract.base.BasePresenterContract;
import com.lodz.android.component.mvp.contract.base.BaseViewContract;

/**
 * MVP基类Fragment（带基础状态控件）
 * Created by zhouL on 2017/8/2.
 */

public abstract class MvpBaseFragment<PC extends BasePresenterContract<VC>, VC extends BaseViewContract> extends BaseFragment implements BaseViewContract {

    /** Presenter接口 */
    private PC mPresenterContract;

    @Override
    @SuppressWarnings("unchecked")
    protected void startCreate() {
        super.startCreate();
        mPresenterContract = createMainPresenter();
        mPresenterContract.onCreate(getContext(), (VC) this);
    }

    protected abstract PC createMainPresenter();

    @NonNull
    protected PC getPresenterContract(){
        return mPresenterContract;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenterContract.onDestroy();
    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
        mPresenterContract.onResume();
    }

    @Override
    protected void onFragmentPause() {
        super.onFragmentPause();
        mPresenterContract.onPause();
    }

    @Override
    protected void clickBackBtn() {
        super.clickBackBtn();
        mPresenterContract.clickBackBtn();
    }

    @Override
    protected void clickReload() {
        super.clickReload();
        mPresenterContract.clickReload();
    }

    @Override
    public void showStatusNoData() {
        super.showStatusNoData();
    }

    @Override
    public void showStatusError() {
        super.showStatusError();
    }

    @Override
    public void showStatusLoading() {
        super.showStatusLoading();
    }

    @Override
    public void showStatusCompleted() {
        super.showStatusCompleted();
    }

    @Override
    public void goneTitleBar() {
        super.goneTitleBar();
    }

    @Override
    public void showTitleBar() {
        super.showTitleBar();
    }

}
