package com.sunfusheng.rxjava;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

/**
 * @author sunfusheng on 2017/9/28.
 */
public class BaseRxJava {

    protected static final String TAG = "--->";
    protected TextView textView;
    protected StringBuilder sb;

    public BaseRxJava(TextView textView) {
        this.textView = textView;
        this.sb = new StringBuilder();
    }

    protected void setText(String s) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append(s).append("\n");
        if (textView != null && isMainThread()) {
            textView.setText(sb);
        }
    }

    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    protected void log(String s) {
        if (!TextUtils.isEmpty(s)) {
            Log.d(TAG, s);
            setText(s);
        }
    }

    protected void log(int i) {
        log("" + i);
    }

    protected void printCurrentThread() {
        printCurrentThread("");
    }

    protected void printCurrentThread(String prefix) {
        String s = TextUtils.isEmpty(prefix) ? "Current thread:" : prefix + " current thread:" + Thread.currentThread().getName();
        log(s);
    }
}
