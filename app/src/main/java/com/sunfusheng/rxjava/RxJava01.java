package com.sunfusheng.rxjava;

import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author sunfusheng on 2017/9/28.
 */
public class RxJava01 extends BaseRxJava {

    public RxJava01(TextView textView) {
        super(textView);
    }

    public void test() {
        create01();
        create02();
        just01();
        just02();
        just03();
        from01();
        defer01();
        defer02();
        testJustDeferOperator();
    }

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
                .map(it -> it * 10)
                .doOnNext(it -> printCurrentThread("2.doOnNext()"))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(it -> printCurrentThread("3.doOnNext()"))
                .subscribeOn(Schedulers.io())
                .doOnNext(it -> printCurrentThread("4.doOnNext()"))
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
        Observable.just(buildData01("just03()"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> log(it.toString()));
    }

    public void from01() {
        Observable.fromArray(buildData01("from01()"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(it -> it * 2)
                .toList()
                .subscribe(it -> log(it.toString()));
    }

    public void defer01() {
        Observable.defer(() -> Observable.fromArray(buildData01("defer01()")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(it -> log(it.toString()));
    }

    public void defer02() {
        Observable.defer(() -> Observable.just(buildData02("defer02()")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> log(it.toString()));
    }

    public static class ValueClass {
        private String value = "default";

        public void setValue(String value) {
            this.value = value;
        }

        public Observable<String> justObservable() {
            return Observable.just(value);
        }

        public Observable<String> deferObservable() {
            return Observable.defer(this::justObservable);
        }
    }

    public void testJustDeferOperator() {
        ValueClass instance = new ValueClass();
        Observable<String> justObservable = instance.justObservable();
        instance.setValue("some value");
        justObservable.subscribe(it -> log("justObservable() value:" + it));

        instance = new ValueClass();
        Observable<String> deferObservable = instance.deferObservable();
        instance.setValue("some value");
        deferObservable.subscribe(it -> log("deferObservable() value:" + it));
    }
}
