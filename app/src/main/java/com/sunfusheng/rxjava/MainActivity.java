package com.sunfusheng.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {

    TextView textView;
    Example01 example01 = new Example01();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

//        example01.create01();
//        example01.create02();
        example01.just01();
        example01.just02();
        example01.just03();
    }

}
