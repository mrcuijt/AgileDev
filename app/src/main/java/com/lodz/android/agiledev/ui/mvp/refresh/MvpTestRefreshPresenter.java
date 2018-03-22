package com.lodz.android.agiledev.ui.mvp.refresh;

import com.lodz.android.agiledev.ui.mvp.ApiModule;
import com.lodz.android.component.mvp.presenter.BaseRefreshPresenter;
import com.lodz.android.component.rx.subscribe.observer.BaseObserver;
import com.lodz.android.component.rx.utils.RxUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * 测试Presenter
 * Created by zhouL on 2017/7/29.
 */

public class MvpTestRefreshPresenter extends BaseRefreshPresenter<MvpTestRefreshViewContract>{

    /** 数据来源 */
    private ApiModule mApiModule;

    public MvpTestRefreshPresenter() {
        this.mApiModule = new ApiModule();
    }

    public void getResult(){
        ApiModule.requestResult()
                .compose(RxUtils.<String>ioToMainObservable())
                .compose(this.<String>bindUntilActivityEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onBaseNext(String s) {
                        getViewContract().showResult();
                        getViewContract().setResult(s);
                        getViewContract().showStatusCompleted();
                    }

                    @Override
                    public void onBaseError(Throwable e) {
                        getViewContract().showStatusError();
                    }
                });
    }

    public void getRefreshData(){
        ApiModule.requestResult()
                .compose(RxUtils.<String>ioToMainObservable())
                .compose(this.<String>bindUntilActivityEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onBaseNext(String s) {
                        getViewContract().setSwipeRefreshFinish();
//                        getViewContract().refreshFail("刷新数据失败");
                        getViewContract().showResult();
                        getViewContract().setResult(s);
                    }

                    @Override
                    public void onBaseError(Throwable e) {
                        getViewContract().showStatusError();
                    }
                });
    }
}
