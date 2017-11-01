package com.sunfusheng.rxjava;

import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author sunfusheng on 2017/11/1.
 */
public class RxJava02 extends BaseRxJava {

    public RxJava02(TextView textView) {
        super(textView);
    }

    public void test() {
        flatMap01();
        flatMap02();
    }

    public void flatMap01() {
        Observable.defer(() -> Observable.fromIterable(buildData02("flatMap02()")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .toList()
                .subscribe(it -> log(it.toString()));
    }

    public void flatMap02() {
        Observable.defer(() -> Observable.just(buildData02("flatMap02()")))
                .subscribeOn(Schedulers.io())
                .flatMap(it -> Observable.defer(() -> Observable.just(it))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> log(it.toString()));
    }

}
