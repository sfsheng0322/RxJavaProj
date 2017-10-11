package com.sunfusheng.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        example01Test();
    }

    void example01Test() {
        Example01 _01 = new Example01();
//        _01.create01();
//        _01.create02();
//        _01.just01();
//        _01.just02();
//        _01.just03();
//        _01.from01();
//        _01.defer01();
//        _01.defer02();
        _01.testJustDeferOperator();
    }

}
