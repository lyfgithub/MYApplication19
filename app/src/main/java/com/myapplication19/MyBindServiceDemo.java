package com.myapplication19;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class MyBindServiceDemo extends Activity {
    String Tag="MyBindServiceDemo3";
    MyConn conn=new MyConn();
    MyBindService.MyMiddlePerson myMiddlePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bindsevicedemo);
    }

    //绑定服务
    public void myBindService(View v){
        Intent intent=new Intent(this,MyBindService.class);
        MyBindServiceDemo.this.bindService(intent,conn,BIND_AUTO_CREATE);
    }

    //解除绑定服务
    public void myUnBindService(View v){
        MyBindServiceDemo.this.unbindService(conn);
    }

    //调用服务中方法
    public void mycallServiceFun(View v){
        if (myMiddlePerson!=null){
            myMiddlePerson.funInService2(55);
        }
    }

    //中间人
    class MyConn implements ServiceConnection{
        //服务连接成功时
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(Tag,"onServiceConnected()连接成功！");
            myMiddlePerson= (MyBindService.MyMiddlePerson) iBinder;  //得到中间人
        }

        //连接被断开
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v(Tag,"onServiceDisconnected()连接断开！");
        }
    }



}
