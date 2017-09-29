package com.sunfusheng.rxjava;

import android.util.Log;

/**
 * @author sunfusheng on 2017/9/28.
 */
public class BaseExample {

    protected static final String TAG = "--->";

    protected void log(int i) {
        Log.d(TAG, " " + i);
    }

    protected void log(String s) {
        Log.d(TAG, " " + s);
    }

    protected void logE(int i) {
        Log.e(TAG, " " + i);
    }

    protected void logE(String s) {
        Log.e(TAG, " " + s);
    }

    protected void printCurrentThread() {
        printCurrentThread(" ");
    }

    protected void printCurrentThread(String prefix) {
        log(prefix + " current thread: " + Thread.currentThread().getName());
    }
}
