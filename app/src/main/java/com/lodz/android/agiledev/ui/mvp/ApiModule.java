package com.lodz.android.agiledev.ui.mvp;

import com.lodz.android.component.rx.exception.DataException;
import com.lodz.android.core.utils.UiHandler;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 数据
 * Created by zhouL on 2017/7/9.
 */

public class ApiModule {

    public static Observable<String> requestResult(final boolean isSuccess){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                if (emitter.isDisposed()){
                    return;
                }
                try {
                    UiHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (emitter.isDisposed()){
                                return;
                            }
                            if (!isSuccess){
                                emitter.onError(new DataException("request fail"));
                                return;
                            }
                            emitter.onNext("result is " + System.currentTimeMillis());
                            emitter.onComplete();
                        }
                    }, 1000);
                }catch (Exception e){
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        });
    }
}
