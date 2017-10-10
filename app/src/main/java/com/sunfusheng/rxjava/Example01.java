package com.sunfusheng.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author sunfusheng on 2017/9/28.
 */
public class Example01 extends BaseExample {

    public void create01() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
        }).subscribe(this::log, Throwable::printStackTrace);
    }

    public void create02() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            printCurrentThread("create()");
            e.onNext(1);
        }).subscribeOn(Schedulers.io())
                .doOnNext(it -> printCurrentThread("1.doOnNext()"))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(it -> printCurrentThread("2.doOnNext()"))
                .subscribeOn(Schedulers.io())
                .doOnNext(it -> printCurrentThread("3.doOnNext()"))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(it -> printCurrentThread("4.doOnNext()"))
                .subscribeOn(Schedulers.io())
                .doOnNext(it -> printCurrentThread("5.doOnNext()"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::log);
    }

    public void just01() {
        Observable.just(1)
                .subscribe(this::log);
    }

    public void just02() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe(this::log);
    }

    public void just03() {
        Observable.just(buildData("just03()"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> log(it.toString()));
    }

    private Integer[] buildData(String prefix) {
        printCurrentThread(prefix);
        return new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    public void from01() {
        Observable.fromArray(buildData("from01()"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(it -> it * 2)
                .toList()
                .subscribe(it -> log(it.toString()));
    }

    public void defer01() {
        Observable.defer(() -> Observable.fromArray(buildData("defer01()")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(it -> log(it.toString()));
    }

    public void defer02() {
        Observable.defer(() -> Observable.just(buildData("defer02()")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> log(it.toString()));
    }
}
