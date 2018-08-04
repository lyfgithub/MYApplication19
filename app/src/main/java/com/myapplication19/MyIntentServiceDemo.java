package com.myapplication19;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Author LYF
 * Created by Administrator on 2016/9/16.
 */
public class MyIntentServiceDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myintentservicedemo);
    }

    public void HanderIntent1(View v){
        MyIntentService.startActionFoo(this,"aaaa","bbbbb");
        MyIntentService.startActionBaz(this,"cccc","www");
    }


}
